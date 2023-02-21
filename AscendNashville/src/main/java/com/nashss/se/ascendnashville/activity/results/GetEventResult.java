package com.nashss.se.ascendnashville.activity.results;

import com.nashss.se.ascendnashville.models.EventModel;

/**
 * GetEventResult Class.
 */
public class GetEventResult {
    private final EventModel eventModel;

    /**
     * Constructor for GetEventResult.
     *
     * @param eventModel to covert.
     */
    private GetEventResult(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    public EventModel getEvent() {
        return eventModel;
    }

    @Override
    public String toString() {
        return "GetEventResult{" +
                "eventModel=" + eventModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static GetEventResult.Builder builder() {return new GetEventResult.Builder();}

    public static class Builder {
        private EventModel eventModel;

        public Builder withEvent(EventModel eventModel) {
            this.eventModel = eventModel;
            return this;
        }

        public GetEventResult build() { return new GetEventResult(eventModel);}
    }
}
