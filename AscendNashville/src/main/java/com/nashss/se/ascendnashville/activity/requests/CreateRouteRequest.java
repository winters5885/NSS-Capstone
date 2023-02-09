package com.nashss.se.ascendnashville.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateRouteRequest.class)
public class CreateRouteRequest {
    private final String routeId;
    private final String difficultyRating;
    private final String routeType;
    private final Integer memberRating;

    public CreateRouteRequest (String routeId, String difficultyRating,
                               String routeType, Integer memberRating) {
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
        return "CreateRouteRequest{" +
                "routeId='" + routeId + '\'' +
                ", difficultyRating='" + difficultyRating + '\'' +
                ", routeType='" + routeType + '\'' +
                ", memberRating=" + memberRating +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static CreateMemberRequest.Builder builder() {
        return new CreateMemberRequest.Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String routeId;
        private String difficultyRating;
        private String routeType;
        private Integer memberRating;

        public Builder withRouteId(String routeId) {
            this.routeId = routeId;
            return this;
        }

        public Builder withDifficultyRating(String difficultyRating) {
            this.difficultyRating = difficultyRating;
            return this;
        }

        public Builder withRouteType(String routeType) {
            this.routeType = routeType;
            return this;
        }

        public Builder withMemberRating(Integer memberRating)  {
            this.memberRating = memberRating;
            return this;
        }

        public CreateRouteRequest build() {
            return new CreateRouteRequest(routeId, difficultyRating, routeType, memberRating);
        }
    }
}
