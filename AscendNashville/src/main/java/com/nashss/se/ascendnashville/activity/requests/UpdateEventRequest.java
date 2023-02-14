package com.nashss.se.ascendnashville.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdateEventRequest.Builder.class)
public class UpdateEventRequest {
    private final String eventId;
    private final String date;
    private final String eventDetails;

    private UpdateEventRequest(String eventId, String date, String eventDetails) {
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
        return "UpdateEventRequest{" +
                "eventId='" + eventId + '\'' +
                ", date='" + date + '\'' +
                ", eventDetails='" + eventDetails + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String eventId;
        private String date;
        private String eventDetails;

        public Builder withEventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        public Builder withEventDetails(String eventDetails) {
            this.eventDetails = eventDetails;
            return this;
        }

        public UpdateEventRequest build() {
            return new UpdateEventRequest(eventId, date, eventDetails);
        }
    }
}
