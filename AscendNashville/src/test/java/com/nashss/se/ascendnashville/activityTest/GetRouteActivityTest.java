package com.nashss.se.ascendnashville.activityTest;

import com.nashss.se.ascendnashville.activity.GetRouteActivity;
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

    @Test
    void getRoutes_scansDynamoDbRouteTable_returnsListOfRoutes() {
        //GIVEN
        Route route1 = new Route("1", "difficultyRating1", "routeType1", 1);
        Route route2 = new Route("2", "difficultyRating2", "routeType2", 1);
        Route route3 = new Route("3", "difficultyRating3", "routeType3", 1);

        List<Route> routesList = new ArrayList<>();
        routesList.add(route1);
        routesList.add(route2);
        routesList.add(route3);

        when(routeDao.getRoutes()).thenReturn(routesList);

        // WHEN
        GetRouteResult result = getRouteActivity.handleRequest();
        System.out.println(result);
        // THEN
        assertEquals(3, result.getRoutes().size());
        assertEquals(route1.getRouteId(), result.getRoutes().get(0).getRouteId());
        assertEquals(route1.getDifficultyRating(), result.getRoutes().get(0).getDifficultyRating());
        assertEquals(route1.getRouteType(), result.getRoutes().get(0).getRouteType());
        assertEquals(route1.getMemberRating(), result.getRoutes().get(0).getMemberRating());

        assertEquals(route2.getRouteId(), result.getRoutes().get(1).getRouteId());
        assertEquals(route2.getDifficultyRating(), result.getRoutes().get(1).getDifficultyRating());
        assertEquals(route2.getRouteType(), result.getRoutes().get(1).getRouteType());
        assertEquals(route2.getMemberRating(), result.getRoutes().get(1).getMemberRating());

        assertEquals(route3.getRouteId(), result.getRoutes().get(2).getRouteId());
        assertEquals(route3.getDifficultyRating(), result.getRoutes().get(2).getDifficultyRating());
        assertEquals(route3.getRouteType(), result.getRoutes().get(2).getRouteType());
        assertEquals(route3.getMemberRating(), result.getRoutes().get(2).getMemberRating());
    }

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
