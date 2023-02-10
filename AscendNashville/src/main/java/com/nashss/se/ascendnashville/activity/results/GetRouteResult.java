package com.nashss.se.ascendnashville.activity.results;

import com.nashss.se.ascendnashville.models.RouteModel;

/**
 * Implementation of the GetDestinationsActivity for Digital Nomad's GetDestinations API.
 *
 * This API allows the customer to get a list of destinations.
 */
public class GetRouteResult {

    private final RouteModel routeModel;

    private GetRouteResult(RouteModel routeModel) {
        this.routeModel = routeModel;
    }

    public RouteModel getRoute() {
        return routeModel;
    }

    @Override
    public String toString() {
        return "GetRouteResult{" +
                "routeModel=" + routeModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static GetRouteResult.Builder builder() {
        return new GetRouteResult.Builder();
    }

    public static class Builder {
        private RouteModel routeModel;

        public Builder withRoute(RouteModel routeModel) {
            this.routeModel = routeModel;
            return this;
        }

        public GetRouteResult build() {
            return new GetRouteResult(routeModel);
        }
    }
}
