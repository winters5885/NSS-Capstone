package com.nashss.se.ascendnashville.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.ascendnashville.activity.requests.GetRouteRequest;
import com.nashss.se.ascendnashville.activity.results.GetRouteResult;

/**
 * GetRoutesLambda function.
 */
public class GetRouteLambda extends LambdaActivityRunner<GetRouteRequest, GetRouteResult>
        implements RequestHandler<LambdaRequest<GetRouteRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetRouteRequest> input, Context context) {
        return super.runActivity(() -> input.fromPathAndQuery((path, query) ->
                GetRouteRequest.builder()
                        .withRouteId(path.get("routeId"))
                        .build()), (request, serviceComponent) ->
                serviceComponent.provideGetRouteActivity().handleRequest(request));
    }
}
