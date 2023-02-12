package com.nashss.se.ascendnashville.dynamoDB;

import com.nashss.se.ascendnashville.dynamoDB.models.Event;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import javax.inject.Inject;

/**
 * Accesses data for an album using {@link EventDao} to represent the model in DynamoDB.
 */
public class EventDao {
    private final DynamoDBMapper dynamoDBMapper;
    private final Logger log = LogManager.getLogger();

    /**
     * Instantiates an EventDao object.
     *
     * @param dynamoDBMapper   the {@link DynamoDBMapper} used to interact with the event table
     */

    @Inject
    public EventDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    /**
     * Retrieves all events in event table.
     * <p>
     * If not found, throws EventNotFoundException.
     *
     * @return All events in event table
     */

    public List<Event> getEvents() {
        log.info("Inside EventDao getEvents.");

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(Event.class, scanExpression);
    }

    /**
     * Saves (creates or updates) the given event.
     *
     * @param event The event to save
     * @return The Event object that was saved
     */
    public Event saveEvent(Event event) {
        this.dynamoDBMapper.save(event);
        return event;
    }
}
