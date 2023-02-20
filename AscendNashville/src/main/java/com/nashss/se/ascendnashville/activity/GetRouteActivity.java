package com.nashss.se.ascendnashville.activity;

import com.nashss.se.ascendnashville.activity.results.GetRouteResult;
import com.nashss.se.ascendnashville.converters.ModelConverter;
import com.nashss.se.ascendnashville.dynamoDB.RouteDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Route;
import com.nashss.se.ascendnashville.models.RouteModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import javax.inject.Inject;


/**
 * Implementation of the GetRouteActivity for Ascend Nashville's GetRoute API.
 * <p>
 * This API allows the customer to retrieve the routes.
 */
public class GetRouteActivity {
    private final Logger log = LogManager.getLogger();
    private final RouteDao routeDao;

    /**
     * Instantiates a new GetRouteActivity object.
     *
     * @param routeDao RouteDao to access the route table.
     */
    @Inject
    public GetRouteActivity(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    /**
     * This method handles the incoming request by retrieving the routes from the database.
     * <p>
     * It then returns the routes.
     * <p>
     * @return getRouteResult result object containing the API defined {@link RouteModel}
     */
    public GetRouteResult handleRequest() {
        log.info("In the GetRouteActivity handleRequest.");
        List<Route> routes = routeDao.getRoutes();
        List<RouteModel> routeModels = new ModelConverter().toRoutesModelList(routes);
        return GetRouteResult.builder()
                .withRouteList(routeModels)
                .build();
    }
}
