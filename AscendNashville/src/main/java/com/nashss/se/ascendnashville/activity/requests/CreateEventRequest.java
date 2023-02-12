package com.nashss.se.ascendnashville.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Date;

/**
 * Instantiates a new CreateMemberRequest object.
 *
 */
@JsonDeserialize(builder = CreateEventRequest.Builder.class)
public class CreateEventRequest {
    private final String eventId;
    private final Date date;
    private final String eventDetails;

    /**
     * Instantiates a new CreateEventRequest object.
     *
     * @param eventId An eventId tied to an event.
     * @param date The date of the event.
     * @param eventDetails Event details submitted by user.
     */
    public CreateEventRequest(String eventId, Date date, String eventDetails) {
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
        return "CreateEventRequest{" +
                "eventId='" + eventId + '\'' +
                ", date=" + date +
                ", eventDetails='" + eventDetails + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder{
        private String eventId;
        private Date date;
        private String eventDetails;

        public Builder withEventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder withDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder withEventDetails(String eventDetails) {
            this.eventDetails = eventDetails;
            return this;
        }

        public CreateEventRequest build() {
            return new CreateEventRequest(eventId, date, eventDetails);
        }
    }
}
