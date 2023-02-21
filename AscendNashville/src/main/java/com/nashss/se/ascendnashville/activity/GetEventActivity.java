package com.nashss.se.ascendnashville.activity;

import com.nashss.se.ascendnashville.Exceptions.EventNotFoundException;

import com.nashss.se.ascendnashville.activity.requests.GetEventRequest;

import com.nashss.se.ascendnashville.activity.results.GetEventResult;
import com.nashss.se.ascendnashville.converters.ModelConverter;
import com.nashss.se.ascendnashville.dynamoDB.EventDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Event;
import com.nashss.se.ascendnashville.models.EventModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the GetEventActivity for Ascend Nashville's GetEvent API.
 * <p>
 * This API allows the customer to create a new event.
 */
public class GetEventActivity {
    private final Logger log = LogManager.getLogger();
    private final EventDao eventDao;

    /**
     * Instantiates a new GetEventActivity object.
     *
     * @param eventDao EventDao to access the event table.
     */
    @Inject
    public GetEventActivity(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    /**
     * This method handles the incoming request by retrieving the event from the database.
     * <p>
     * It then returns the event.
     * <p>
     * If the event does not exist, this should throw a EventNotFoundException.
     *
     * @param getEventRequest request object.
     *
     * @return getEventResult result object containing the API defined {@link EventModel}
     */
    public GetEventResult handleRequest(final GetEventRequest getEventRequest) {
        log.info("In the GetEventActivity handleRequest.");
        String requestedEventId = getEventRequest.getEventId();

        Event event = eventDao.getEvent(requestedEventId);

        if (event == null) {
            throw new EventNotFoundException("No event exists with eventID:" + requestedEventId);
        }
        event.setEventId(getEventRequest.getEventId());

        EventModel eventModel = new ModelConverter().toEventModel(event);
        return GetEventResult.builder()
                .withEvent(eventModel)
                .build();
    }
}
