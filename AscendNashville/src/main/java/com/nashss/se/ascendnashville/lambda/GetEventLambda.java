package com.nashss.se.ascendnashville.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.ascendnashville.activity.requests.GetEventRequest;
import com.nashss.se.ascendnashville.activity.results.GetEventResult;


public class GetEventLambda extends  LambdaActivityRunner<GetEventRequest, GetEventResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetEventRequest>, LambdaResponse> {

    /**
     * @param input   The Authenticated Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return LambdaResponse
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetEventRequest> input, Context context) {
        return super.runActivity(() -> input.fromPath(path ->
                GetEventRequest.builder()
                        .withEventId(path.get("eventId"))
                        .build()),
                (request, serviceComponent) ->
                serviceComponent.provideGetEventActivity().handleRequest(request));
    }
}