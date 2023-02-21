package com.nashss.se.ascendnashville.activity;

import com.nashss.se.ascendnashville.Exceptions.EventNotFoundException;
import com.nashss.se.ascendnashville.Exceptions.InvalidAttributeValueException;
import com.nashss.se.ascendnashville.activity.requests.DeleteEventRequest;
import com.nashss.se.ascendnashville.activity.results.DeleteEventResult;

import com.nashss.se.ascendnashville.converters.ModelConverter;
import com.nashss.se.ascendnashville.dynamoDB.EventDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Event;
import com.nashss.se.ascendnashville.utils.AscendNashvilleUtils;

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
    public DeleteEventActivity(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    /**
     * This method handles the incoming request by deleting the event in the event table.
     * <p>
     * It then returns the deleted event.
     * <p>
     * If the event does not exist, this should throw an EventNotFoundException.
     * <p>
     * If the provided eventId has invalid characters, throws an
     * InvalidAttributeValueException
     * <p>
     * @param deleteEventRequest request object containing the event ID
     *                              associated with it
     * @return DeleteEventRequest
     */
    public DeleteEventResult handleRequest(final DeleteEventRequest deleteEventRequest) {
        log.info("Inside DeleteEventActivity handleRequest.");

        if (!AscendNashvilleUtils.isValidString(deleteEventRequest.getEventId())) {
            throw new InvalidAttributeValueException("Event date [" + deleteEventRequest.getEventId() +
                    "} contains illegal characters");
        }
        Event event = eventDao.getEvent(deleteEventRequest.getEventId());

        if (event == null) {
            throw new EventNotFoundException("No event exists associated with event Id:" +
                    deleteEventRequest.getEventId());
        }
        event.setEventId(deleteEventRequest.getEventId());
        eventDao.deleteEvent(event);

        return DeleteEventResult.builder()
                .withEvent(new ModelConverter().toEventModel(event))
                .build();
    }
}
