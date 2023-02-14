package com.nashss.se.ascendnashville.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.ascendnashville.activity.requests.UpdateEventRequest;
import com.nashss.se.ascendnashville.activity.results.UpdateEventResult;

public class UpdateEventLambda extends LambdaActivityRunner<UpdateEventRequest, UpdateEventResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateEventRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateEventRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    UpdateEventRequest unauthenticatedRequest = input.fromBody(UpdateEventRequest.class);
                    return input.fromUserClaims(claims ->
                            UpdateEventRequest.builder()
                                    .withEventId(unauthenticatedRequest.getEventId())
                                    .withEventDetails(unauthenticatedRequest.getEventDetails())
                                    .withDate(unauthenticatedRequest.getDate())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideUpdateEventActivity().handleRequest(request)
        );
    }
}
