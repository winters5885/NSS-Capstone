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

public class GetEventLambdaTest {

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
        String expectedEventId = "expectedEventId";
        String expectedDate = "expectedDate";
        String expectedEventDetails = "expectedEventDetails";

        Event event = new Event();
        event.setEventId(expectedEventId);
        event.setDate(expectedDate);
        event.setEventDetails(expectedEventDetails);

        when(eventDao.getEvent(expectedEventId)).thenReturn(event);

        GetEventRequest request = GetEventRequest.builder()
                .withEventId(expectedEventId)
                .build();

        // WHEN
        GetEventResult result = getEventActivity.handleRequest(request);

        // THEN
        Assertions.assertEquals(expectedEventId, result.getEvent().getEventId());
        Assertions.assertEquals(expectedDate, result.getEvent().getDate());
        Assertions.assertEquals(expectedEventDetails, result.getEvent().getEventDetails());
    }
}
