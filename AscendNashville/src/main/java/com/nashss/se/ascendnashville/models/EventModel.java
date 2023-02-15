package com.nashss.se.ascendnashville.models;

import java.util.Objects;

/**
 * EventModel.
 */
public class EventModel {

    private final String eventId;
    private final String date;
    private final String eventDetails;
    private final Boolean deleted;

    private EventModel(String eventId, String date, String eventDetails, Boolean deleted) {
        this.eventId = eventId;
        this.date = date;
        this.eventDetails = eventDetails;
        this.deleted = deleted;
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

    public Boolean getDeleted() {
        return deleted;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EventModel that = (EventModel) o;
        return Objects.equals(eventId, that.eventId) &&
                Objects.equals(date, that.date) &&
                Objects.equals(eventDetails, that.eventDetails) &&
                Objects.equals(deleted, that.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, date, eventDetails, deleted);
    }

    //CHECKSTYLE:OFF:Builder
    public static EventModel.Builder builder() { return new EventModel.Builder();}

    public static class Builder {
        private String eventId;
        private String date;
        private String eventDetails;
        private Boolean deleted;

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

        public Builder withDeleteStatus(Boolean deleted) {
            this.deleted = deleted;
            return this;
        }

        public EventModel build() { return new EventModel(eventId, date, eventDetails, deleted);}
    }
}
