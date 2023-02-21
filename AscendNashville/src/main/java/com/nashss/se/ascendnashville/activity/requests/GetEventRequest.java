package com.nashss.se.ascendnashville.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Date;

/**
 * Instantiates a new GetEventRequest object.
 *
 */
@JsonDeserialize(builder = GetEventRequest.Builder.class)
public class GetEventRequest {

    private final String eventId;
    private final Date date;
    private final String eventDetails;

    /**
     * Instantiates a new GetEventRequest object.
     *
     * @param eventId A eventId tied to the event.
     * @param date Date the event will take place.
     * @param eventDetails Details of the event.
     */
    private GetEventRequest(String eventId, Date date, String eventDetails) {
        this.eventId = eventId;
        this.date = date;
        this.eventDetails = eventDetails;
    }

    public String getEventId() {
        return eventId;
    }

    public Date getDate() {
        return date;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    @Override
    public String toString() {
        return "GetEventRequest{" +
                "eventId='" + eventId + '\'' +
                ", date=" + date +
                ", eventDetails='" + eventDetails + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static GetEventRequest.Builder builder() {return new GetEventRequest.Builder();}

    @JsonPOJOBuilder
    public static class Builder {
        private String eventId;
        private Date date;
        private String eventDetails;

        public GetEventRequest.Builder withEventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        public GetEventRequest.Builder withDate(Date date) {
            this.date = date;
            return this;
        }

        public GetEventRequest.Builder withEventDetails(String eventDetails) {
            this.eventDetails = eventDetails;
            return this;
        }

        public GetEventRequest build() {

            return new GetEventRequest(eventId, date, eventDetails);
        }
    }
}
