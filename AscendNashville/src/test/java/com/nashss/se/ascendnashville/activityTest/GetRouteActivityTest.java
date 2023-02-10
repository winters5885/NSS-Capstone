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

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetRouteActivityTest {
//
//    @Mock
//    private RouteDao routeDao;
//    private GetRouteActivity getRouteActivity;
//
//    @BeforeEach
//    public void setUp() {
//        openMocks(this);
//        getRouteActivity = new GetRouteActivity(routeDao);
//    }
//
//    @Test
//    public void handleRequest_savedRouteFound_returnsRoute() {
//        // GIVEN
//        String routeId = "expectedId";
//        String difficultyRating = "expectedDifficultyRating";
//        String routeType = "expectedRouteType";
//        Integer memberRating = 3;
//
//        Route route = new Route();
//        route.setRouteId(routeId);
//        route.setDifficultyRating(difficultyRating);
//        route.setRouteType(routeType);
//        route.setMemberRating(memberRating);
//
//        when(routeDao.getRoute(routeId)).thenReturn(route);
//
//        GetRouteRequest request = GetRouteRequest.builder()
//                .withRouteId(routeId)
//                .build();
//
//        // WHEN
//        GetRouteResult result = getRouteActivity.handleRequest(request);
//
//        // THEN
//        Assertions.assertEquals(routeId, result.getRoute().getRouteId());
//        Assertions.assertEquals(difficultyRating, result.getRoute().getDifficultyRating());
//        Assertions.assertEquals(routeType, result.getRoute().getRouteType());
//        Assertions.assertEquals(memberRating, result.getRoute().getMemberRating());
//    }
}
