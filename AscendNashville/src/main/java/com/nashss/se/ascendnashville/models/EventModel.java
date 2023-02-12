package com.nashss.se.ascendnashville.models;

import java.util.Objects;

/**
 * EventModel.
 */
public class EventModel {

    private final String eventId;
    private final String date;
    private final String eventDetails;

    private EventModel(String eventId, String date, String eventDetails) {
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
                Objects.equals(eventDetails, that.eventDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, date, eventDetails);
    }

    //CHECKSTYLE:OFF:Builder
    public static EventModel.Builder builder() { return new EventModel.Builder();}

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

        public EventModel build() { return new EventModel(eventId, date, eventDetails);}
    }
}
