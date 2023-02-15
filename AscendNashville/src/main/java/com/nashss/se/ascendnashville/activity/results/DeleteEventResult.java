package com.nashss.se.ascendnashville.activity.results;

import com.nashss.se.ascendnashville.models.EventModel;

/**
 * DeleteEventResult Class.
 */
public class DeleteEventResult {
    private final EventModel eventModel;

    /**
     * Constructor for DeleteEventResult.
     *
     * @param eventModel to covert.
     */
    private DeleteEventResult(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    public EventModel getEventModel() {
        return eventModel;
    }

    @Override
    public String toString() {
        return "DeleteEventResult{" +
                "eventModel=" + eventModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static DeleteEventResult.Builder builder() {
        return new DeleteEventResult.Builder();
    }

    public static class Builder {
        private EventModel event;

        public DeleteEventResult.Builder withEvent(EventModel event) {
            this.event = event;
            return this;
        }

        public DeleteEventResult build() {
            return new DeleteEventResult(event);
        }
    }
}
