package com.nashss.se.ascendnashville.lambda;

import com.nashss.se.ascendnashville.activity.requests.CreateEventRequest;
import com.nashss.se.ascendnashville.activity.results.CreateEventResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
* Generates a LambdaResponse.
* */
public class CreateEventLambda extends LambdaActivityRunner<CreateEventRequest, CreateEventResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateEventRequest>, LambdaResponse> {

    /**
     *
     * @param input The Authenticated Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return LambdaResponse
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateEventRequest> input, Context context) {
        return super.runActivity(() -> input.fromBody(CreateEventRequest.class), (request, serviceComponent) ->
                serviceComponent.provideCreateEventActivity().handleRequest(request));
    }
}
