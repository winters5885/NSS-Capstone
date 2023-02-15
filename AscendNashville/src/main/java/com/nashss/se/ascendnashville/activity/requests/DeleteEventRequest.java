package com.nashss.se.ascendnashville.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * DeleteEventResult Class.
 */
@JsonDeserialize(builder = DeleteEventRequest.Builder.class)
public class DeleteEventRequest {
    private final String eventId;
    private final String date;
    private final String eventDetails;

    /**
     * Instantiates a new DeleteEventRequest object.
     *
     * @param eventId Event ID associated with a particular event.
     * @param date Date for the event.
     * @param eventDetails Details about the event.
     */
    private DeleteEventRequest(String eventId, String date, String eventDetails) {
        this.eventId = eventId;
        this.date = date;
        this.eventDetails = eventDetails;
    }

    public String getEventId() {
        return eventId;
    }

    public String getDate() {
        return date;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    @Override
    public String toString() {
        return "DeleteEventRequest{" +
                "eventId='" + eventId + '\'' +
                ", date='" + date + '\'' +
                ", eventDetails='" + eventDetails + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static DeleteEventRequest.Builder builder() {
        return new DeleteEventRequest.Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String eventId;
        private String date;
        private String eventDetails;

        public DeleteEventRequest.Builder withEventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        public DeleteEventRequest.Builder withDate(String date) {
            this.date = date;
            return this;
        }

        public DeleteEventRequest.Builder withEventDetails(String eventDetails) {
            this.eventDetails = eventDetails;
            return this;
        }

        public DeleteEventRequest build() {
            return new DeleteEventRequest(eventId, date, eventDetails);
        }
    }
}
