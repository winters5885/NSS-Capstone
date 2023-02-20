package com.nashss.se.ascendnashville.activity;

import com.nashss.se.ascendnashville.Exceptions.EventNotFoundException;
import com.nashss.se.ascendnashville.Exceptions.InvalidAttributeValueException;
import com.nashss.se.ascendnashville.activity.requests.UpdateEventRequest;
import com.nashss.se.ascendnashville.activity.results.UpdateEventResult;
import com.nashss.se.ascendnashville.converters.ModelConverter;
import com.nashss.se.ascendnashville.dynamoDB.EventDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Event;

import com.nashss.se.ascendnashville.utils.AscendNashvilleUtils;
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
     * If the provided date has invalid characters, throws an
     * InvalidAttributeValueException
     * <p>
     * If the provided eventDetails has invalid characters, throw an
     * InvalidAttributeValueException
     *
     * @param updateEventRequest request object containing the event ID, date, and event details
     *                              associated with it
     * @return updateEventResult result object containing the API defined {@link Event}
     */
    public UpdateEventResult handleRequest(final UpdateEventRequest updateEventRequest) {
        log.info("Inside UpdateEventActivity handleRequest.");
        if(!AscendNashvilleUtils.isValidString(updateEventRequest.getDate())) {
            throw new InvalidAttributeValueException("Event date [" + updateEventRequest.getDate() +
                    "} contains illegal characters");
        }

        if(!AscendNashvilleUtils.isValidString(updateEventRequest.getEventDetails())) {
            throw new InvalidAttributeValueException("Event details [" + updateEventRequest.getEventDetails() +
                    "} contains illegal characters");
        }
        Event event = eventDao.getEvent(updateEventRequest.getEventId());

        if (event == null) {
            throw new EventNotFoundException("No event exists associated with eventID: " +
                    updateEventRequest.getEventId());
        }
        event.setEventDetails(updateEventRequest.getEventDetails());
        event.setDate(updateEventRequest.getDate());
        event = eventDao.saveEvent(event);

        return UpdateEventResult.builder()
                .withEvent(new ModelConverter().toEventModel(event))
                .build();
    }
}
