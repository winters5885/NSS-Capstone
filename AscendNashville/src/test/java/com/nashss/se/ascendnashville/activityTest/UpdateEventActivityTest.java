package com.nashss.se.ascendnashville.activityTest;

import com.nashss.se.ascendnashville.activity.UpdateEventActivity;
import com.nashss.se.ascendnashville.activity.requests.UpdateEventRequest;
import com.nashss.se.ascendnashville.activity.results.UpdateEventResult;
import com.nashss.se.ascendnashville.dynamoDB.EventDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UpdateEventActivityTest {
    @Mock
    private EventDao eventDao;

    private UpdateEventActivity updateEventActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        updateEventActivity = new UpdateEventActivity(eventDao);
    }

    @Test
    public void handleRequest_goodRequest_updatesEventDetails() {
        // GIVEN
        String eventId = "eventId";
        String expectedDate = "expectedDate";
        String expectedEventDetails = "expected event details";

        UpdateEventRequest request = UpdateEventRequest.builder()
                .withEventId(eventId)
                .withDate(expectedDate)
                .withEventDetails(expectedEventDetails)
                .build();

        Event startingEvent = new Event();
        startingEvent.setEventId(eventId);
        startingEvent.setDate(expectedDate);
        startingEvent.setEventDetails(expectedEventDetails);

        when(eventDao.getEvent(eventId)).thenReturn(startingEvent);
        when(eventDao.saveEvent(startingEvent)).thenReturn(startingEvent);

        // WHEN
        UpdateEventResult result = updateEventActivity.handleRequest(request);

        // THEN
        Assertions.assertEquals(eventId, result.getEventModel().getEventId());
        Assertions.assertEquals(expectedEventDetails, result.getEventModel().getEventDetails());
        Assertions.assertEquals(expectedDate, result.getEventModel().getDate());
    }
}
