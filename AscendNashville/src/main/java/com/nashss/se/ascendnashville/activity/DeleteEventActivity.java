package com.nashss.se.ascendnashville.activity;


import com.nashss.se.ascendnashville.activity.requests.DeleteEventRequest;
import com.nashss.se.ascendnashville.activity.results.DeleteEventResult;
import com.nashss.se.ascendnashville.activity.results.UpdateEventResult;
import com.nashss.se.ascendnashville.converters.ModelConverter;
import com.nashss.se.ascendnashville.dynamoDB.EventDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Event;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the DeleteEventActivity for Ascend Nashville's DeleteEvent API.
 * <p>
 * This API allows the customer to delete an event.
 */
public class DeleteEventActivity {

    private final Logger log = LogManager.getLogger();
    private final EventDao eventDao;

    /**
     * Instantiates a new DeleteEventActivity object.
     *
     * @param eventDao EventDao to access the event table.
     */
    @Inject
    public DeleteEventActivity (EventDao eventDao) {
        this.eventDao = eventDao;
    }

    /**
     * This method handles the incoming request by deleting the event in the event table.
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
     * @param deleteEventRequest request object containing the event ID, date, and event details
     *                              associated with it
     */
    public DeleteEventResult handleRequest(final DeleteEventRequest deleteEventRequest) {
        log.info("Inside DeleteEventActivity handleRequest.");
        Event event = eventDao.getEvent(deleteEventRequest.getEventId());

        event.setEventId(deleteEventRequest.getEventId());
        eventDao.deleteEvent(event);

        return DeleteEventResult.builder()
                .withEvent(new ModelConverter().toDeletedEventModel(event))
                .build();
    }
}
