package com.nashss.se.ascendnashville.activity.results;

import com.nashss.se.ascendnashville.models.EventModel;

import java.util.ArrayList;
import java.util.List;

/**
 * GetEventResult Class.
 */
public class GetEventResult {
    private final List<EventModel> eventsList;

    /**
     * Constructor for GetEventResult.
     *
     * @param eventsList to covert.
     */
    private GetEventResult(List<EventModel> eventsList) {
        this.eventsList = eventsList;
    }

    public List<EventModel> getEventsList() {
        return eventsList;
    }

    @Override
    public String toString() {
        return "GetEventResult{" +
                "eventsList=" + eventsList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static GetEventResult.Builder builder() {return new GetEventResult.Builder();}

    public static class Builder {
        private List<EventModel> eventsList;

        public Builder withEventsList(List<EventModel> eventsList) {
            this.eventsList = new ArrayList<>(eventsList);
            return this;
        }

        public GetEventResult build() { return new GetEventResult(eventsList);}
    }
}
