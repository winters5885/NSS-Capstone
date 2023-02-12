package com.nashss.se.ascendnashville.activity.results;

import com.nashss.se.ascendnashville.models.RouteModel;

public class CreateRouteResult {
    private final RouteModel route;

    /**
     * Constructor for CreateRouteResult
     *
     * @param route to covert.
     */
    private CreateRouteResult(RouteModel route) {
        this.route = route;
    }

    public RouteModel getRoute() {
        return route;
    }

    @Override
    public String toString() {
        return "CreateRouteResult{" +
                "route=" + route +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private RouteModel route;

        public Builder withRoute(RouteModel route) {
            this.route = route;
            return this;
        }

        public CreateRouteResult build() {
            return new CreateRouteResult(route);
        }
    }
}
