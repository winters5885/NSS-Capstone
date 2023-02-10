package com.nashss.se.ascendnashville.models;

import java.util.Objects;

/**
 * RouteModel Class.
 */
public class RouteModel {

    private final String routeId;
    private final String difficultyRating;
    private final String routeType;
    private final Integer memberRating;

    private RouteModel(String routeId, String difficultyRating, String routeType, Integer memberRating) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteModel that = (RouteModel) o;
        return Objects.equals(routeId, that.routeId) && Objects.equals(difficultyRating, that.difficultyRating) && Objects.equals(routeType, that.routeType) && Objects.equals(memberRating, that.memberRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeId, difficultyRating, routeType, memberRating);
    }

    //CHECKSTYLE:OFF:Builder
    public static RouteModel.Builder builder() {
        return new RouteModel.Builder();
    }

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

        public Builder withMemberRating(Integer memberRating) {
            this.memberRating = memberRating;
            return this;
        }

        public RouteModel build() {
            return new RouteModel(routeId, difficultyRating, routeType, memberRating);
        }
    }
}
