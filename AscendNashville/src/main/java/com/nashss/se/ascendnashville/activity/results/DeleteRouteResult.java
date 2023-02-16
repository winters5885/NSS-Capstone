package com.nashss.se.ascendnashville.activity.results;

import com.nashss.se.ascendnashville.models.RouteModel;

/**
 * DeleteRouteResult Class.
 */
public class DeleteRouteResult {
    private final RouteModel routeModel;

    /**
     * Constructor for DeleteRouteResult.
     *
     * @param routeModel to covert.
     */
    private DeleteRouteResult(RouteModel routeModel) {
        this.routeModel = routeModel;
    }

    public RouteModel getRouteModel() {
        return routeModel;
    }

    @Override
    public String toString() {
        return "DeleteRouteResult{" +
                "routeModel=" + routeModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static DeleteRouteResult.Builder builder() {
        return new DeleteRouteResult.Builder();
    }

    public static class Builder {
        private RouteModel route;

        public DeleteRouteResult.Builder withRoute(RouteModel route) {
            this.route = route;
            return this;
        }

        public DeleteRouteResult build() {
            return new DeleteRouteResult(route);
        }
    }
}
