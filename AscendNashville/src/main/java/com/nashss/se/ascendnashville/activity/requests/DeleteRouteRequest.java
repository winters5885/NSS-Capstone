package com.nashss.se.ascendnashville.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * DeleteRouteResult Class.
 */
@JsonDeserialize(builder = DeleteRouteRequest.Builder.class)
public class DeleteRouteRequest {
    private final String routeId;

    /**
     * Instantiates a new DeleteRouteRequest object.
     *
     * @param routeId Route ID associated with a particular event.
     */
    private DeleteRouteRequest(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteId() {
        return routeId;
    }

    @Override
    public String toString() {
        return "DeleteRouteRequest{" +
                "routeId='" + routeId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static DeleteRouteRequest.Builder builder() {
        return new DeleteRouteRequest.Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String routeId;

        public DeleteRouteRequest.Builder withRouteId(String routeId) {
            this.routeId = routeId;
            return this;
        }

        public DeleteRouteRequest build() {
            return new DeleteRouteRequest(routeId);
        }
    }
}
