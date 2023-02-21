package com.nashss.se.ascendnashville.activity.results;

import com.nashss.se.ascendnashville.models.EventModel;

import java.util.ArrayList;
import java.util.List;

/**
 * GetAllEventsResult Class.
 */
public class GetAllEventsResult {
    private final List<EventModel> eventsList;

    /**
     * Constructor for GetAllEventsResult.
     *
     * @param eventsList to covert.
     */
    private GetAllEventsResult(List<EventModel> eventsList) {
        this.eventsList = eventsList;
    }

    public List<EventModel> getEventsList() {
        return eventsList;
    }

    @Override
    public String toString() {
        return "GetAllEventsResult{" +
                "eventsList=" + eventsList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static GetAllEventsResult.Builder builder() {return new GetAllEventsResult.Builder();}

    public static class Builder {
        private List<EventModel> eventsList;

        public Builder withEventsList(List<EventModel> eventsList) {
            this.eventsList = new ArrayList<>(eventsList);
            return this;
        }

        public GetAllEventsResult build() { return new GetAllEventsResult(eventsList);}
    }
}
