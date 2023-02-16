package com.nashss.se.ascendnashville.activity;

import com.nashss.se.ascendnashville.activity.requests.DeleteEventRequest;
import com.nashss.se.ascendnashville.activity.requests.DeleteRouteRequest;
import com.nashss.se.ascendnashville.activity.results.DeleteEventResult;
import com.nashss.se.ascendnashville.activity.results.DeleteRouteResult;
import com.nashss.se.ascendnashville.converters.ModelConverter;
import com.nashss.se.ascendnashville.dynamoDB.EventDao;
import com.nashss.se.ascendnashville.dynamoDB.RouteDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Event;
import com.nashss.se.ascendnashville.dynamoDB.models.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class DeleteRouteActivity {

    private final Logger log = LogManager.getLogger();
    private final RouteDao routeDao;

    /**
     * Instantiates a new DeleteRouteActivity object.
     *
     * @param routeDao RouteDao to access the route table.
     */
    @Inject
    public DeleteRouteActivity (RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    /**
     * This method handles the incoming request by deleting the route in the route table.
     * <p>
     * It then returns the deleted route.
     * <p>
     * If the route does not exist, this should throw a RouteNotFoundException.
     * <p>
     * If the provided eventId has invalid characters, throws an
     * InvalidAttributeValueException
     * <p>
     * If the request tries to update the routeId,
     * this should throw an InvalidAttributeChangeException
     *
     * @param deleteRouteRequest request object containing the event ID
     *                              associated with it
     */
    public DeleteRouteResult handleRequest(final DeleteRouteRequest deleteRouteRequest) {
        log.info("Inside DeleteRouteActivity handleRequest.");
        Route route = routeDao.getRoute(deleteRouteRequest.getRouteId());

        route.setRouteId(deleteRouteRequest.getRouteId());
        routeDao.deleteRoute(route);

        return DeleteRouteResult.builder()
                .withRoute(new ModelConverter().toRouteModel(route))
                .build();
    }
}
