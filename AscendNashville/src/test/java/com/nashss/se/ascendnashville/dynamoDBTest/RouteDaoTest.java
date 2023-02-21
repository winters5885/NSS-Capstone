package com.nashss.se.ascendnashville.dynamoDBTest;

import com.amazonaws.services.dynamodbv2.datamodeling.*;


import com.nashss.se.ascendnashville.Exceptions.RouteNotFoundException;
import com.nashss.se.ascendnashville.dynamoDB.RouteDao;

import com.nashss.se.ascendnashville.dynamoDB.models.Route;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class RouteDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;
    private RouteDao routeDao;
    @Mock
    private PaginatedScanList<Route> scanResult;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        routeDao = new RouteDao(dynamoDBMapper);

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

    @Test
    public void getRoute_withRouteId_callsMapperWithPartitionKey() {
        // GIVEN
        String expectedRouteId = "expectedRouteId";
        when(dynamoDBMapper.load(Route.class, expectedRouteId)).thenReturn(new Route());

        // WHEN
        Route route = routeDao.getRoute(expectedRouteId);

        // THEN
        Assertions.assertNotNull(route);
        verify(dynamoDBMapper).load(Route.class, expectedRouteId);

    }
    @Test
    void getRoutes_scansDynamoDbRouteTable_returnsRoutes() {
        // GIVEN
        when(dynamoDBMapper.scan(eq(Route.class), any())).thenReturn(scanResult);

        ArgumentCaptor<DynamoDBScanExpression> scanExpressionArgumentCaptor =
                ArgumentCaptor.forClass(DynamoDBScanExpression.class);

        // WHEN
        List<Route> routes = routeDao.getRoutes();

        // THEN
        verify(dynamoDBMapper).scan(eq(Route.class), scanExpressionArgumentCaptor.capture());
        DynamoDBScanExpression scanExpression = scanExpressionArgumentCaptor.getValue();

        verify(dynamoDBMapper).scan(Route.class, scanExpression);

        assertEquals(scanResult, routes, "Expected method to return the results of the scan");
    }

    @Test
    public void getRoute_routeIdNotFound_throwsRouteNotFoundException() {
        // GIVEN
        String nonExistentRouteId = "nonExistentRouteId";
        when(dynamoDBMapper.load(Route.class, nonExistentRouteId)).thenReturn(null);

        // WHEN + THEN
        assertThrows(RouteNotFoundException.class, () -> routeDao.getRoute(nonExistentRouteId));
    }
}
