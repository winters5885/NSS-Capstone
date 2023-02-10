package com.nashss.se.ascendnashville.dynamoDBTest;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.ascendnashville.Exceptions.RouteNotFoundException;
import com.nashss.se.ascendnashville.dynamoDB.RouteDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class RouteDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;
    private RouteDao routeDao;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        routeDao = new RouteDao(dynamoDBMapper);
    }

    @Test
    void getRoute_withRouteId_callsMapperWithPartitionKey() {
        // GIVEN
        String routeId = "routeId";
        when(dynamoDBMapper.load(Route.class, routeId)).thenReturn(new Route());

        // WHEN
        Route route = routeDao.getRoute(routeId);

        // THEN
        assertNotNull(route);
        verify(dynamoDBMapper).load(Route.class, routeId);
    }

    @Test
    public void getRoute_routeIdNotFound_throwsRouteNotFoundException() {
        // GIVEN
        String nonExistentRouteId = "nonExistentRouteId";
        when(dynamoDBMapper.load(Route.class, nonExistentRouteId)).thenReturn(null);

        // WHEN + THEN
        assertThrows(RouteNotFoundException.class, () -> routeDao.getRoute(nonExistentRouteId));
    }
    @Test
    public void saveRoute_callsMapperWithRoute() {
        // GIVEN
        Route route = new Route();

        // WHEN
        Route result = routeDao.saveRoute(route);

        // THEN
        verify(dynamoDBMapper).save(route);
        assertEquals(route, result);
    }
}
