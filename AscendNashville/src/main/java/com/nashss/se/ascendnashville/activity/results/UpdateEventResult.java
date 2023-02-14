package com.nashss.se.ascendnashville.activity.results;

import com.nashss.se.ascendnashville.models.EventModel;

public class UpdateEventResult {
    private final EventModel eventModel;

    private UpdateEventResult(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    public EventModel getEventModel() {
        return eventModel;
    }

    @Override
    public String toString() {
        return "UpdateEventResult{" +
                "eventModel=" + eventModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private EventModel event;

        public Builder withEvent(EventModel event) {
            this.event = event;
            return this;
        }

        public UpdateEventResult build() {
            return new UpdateEventResult(event);
        }
    }
}
