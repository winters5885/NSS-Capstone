package com.nashss.se.ascendnashville.activityTest;

import com.nashss.se.ascendnashville.activity.GetAllEventsActivity;
import com.nashss.se.ascendnashville.activity.results.GetAllEventsResult;

import com.nashss.se.ascendnashville.dynamoDB.EventDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetAllEventsActivityTest {
    @Mock
    private EventDao eventDao;

    private GetAllEventsActivity getAllEventsActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getAllEventsActivity = new GetAllEventsActivity(eventDao);
    }

    @Test
    void getEvents_scansDynamoDbEventTable_returnsListOfEvents() {
        // GIVEN
        Event event1 = new Event("eventId1", "eventDate1", "eventDetails1");
        Event event2 = new Event("eventId2", "eventDate2", "eventDetails2");
        Event event3 = new Event("eventId3", "eventDate3", "eventDetails3");

        List<Event> eventsList = new ArrayList<>();
        eventsList.add(event1);
        eventsList.add(event2);
        eventsList.add(event3);

        when(eventDao.getEvents()).thenReturn(eventsList);

        // WHEN
        GetAllEventsResult result = getAllEventsActivity.handleRequest();

        // THEN
        assertEquals(3, result.getEventsList().size());
        assertEquals(event1.getEventId(), result.getEventsList().get(0).getEventId());
        assertEquals(event1.getDate(), result.getEventsList().get(0).getDate());
        assertEquals(event1.getEventDetails(), result.getEventsList().get(0).getEventDetails());

        assertEquals(event2.getEventId(), result.getEventsList().get(1).getEventId());
        assertEquals(event2.getDate(), result.getEventsList().get(1).getDate());
        assertEquals(event2.getEventDetails(), result.getEventsList().get(1).getEventDetails());

        assertEquals(event3.getEventId(), result.getEventsList().get(2).getEventId());
        assertEquals(event3.getDate(), result.getEventsList().get(2).getDate());
        assertEquals(event3.getEventDetails(), result.getEventsList().get(2).getEventDetails());
    }

    @Test
    public void handleRequest_savedEventFound_returnsEvent() {
        // GIVEN
        String eventId = "expectedId";
        String date = "expectedDate";
        String eventDetails = "expectedEventDetails";

        Event event = new Event();
        event.setEventId(eventId);
        event.setDate(date);
        event.setEventDetails(eventDetails);

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);

        when(eventDao.getEvents()).thenReturn(eventList);

        // WHEN
        GetAllEventsResult result = getAllEventsActivity.handleRequest();

        // THEN
        Assertions.assertEquals(eventId, result.getEventsList().get(0).getEventId());
        Assertions.assertEquals(date, result.getEventsList().get(0).getDate());
        Assertions.assertEquals(eventDetails, result.getEventsList().get(0).getEventDetails());
    }
}
