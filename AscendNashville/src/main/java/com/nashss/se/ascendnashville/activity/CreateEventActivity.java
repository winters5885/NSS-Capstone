package com.nashss.se.ascendnashville.activity;

import com.nashss.se.ascendnashville.Exceptions.InvalidAttributeValueException;
import com.nashss.se.ascendnashville.activity.requests.CreateEventRequest;
import com.nashss.se.ascendnashville.activity.results.CreateEventResult;
import com.nashss.se.ascendnashville.converters.ModelConverter;
import com.nashss.se.ascendnashville.dynamoDB.EventDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Event;
import com.nashss.se.ascendnashville.models.EventModel;

import com.nashss.se.ascendnashville.utils.AscendNashvilleUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the CreateEventActivity for Ascent Nashville's CreateEvent API.
 * <p>
 * This API allows the customer to create a new event with eventID, date, and event details.
 */
public class CreateEventActivity {
    private final Logger log = LogManager.getLogger();
    private final EventDao eventDao;

    /**
     * Instantiates a new CreateEventActivity object.
     *
     * @param eventDao EventDao to access the event table.
     */
    @Inject
    public CreateEventActivity(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    /**
     * This method handles the incoming request by persisting a new event
     * with the provided eventId, date, and event details from the request.
     * <p>
     * It then returns the newly created event.
     * <p>
     * If the provided date,or event details has invalid characters,
     * throws an InvalidAttributeValueException.
     *
     * @param createEventRequest request object containing the eventId, date
     *                           and event details associated with it.
     * @return createEventResult result object containing the API defined {@link EventModel}
     */
    public CreateEventResult handleRequest(final CreateEventRequest createEventRequest) {
        log.info("In the CreateEventActivity handleRequest.");
        if (!AscendNashvilleUtils.isValidString(createEventRequest.getDate())) {
            throw new InvalidAttributeValueException("Event date [" + createEventRequest.getDate() +
                    "] contains illegal characters");
        }

        if (!AscendNashvilleUtils.isValidString(createEventRequest.getEventDetails())) {
            throw new InvalidAttributeValueException("Event details [" + createEventRequest.getEventDetails() +
                    "] contains illegal characters");
        }

        Event newEvent = new Event();
        newEvent.setEventId(AscendNashvilleUtils.generateEventId());
        newEvent.setDate(createEventRequest.getDate());
        newEvent.setEventDetails(createEventRequest.getEventDetails());

        eventDao.saveEvent(newEvent);

        EventModel eventModel = new ModelConverter().toEventModel(newEvent);
        return CreateEventResult.builder()
                .withEvent(eventModel)
                .build();
    }
}
