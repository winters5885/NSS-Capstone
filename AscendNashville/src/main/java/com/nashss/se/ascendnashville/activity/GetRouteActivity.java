package com.nashss.se.ascendnashville.activity;

import com.nashss.se.ascendnashville.activity.requests.GetRouteRequest;
import com.nashss.se.ascendnashville.activity.results.GetRouteResult;
import com.nashss.se.ascendnashville.converters.ModelConverter;
import com.nashss.se.ascendnashville.dynamoDB.RouteDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Route;
import com.nashss.se.ascendnashville.models.RouteModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the CreatePlaylistActivity for the MusicPlaylistService's CreatePlaylist API.
 * <p>
 * This API allows the customer to create a new playlist with no songs.
 */
public class GetRouteActivity {

    private final Logger log = LogManager.getLogger();

    private final RouteDao routeDao;

    @Inject
    public GetRouteActivity(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    public GetRouteResult handleRequest(final GetRouteRequest getRouteRequest) {
        log.info("In the GetRouteActivity handleRequest.");
        String requestedRouteId = getRouteRequest.getRouteId();
        Route route = routeDao.getRoute(requestedRouteId);
        RouteModel routeModel = new ModelConverter().toRouteModel(route);

        return GetRouteResult.builder()
                .withRoute(routeModel)
                .build();
    }
}
