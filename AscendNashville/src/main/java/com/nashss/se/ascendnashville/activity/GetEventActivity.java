package com.nashss.se.ascendnashville.activity;

import com.nashss.se.ascendnashville.activity.results.GetEventResult;
import com.nashss.se.ascendnashville.converters.ModelConverter;
import com.nashss.se.ascendnashville.dynamoDB.EventDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Event;

import com.nashss.se.ascendnashville.models.EventModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.inject.Inject;
import java.util.List;

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
     * This method handles the incoming request by retrieving the events from the database.
     * <p>
     * It then returns the events.
     * <p>
     * If the event does not exist, this should throw a EventNotFoundException.
     *
     * @return getEventResult result object containing the API defined {@link EventModel}
     */
    public GetEventResult handleRequest() {
        log.info("In the GetEventActivity handleRequest.");
        List<Event> events = eventDao.getEvents();
        List<EventModel> eventModels = new ModelConverter().toEventsModelList(events);
        return GetEventResult.builder()
                .withEventsList(eventModels)
                .build();
    }
}
