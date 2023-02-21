package com.nashss.se.ascendnashville.activity;

import com.nashss.se.ascendnashville.Exceptions.EventNotFoundException;
import com.nashss.se.ascendnashville.activity.requests.DeleteRouteRequest;

import com.nashss.se.ascendnashville.activity.results.DeleteRouteResult;
import com.nashss.se.ascendnashville.converters.ModelConverter;

import com.nashss.se.ascendnashville.dynamoDB.RouteDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Route;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the DeleteRouteActivity for Ascend Nashville's DeleteRoute API.
 * <p>
 * This API allows the customer to delete a route.
 */
public class DeleteRouteActivity {

    private final Logger log = LogManager.getLogger();
    private final RouteDao routeDao;

    /**
     * Instantiates a new DeleteRouteActivity object.
     *
     * @param routeDao RouteDao to access the route table.
     */
    @Inject
    public DeleteRouteActivity(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    /**
     * This method handles the incoming request by deleting the route in the route table.
     * <p>
     * It then returns the deleted route.
     * <p>
     * If the route does not exist, this should throw a RouteNotFoundException.
     * <p>
     * @param deleteRouteRequest request object containing the event ID
     *                              associated with it
     * @return DeleteRouteResult
     */
    public DeleteRouteResult handleRequest(final DeleteRouteRequest deleteRouteRequest) {
        log.info("Inside DeleteRouteActivity handleRequest.");
        Route route = routeDao.getRoute(deleteRouteRequest.getRouteId());

        if (route == null) {
            throw new EventNotFoundException("No route exists associated with route Id:" +
                    deleteRouteRequest.getRouteId());
        }

        route.setRouteId(deleteRouteRequest.getRouteId());
        routeDao.deleteRoute(route);

        return DeleteRouteResult.builder()
                .withRoute(new ModelConverter().toRouteModel(route))
                .build();
    }
}
