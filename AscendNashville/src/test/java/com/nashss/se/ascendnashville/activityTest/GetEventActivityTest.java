package com.nashss.se.ascendnashville.activityTest;

import com.nashss.se.ascendnashville.activity.GetEventActivity;
import com.nashss.se.ascendnashville.activity.requests.GetEventRequest;

import com.nashss.se.ascendnashville.activity.results.GetEventResult;
import com.nashss.se.ascendnashville.dynamoDB.EventDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetEventActivityTest {

    @Mock
    private EventDao eventDao;

    private GetEventActivity getEventActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getEventActivity = new GetEventActivity(eventDao);
    }

    @Test
    public void handleRequest_savedEventFound_returnsEvent() {
        // GIVEN
        String eventId = "expectedId";

        Event event = new Event();
        event.setEventId(eventId);

        when(eventDao.getEvent(eventId)).thenReturn(event);

        GetEventRequest request = GetEventRequest.builder()
                .withEventId(eventId)
                .build();
        // WHEN
        GetEventResult result = getEventActivity.handleRequest(request);

        // THEN
        Assertions.assertEquals(eventId, result.getEvent().getEventId());
    }
}

