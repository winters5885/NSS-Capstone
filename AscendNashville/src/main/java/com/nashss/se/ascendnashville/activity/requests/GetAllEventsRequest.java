package com.nashss.se.ascendnashville.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Date;

/**
 * Instantiates a new GetAllEventsRequest object.
 *
 */
@JsonDeserialize(builder = GetAllEventsRequest.Builder.class)
public class GetAllEventsRequest {

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
    private GetAllEventsRequest(String eventId, Date date, String eventDetails) {
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
        return "GetAllEventsRequest{" +
                "eventId='" + eventId + '\'' +
                ", date=" + date +
                ", eventDetails='" + eventDetails + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static GetAllEventsRequest.Builder builder() {return new GetAllEventsRequest.Builder();}

    @JsonPOJOBuilder
    public static class Builder {
        private String eventId;
        private Date date;
        private String eventDetails;

        public GetAllEventsRequest.Builder withEventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        public GetAllEventsRequest.Builder withDate(Date date) {
            this.date = date;
            return this;
        }

        public GetAllEventsRequest.Builder withEventDetails(String eventDetails) {
            this.eventDetails = eventDetails;
            return this;
        }

        public GetAllEventsRequest build() {
            return new GetAllEventsRequest(eventId, date, eventDetails);
        }
    }
}
