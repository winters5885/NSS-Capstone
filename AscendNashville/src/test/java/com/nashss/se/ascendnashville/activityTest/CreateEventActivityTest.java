package com.nashss.se.ascendnashville.activityTest;

import com.nashss.se.ascendnashville.activity.CreateEventActivity;
import com.nashss.se.ascendnashville.activity.requests.CreateEventRequest;
import com.nashss.se.ascendnashville.activity.results.CreateEventResult;
import com.nashss.se.ascendnashville.dynamoDB.EventDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateEventActivityTest {

    @Mock
    private EventDao eventDao;

    private CreateEventActivity createEventActivity;

    @BeforeEach
            public void setUp() {
        openMocks(this);
        createEventActivity = new CreateEventActivity(eventDao);
    }

    @Test
    public void handleRequest_withEventId_createsAndSavesEvent() {
        // GIVEN
        String expectedEventId = "expectedEventId";
        String expectedDate = "expectedDate";
        String expectedEventDetails = "expectedEventDetails";

        CreateEventRequest request = CreateEventRequest.builder()
                .withEventId(expectedEventId)
                .withDate(expectedDate)
                .withEventDetails(expectedEventDetails)
                .build();

        // WHEN
        CreateEventResult result = createEventActivity.handleRequest(request);

        // THEN
        verify(eventDao).saveEvent(ArgumentMatchers.any(Event.class));

        Assertions.assertNotNull(result.getEvent().getEventId());
        Assertions.assertEquals(expectedDate, result.getEvent().getDate());
        Assertions.assertEquals(expectedEventDetails, result.getEvent().getEventDetails());
    }
}
