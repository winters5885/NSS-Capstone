package com.nashss.se.ascendnashville.activity;

import com.nashss.se.ascendnashville.activity.requests.UpdateEventRequest;
import com.nashss.se.ascendnashville.activity.results.UpdateEventResult;
import com.nashss.se.ascendnashville.converters.ModelConverter;
import com.nashss.se.ascendnashville.dynamoDB.EventDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the UpdateEventActivity for Ascend Nashville's UpdateEvent API.
 * <p>
 * This API allows the customer to update their saved event information.
 */
public class UpdateEventActivity {
    private final Logger log = LogManager.getLogger();
    private final EventDao eventDao;

    /**
     * Instantiates a new UpdateEventActivity object.
     *
     * @param eventDao EventDao to access the event table.
     */
    @Inject
    public UpdateEventActivity(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    /**
     * This method handles the incoming request by retrieving the event, updating it,
     * and persisting the event.
     * <p>
     * It then returns the updated event.
     * <p>
     * If the event does not exist, this should throw an EventNotFoundException.
     * <p>
     * If the provided eventId has invalid characters, throws an
     * InvalidAttributeValueException
     * <p>
     * If the request tries to update the event Id,
     * this should throw an InvalidAttributeChangeException
     *
     * @param updateEventRequest request object containing the event ID, date, and event details
     *                              associated with it
     * @return updateEventResult result object containing the API defined {@link Event}
     */
    public UpdateEventResult handleRequest(final UpdateEventRequest updateEventRequest) {
        log.info("Inside UpdateEventActivity handleRequest.");

        Event event = eventDao.getEvent(updateEventRequest.getEventId());

        event.setEventDetails(updateEventRequest.getEventDetails());
        event.setDate(updateEventRequest.getDate());
        event = eventDao.saveEvent(event);

        return UpdateEventResult.builder()
                .withEvent(new ModelConverter().toEventModel(event))
                .build();
    }
}
