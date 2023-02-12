package com.nashss.se.ascendnashville.dynamoDB;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.ascendnashville.Exceptions.RouteNotFoundException;
import com.nashss.se.ascendnashville.dynamoDB.models.Route;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nashss.se.ascendnashville.dynamoDB.models.Route.Route_Difficulty_GSI;

/**
 * Accesses data for a playlist using {@link RouteDao} to represent the model in DynamoDB.
 */
@Singleton
public class RouteDao {
    private final DynamoDBMapper dynamoDBMapper;
    private final Logger log = LogManager.getLogger();

    @Inject
    public RouteDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

//    public List<Route> getRoutes(String routeId) {
//        Route route = new Route();
//        route.setRouteId(routeId);
//
//        DynamoDBQueryExpression<Route> queryExpression = new DynamoDBQueryExpression<Route>()
//                .withHashKeyValues(route);
//        return dynamoDBMapper.query(Route.class, queryExpression);
//    }

    public List<Route> getRoutes() {
        log.info("Inside RouteDao getRoutes");

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(Route.class, scanExpression);
    }
    public List<Route> getRoutesByDifficulty(String routeId) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":routeId", new AttributeValue().withS("routeId"));
        valueMap.put(":difficultyRating", new AttributeValue().withS("difficultyRating"));

        DynamoDBQueryExpression<Route> queryExpression = new DynamoDBQueryExpression<Route>()
                .withIndexName(Route_Difficulty_GSI)
                .withConsistentRead(false)
                .withKeyConditionExpression("routeId = :routeId and difficultyRating = :difficultyRating")
                .withFilterExpression("difficultyRating = :difficultyRating")
                .withExpressionAttributeValues(valueMap);

        return dynamoDBMapper.query(Route.class, queryExpression);
    }

    public Route saveRoute(Route route) {
        this.dynamoDBMapper.save(route);
        return route;
    }
}

