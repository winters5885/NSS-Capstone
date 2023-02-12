package com.nashss.se.ascendnashville.dynamoDB.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Date;
import java.util.Objects;

/**
 * Represents a record in the event table.
 */
@DynamoDBTable(tableName = "event")
public class Event {
    private String eventId;
    private Date date;
    private String eventDetails;

    /**
     * Empty constructor for Event POJO.
     */
    public Event() {}

    /**
     * Non-empty constructor for Event POJO.
     * @param eventId randomized numeric six digit string
     * @param date Date of the event.
     * @param eventDetails Details provided for the event.
     */
    public Event(String eventId, Date date, String eventDetails) {
        this.eventId = eventId;
        this.date = date;
        this.eventDetails = eventDetails;
    }

    @DynamoDBHashKey(attributeName = "eventId")
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @DynamoDBAttribute(attributeName = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @DynamoDBAttribute(attributeName = "eventDetails")
    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return Objects.equals(eventId, event.eventId) &&
                Objects.equals(date, event.date) &&
                Objects.equals(eventDetails, event.eventDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, date, eventDetails);
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId='" + eventId + '\'' +
                ", date=" + date +
                ", eventDetails='" + eventDetails + '\'' +
                '}';
    }
}
