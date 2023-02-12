package com.nashss.se.ascendnashville.dynamoDB;

import com.nashss.se.ascendnashville.dynamoDB.models.Route;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.nashss.se.ascendnashville.dynamoDB.models.Route.Route_Difficulty_GSI;

/**
 * Accesses data for a route using {@link RouteDao} to represent the model in DynamoDB.
 */
@Singleton
public class RouteDao {
    private final DynamoDBMapper dynamoDBMapper;
    private final Logger log = LogManager.getLogger();

    /**
     * Instantiates a RouteDao object.
     *
     * @param dynamoDBMapper   the {@link DynamoDBMapper} used to interact with the route table
     */
    @Inject
    public RouteDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    /**
     * Retrieves all routes in route table.
     * <p>
     * If not found, throws RouteNotFoundException.
     *
     * @return All routes in route table
     */
    public List<Route> getRoutes() {
        log.info("Inside RouteDao getRoutes");

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(Route.class, scanExpression);
    }

    /**
     * Retrieves all routes in route table filtered by difficulty rating.
     * <p>
     * If not found, throws RouteNotFoundException.
     *
     * @param difficultyRating difficulty rating for a particular route.
     * @return List of routes from the GSI route table
     */
    public List<Route> getRoutesByDifficulty(String difficultyRating) {
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

    /**
     * Saves (creates or updates) the given route.
     *
     * @param route The route to save
     * @return The Route object that was saved
     */
    public Route saveRoute(Route route) {
        this.dynamoDBMapper.save(route);
        return route;
    }
}

