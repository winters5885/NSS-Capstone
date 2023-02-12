package com.nashss.se.ascendnashville.activityTest;

import com.nashss.se.ascendnashville.activity.GetRouteActivity;
import com.nashss.se.ascendnashville.activity.requests.GetRouteRequest;
import com.nashss.se.ascendnashville.activity.results.GetRouteResult;
import com.nashss.se.ascendnashville.dynamoDB.RouteDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Route;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetRouteActivityTest {

    @Mock
    private RouteDao routeDao;
    private GetRouteActivity getRouteActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getRouteActivity = new GetRouteActivity(routeDao);
    }


//    @Test
//    void getRoutes_queriesDynamoDbRouteTable_returnsRoutes() {
//        //GIVEN
//        Route route1 = new Route("route1", "difficultyRating1", "routeType1", 1);
//        Route route2 = new Route("route2", "difficultyRating2", "routeType2", 1);
//        Route route3 = new Route("route3", "difficultyRating3", "routeType3", 1);
//
//        List<Route> routesList = new ArrayList<>();
//        routesList.add(route1);
//        routesList.add(route2);
//        routesList.add(route3);
//
//        when(routeDao.getRoutes()).thenReturn(routesList);
//
//        String testString = "[route1, route2, route3]";
//
//        GetRouteResult result = getRouteActivity.handleRequest();
//
//        assertEquals(3, result.getRoutes().size());
//        assertEquals(testString, result.getRoutes().toString());
//
//    }
    @Test
    public void handleRequest_savedRouteFound_returnsRoute() {
        // GIVEN
        String routeId = "expectedId";
        String difficultyRating = "expectedDifficultyRating";
        String routeType = "expectedRouteType";
        Integer memberRating = 3;

        Route firstRoute = new Route();
        firstRoute.setRouteId(routeId);
        firstRoute.setDifficultyRating(difficultyRating);
        firstRoute.setRouteType(routeType);
        firstRoute.setMemberRating(memberRating);

        List<Route> routesList = new ArrayList<>();
        routesList.add(firstRoute);

        when(routeDao.getRoutes()).thenReturn(routesList);

        // WHEN
        GetRouteResult result = getRouteActivity.handleRequest();

        // THEN
        Assertions.assertEquals(routeId, result.getRoutes().get(0).getRouteId());
        Assertions.assertEquals(difficultyRating, result.getRoutes().get(0).getDifficultyRating());
        Assertions.assertEquals(routeType, result.getRoutes().get(0).getRouteType());
        Assertions.assertEquals(memberRating, result.getRoutes().get(0).getMemberRating());
    }
}
