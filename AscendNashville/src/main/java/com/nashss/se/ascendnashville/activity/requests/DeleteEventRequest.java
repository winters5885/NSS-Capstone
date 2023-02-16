package com.nashss.se.ascendnashville.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * DeleteEventResult Class.
 */
@JsonDeserialize(builder = DeleteEventRequest.Builder.class)
public class DeleteEventRequest {
    private final String eventId;

    /**
     * Instantiates a new DeleteEventRequest object.
     *
     * @param eventId Event ID associated with a particular event.
     */
    private DeleteEventRequest(String eventId) {
        this.eventId = eventId;
    }

    public String getEventId() {
        return eventId;
    }

    @Override
    public String toString() {
        return "DeleteEventRequest{" +
                "eventId='" + eventId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static DeleteEventRequest.Builder builder() {
        return new DeleteEventRequest.Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String eventId;

        public DeleteEventRequest.Builder withEventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        public DeleteEventRequest build() {
            return new DeleteEventRequest(eventId);
        }
    }
}
