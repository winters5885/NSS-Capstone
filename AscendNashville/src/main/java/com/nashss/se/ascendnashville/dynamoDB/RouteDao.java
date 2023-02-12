package com.nashss.se.ascendnashville.dynamoDB;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.nashss.se.ascendnashville.Exceptions.RouteNotFoundException;
import com.nashss.se.ascendnashville.dynamoDB.models.Route;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Accesses data for a playlist using {@link RouteDao} to represent the model in DynamoDB.
 */
@Singleton
public class RouteDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public RouteDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public List<Route> getRoutes(String routeId) {
        Route route = new Route();
        route.setRouteId(routeId);

        DynamoDBQueryExpression<Route> queryExpression = new DynamoDBQueryExpression<Route>()
                .withHashKeyValues(route);
        return dynamoDBMapper.query(Route.class, queryExpression);
    }

    public Route saveRoute(Route route) {
        this.dynamoDBMapper.save(route);
        return route;
    }
}

