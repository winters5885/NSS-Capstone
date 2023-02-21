package com.nashss.se.ascendnashville.activity;

import com.nashss.se.ascendnashville.Exceptions.InvalidAttributeValueException;
import com.nashss.se.ascendnashville.activity.requests.CreateRouteRequest;
import com.nashss.se.ascendnashville.activity.results.CreateRouteResult;
import com.nashss.se.ascendnashville.converters.ModelConverter;
import com.nashss.se.ascendnashville.dynamoDB.RouteDao;

import com.nashss.se.ascendnashville.dynamoDB.models.Route;

import com.nashss.se.ascendnashville.models.RouteModel;

import com.nashss.se.ascendnashville.utils.AscendNashvilleUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the CreateRouteActivity for Ascend Nashville's CreateRoute API.
 * <p>
 * This API allows the admin to create a new route with routeID, difficulty rating,
 * route type, and member rating.
 */
public class CreateRouteActivity {
    private final Logger log = LogManager.getLogger();
    private final RouteDao routeDao;

    /**
     * Instantiates a new CreateRouteActivity object.
     *
     * @param routeDao RouteDao to access the route table.
     */
    @Inject
    public CreateRouteActivity(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    /**
     * This method handles the incoming request by persisting a new route
     * with the provided routeId, difficulty rating, route type, and member rating
     * from the request.
     * <p>
     * It then returns the newly created route.
     * <p>
     * If the provided routeId, difficulty rating, route type, or member rating
     * has invalid characters, throws an InvalidAttributeValueException.
     *
     * @param createRouteRequest request object containing the routeId, difficulty
     *                           rating, route type, and member rating
     *                           associated with it.
     * @return createRouteResult result object containing the API defined {@link RouteModel}
     */
    public CreateRouteResult handleRequest(final CreateRouteRequest createRouteRequest) {
        log.info("In the CreateRouteActivity handleRequest.");

        if (!AscendNashvilleUtils.isValidString(createRouteRequest.getRouteId())) {
            throw new InvalidAttributeValueException("RouteId: [" + createRouteRequest.getRouteId() +
                    "} contains illegal characters");
        }

        if (!AscendNashvilleUtils.isValidString(createRouteRequest.getDifficultyRating())) {
            throw new InvalidAttributeValueException("Route Difficulty Rating [" +
                    createRouteRequest.getDifficultyRating() + "] contains illegal characters");
        }

        if (!AscendNashvilleUtils.isValidString(createRouteRequest.getRouteType())) {
            throw new InvalidAttributeValueException("Route type [" + createRouteRequest.getRouteType() +
                    "} contains illegal characters");
        }

        Route newRoute = new Route();
        newRoute.setRouteId(createRouteRequest.getRouteId());
        newRoute.setDifficultyRating(createRouteRequest.getDifficultyRating());
        newRoute.setRouteType(createRouteRequest.getRouteType());
        newRoute.setMemberRating(createRouteRequest.getMemberRating());

        routeDao.saveRoute(newRoute);

        RouteModel routeModel = new ModelConverter().toRouteModel(newRoute);
        return CreateRouteResult.builder()
                .withRoute(routeModel)
                .build();
    }
}
