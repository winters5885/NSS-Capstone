package com.nashss.se.ascendnashville.activityTest;

import com.nashss.se.ascendnashville.activity.CreateRouteActivity;
import com.nashss.se.ascendnashville.activity.requests.CreateRouteRequest;
import com.nashss.se.ascendnashville.activity.results.CreateRouteResult;
import com.nashss.se.ascendnashville.dynamoDB.RouteDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Route;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateRouteActivityTest {

    @Mock
    private RouteDao routeDao;

    private CreateRouteActivity createRouteActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        createRouteActivity = new CreateRouteActivity(routeDao);
    }

    @Test
    public void handleRequest_withRouteId_createsAndSavesRoute() {
        // GIVEN
        String routeId = "expectedId";
        String difficultyRating = "expectedDifficultyRating";
        String routeType = "expectedRouteType";
        Integer memberRating = 3;

        CreateRouteRequest request = CreateRouteRequest.builder()
                .withRouteId(routeId)
                .withDifficultyRating(difficultyRating)
                .withRouteType(routeType)
                .withMemberRating(memberRating)
                .build();

        // WHEN
        CreateRouteResult result = createRouteActivity.handleRequest(request);

        // THEN
        verify(routeDao).saveRoute(any(Route.class));

        Assertions.assertNotNull(result.getRoute().getRouteId());
        Assertions.assertEquals(difficultyRating, result.getRoute().getDifficultyRating());
        Assertions.assertEquals(routeType, result.getRoute().getRouteType());
        Assertions.assertEquals(memberRating, result.getRoute().getMemberRating());
    }
}
