package com.nashss.se.ascendnashville.dynamoDB;

import com.nashss.se.ascendnashville.Exceptions.RouteNotFoundException;
import com.nashss.se.ascendnashville.dynamoDB.models.Route;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

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

    public Route getRoute(String routeId) {
        Route route = this.dynamoDBMapper.load(Route.class, routeId);

        if (route == null) {
            throw new RouteNotFoundException("Could not find route with id " + routeId);
        }

        return route;
    }

    public Route saveRoute(Route route) {
        this.dynamoDBMapper.save(route);
        return route;
    }
}

