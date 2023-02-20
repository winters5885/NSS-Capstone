package com.nashss.se.ascendnashville.dynamoDBTest;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.nashss.se.ascendnashville.Exceptions.EventNotFoundException;
import com.nashss.se.ascendnashville.Exceptions.MemberNotFoundException;
import com.nashss.se.ascendnashville.dynamoDB.EventDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Event;
import com.nashss.se.ascendnashville.dynamoDB.models.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class EventDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    private EventDao eventDao;

    @Mock
    PaginatedScanList<Event> scanResult;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        eventDao = new EventDao(dynamoDBMapper);
    }

    @Test
    public void saveEvent_callsMapperWithEvent() {
        // GIVEN
        Event event = new Event();

        // WHEN
        Event result = eventDao.saveEvent(event);

        // THEN
        verify(dynamoDBMapper).save(event);
        Assertions.assertEquals(event, result);
    }

    @Test
    void getEvents_scansDynamoDbEventTable_returnsEvents() {
        // GIVEN
        when(dynamoDBMapper.scan(eq(Event.class), any())).thenReturn(scanResult);

        ArgumentCaptor<DynamoDBScanExpression> scanExpressionArgumentCaptor =
                ArgumentCaptor.forClass(DynamoDBScanExpression.class);

        // WHEN
        List<Event> events = eventDao.getEvents();

        // THEN
        verify(dynamoDBMapper).scan(eq(Event.class), scanExpressionArgumentCaptor.capture());
        DynamoDBScanExpression scanExpression = scanExpressionArgumentCaptor.getValue();

        verify(dynamoDBMapper).scan(Event.class, scanExpression);

        Assertions.assertEquals(scanResult, events, "Expected method to return the results of the scan.");
    }

    @Test
    public void getEvent_eventIdNotFound_throwsEventNotFoundException() {
        // GIVEN
        String nonexistentEventId = "nonExistentEventId";
        when(dynamoDBMapper.load(Member.class, nonexistentEventId)).thenReturn(null);

        // WHEN + THEN
        assertThrows(EventNotFoundException.class, () -> eventDao.getEvent(nonexistentEventId));
    }
}
