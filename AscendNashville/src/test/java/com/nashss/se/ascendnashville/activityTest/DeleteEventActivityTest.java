package com.nashss.se.ascendnashville.activityTest;

import com.nashss.se.ascendnashville.activity.DeleteEventActivity;
import com.nashss.se.ascendnashville.activity.GetEventActivity;

import com.nashss.se.ascendnashville.activity.requests.DeleteEventRequest;
import com.nashss.se.ascendnashville.activity.requests.GetEventRequest;

import com.nashss.se.ascendnashville.activity.results.DeleteEventResult;
import com.nashss.se.ascendnashville.activity.results.GetEventResult;

import com.nashss.se.ascendnashville.dynamoDB.EventDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class DeleteEventActivityTest {

    @Mock
    private EventDao eventDao;

    private DeleteEventActivity deleteEventActivity;
    @Mock
    private GetEventActivity getEventActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        deleteEventActivity = new DeleteEventActivity(eventDao);
    }

    @Test
    public void handleRequest_goodRequest_deletesEvent() {
        // GIVEN
        String eventId = "eventIdToDelete";

        DeleteEventRequest request = DeleteEventRequest.builder()
                .withEventId(eventId)
                .build();

        GetEventRequest eventRequest = GetEventRequest.builder()
                .withEventId(eventId)
                .build();

        Event startingEvent = new Event();
        startingEvent.setEventId(eventId);

        when(eventDao.getEvent(eventId)).thenReturn(startingEvent);

        // WHEN
        DeleteEventResult result = deleteEventActivity.handleRequest(request);
        GetEventResult getResult = getEventActivity.handleRequest(eventRequest);

        // THEN
        Assertions.assertNull(getResult);



    }

}
