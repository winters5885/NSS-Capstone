package com.nashss.se.ascendnashville.activity.results;

import com.nashss.se.ascendnashville.models.RouteModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the GetDestinationsActivity for Digital Nomad's GetDestinations API.
 *
 * This API allows the customer to get a list of destinations.
 */
public class GetRouteResult {

    private final List<RouteModel> routesList;

    private GetRouteResult(List<RouteModel> routesList) {
        this.routesList = routesList;
    }

    public List<RouteModel> getRoutes() {
        return new ArrayList<>(routesList);
    }

    @Override
    public String toString() {
        return "GetRouteResult{" +
                "routesList=" + routesList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static GetRouteResult.Builder builder() {
        return new GetRouteResult.Builder();
    }

    public static class Builder {
        private List<RouteModel> routesList;

        public Builder withRouteList(List<RouteModel> routesList) {
            this.routesList = new ArrayList<>(routesList);
            return this;
        }

        public GetRouteResult build() {
            return new GetRouteResult(routesList);
        }
    }
}
