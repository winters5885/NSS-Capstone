package com.nashss.se.ascendnashville.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.ascendnashville.activity.requests.CreateRouteRequest;
import com.nashss.se.ascendnashville.activity.results.CreateRouteResult;

public class CreateRouteLambda
        extends LambdaActivityRunner<CreateRouteRequest, CreateRouteResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateRouteRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateRouteRequest> input, Context context) {
        return super.runActivity(() -> input.fromBody(CreateRouteRequest.class), (request, serviceComponent) ->
                serviceComponent.provideCreateRouteActivity().handleRequest(request));
    }
}
