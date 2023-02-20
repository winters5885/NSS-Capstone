package com.nashss.se.ascendnashville.activity;

import com.nashss.se.ascendnashville.activity.results.GetAllEventsResult;
import com.nashss.se.ascendnashville.converters.ModelConverter;
import com.nashss.se.ascendnashville.dynamoDB.EventDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Event;

import com.nashss.se.ascendnashville.models.EventModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import javax.inject.Inject;

/**
 * Implementation of the GetAllEventsActivity for Ascend Nashville's GetAllEvents API.
 * <p>
 * This API allows the customer to create a new event.
 */
public class GetAllEventsActivity {
    private final Logger log = LogManager.getLogger();
    private final EventDao eventDao;

    /**
     * Instantiates a new GetEventActivity object.
     *
     * @param eventDao EventDao to access the event table.
     */
    @Inject
    public GetAllEventsActivity(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    /**
     * This method handles the incoming request by retrieving the events from the database.
     * <p>
     * It then returns the events.
     * <p>
     * @return getEventResult result object containing the API defined {@link EventModel}
     */
    public GetAllEventsResult handleRequest() {
        log.info("In the GetEventActivity handleRequest.");
        List<Event> events = eventDao.getEvents();
        List<EventModel> eventModels = new ModelConverter().toEventsModelList(events);
        return GetAllEventsResult.builder()
                .withEventsList(eventModels)
                .build();
    }
}
