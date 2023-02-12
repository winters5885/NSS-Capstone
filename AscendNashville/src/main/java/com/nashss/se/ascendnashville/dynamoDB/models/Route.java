package com.nashss.se.ascendnashville.dynamoDB.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

/**
 * Represents a record in the route table.
 */
@DynamoDBTable(tableName = "route")
public class Route {
    public static final String Route_Difficulty_GSI = "RouteDifficultyGSI";
    private String routeId;
    private String difficultyRating;
    private String routeType;
    private Integer memberRating;

    /**
     * Empty constructor for Route POJO.
     */
    public Route() {}

    /**
     * Non-empty constructor for Route POJO.
     * @param routeId randomized numeric six digit string
     * @param difficultyRating difficulty rating for the route
     * @param routeType type of route
     * @param memberRating rating provided by member input
     */
    public Route(String routeId, String difficultyRating, String routeType, Integer memberRating) {
        this.routeId = routeId;
        this.difficultyRating = difficultyRating;
        this.routeType = routeType;
        this.memberRating = memberRating;
    }

    @DynamoDBHashKey(attributeName = "routeId")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = Route_Difficulty_GSI, attributeName = "routeId")
    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    @DynamoDBAttribute(attributeName = "difficultyRating")
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = Route_Difficulty_GSI, attributeName = "difficultyRating")
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Route route = (Route) o;
        return Objects.equals(routeId, route.routeId) &&
                Objects.equals(difficultyRating, route.difficultyRating) &&
                Objects.equals(routeType, route.routeType) &&
                Objects.equals(memberRating, route.memberRating);
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
