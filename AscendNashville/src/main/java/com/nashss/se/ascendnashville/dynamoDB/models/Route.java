package com.nashss.se.ascendnashville.dynamoDB.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

/**
 * Represents a record in the route table.
 */
@DynamoDBTable(tableName = "routes")
public class Route {
    private String routeId;
    private String difficultyRating;
    private String routeType;
    private Integer memberRating;

    public Route() {}

    public Route(String routeId, String difficultyRating, String routeType, Integer memberRating) {
        this.routeId = routeId;
        this.difficultyRating = difficultyRating;
        this.routeType = routeType;
        this.memberRating = memberRating;
    }

    @DynamoDBHashKey(attributeName = "routeId")
    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    @DynamoDBAttribute(attributeName = "difficultyRating")
    public String getDifficultyRating() {
        return difficultyRating;
    }

    public void setDifficultyRating(String difficultyRating) {
        this.difficultyRating = difficultyRating;
    }

    @DynamoDBAttribute(attributeName = "routeType")
    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    @DynamoDBAttribute(attributeName = "memberRating")
    public Integer getMemberRating() {
        return memberRating;
    }

    public void setMemberRating(Integer memberRating) {
        this.memberRating = memberRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(routeId, route.routeId) && Objects.equals(difficultyRating, route.difficultyRating) && Objects.equals(routeType, route.routeType) && Objects.equals(memberRating, route.memberRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeId, difficultyRating, routeType, memberRating);
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeId='" + routeId + '\'' +
                ", difficultyRating='" + difficultyRating + '\'' +
                ", routeType='" + routeType + '\'' +
                ", memberRating=" + memberRating +
                '}';
    }
}
