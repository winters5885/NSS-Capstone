package com.nashss.se.ascendnashville.activity.results;

import com.nashss.se.ascendnashville.models.EventModel;

/**
 * CreateEventResult Class.
 */
public class CreateEventResult {

    private final EventModel event;

    /**
     * Constructor for CreateEventResult.
     *
     * @param event to covert.
     */
    private CreateEventResult(EventModel event) {
        this.event = event;
    }

    public EventModel getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return "CreateEventResult{" +
                "event=" + event +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder();}

    public static class Builder {
        private EventModel event;

        public Builder withEvent(EventModel event) {
            this.event = event;
            return this;
        }

        public CreateEventResult build() { return new CreateEventResult(event);}
    }
}
