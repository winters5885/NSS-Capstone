package com.nashss.se.ascendnashville.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.nashss.se.ascendnashville.activity.requests.DeleteRouteRequest;
import com.nashss.se.ascendnashville.activity.results.DeleteRouteResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Generates a LambdaResponse.
 */
public class DeleteRouteLambda
        extends LambdaActivityRunner<DeleteRouteRequest, DeleteRouteResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteRouteRequest>, LambdaResponse> {
    private final Logger log = LogManager.getLogger();
    /**
     *
     * @param input The Authenticated Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return LambdaResponse
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteRouteRequest> input, Context context) {
        log.info("Inside handleRequest for DeleteRouteLambda.");

        return super.runActivity(() -> input.fromPath(path ->
                        DeleteRouteRequest.builder()
                                .withRouteId(path.get("routeId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideDeleteRouteActivity().handleRequest(request));
    }
}
