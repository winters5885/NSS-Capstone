package com.nashss.se.ascendnashville.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Instantiates a new GetRouteRequest object.
 *
 */
@JsonDeserialize(builder = GetRouteRequest.Builder.class)
public class GetRouteRequest {
    private final String routeId;
    private final String difficultyRating;
    private final String routeType;
    private final Integer memberRating;

    /**
     * Instantiates a new GetRouteRequest object.
     *
     * @param routeId A routeId tied to a route.
     * @param difficultyRating Rated difficulty of the route.
     * @param routeType Type of route.
     * @param memberRating Rating of route based on member input.
     */
    private GetRouteRequest(String routeId, String difficultyRating, String routeType, Integer memberRating) {
        this.routeId = routeId;
        this.difficultyRating = difficultyRating;
        this.routeType = routeType;
        this.memberRating = memberRating;
    }


    public String getRouteId() {
        return routeId;
    }

    public String getDifficultyRating() {
        return difficultyRating;
    }

    public String getRouteType() {
        return routeType;
    }

    public Integer getMemberRating() {
        return memberRating;
    }

    @Override
    public String toString() {
        return "GetRouteRequest{" +
                "routeId='" + routeId + '\'' +
                ", difficultyRating='" + difficultyRating + '\'' +
                ", routeType='" + routeType + '\'' +
                ", memberRating=" + memberRating +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static GetRouteRequest.Builder builder() {
        return new GetRouteRequest.Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String routeId;
        private String difficultyRating;
        private String routeType;
        private Integer memberRating;


        public GetRouteRequest.Builder withRouteId(String routeId) {
            this.routeId = routeId;
            return this;
        }

        public GetRouteRequest.Builder withDifficultyRating(String difficultyRating) {
            this.difficultyRating = difficultyRating;
            return this;
        }

        public GetRouteRequest.Builder withRouteType(String routeType) {
            this.routeType = routeType;
            return this;
        }

        public GetRouteRequest.Builder withMemberRating(Integer memberRating) {
            this.memberRating = memberRating;
            return this;
        }

        public GetRouteRequest build() {
            return new GetRouteRequest(routeId, difficultyRating, routeType, memberRating);
        }
    }
}
