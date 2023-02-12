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

        GetRouteRequest request = GetRouteRequest.builder()
                .withRouteId(routeId)
                .build();

        // WHEN
        GetRouteResult result = getRouteActivity.handleRequest(request);

        // THEN
        Assertions.assertEquals(routeId, result.getRoutes().get(0).getRouteId());
        Assertions.assertEquals(difficultyRating, result.getRoutes().get(0).getDifficultyRating());
        Assertions.assertEquals(routeType, result.getRoutes().get(0).getRouteType());
        Assertions.assertEquals(memberRating, result.getRoutes().get(0).getMemberRating());
    }
}
