package com.nashss.se.ascendnashville.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.ascendnashville.activity.requests.GetRouteRequest;
import com.nashss.se.ascendnashville.activity.results.GetRouteResult;

/**
 * Generates a LambdaResponse.
 */
public class GetRouteLambda extends LambdaActivityRunner<GetRouteRequest, GetRouteResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetRouteRequest>, LambdaResponse> {

    /**
     *
     * @param input The Authenticated Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return LambdaResponse
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetRouteRequest> input, Context context) {
        return super.runActivity(() -> input.fromPath(path->
                GetRouteRequest.builder()
                        .build()), (request, serviceComponent) ->
                serviceComponent.provideGetRouteActivity().handleRequest());
    }
}
