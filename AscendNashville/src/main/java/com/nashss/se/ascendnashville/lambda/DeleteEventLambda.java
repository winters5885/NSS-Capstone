package com.nashss.se.ascendnashville.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.ascendnashville.activity.requests.DeleteEventRequest;

import com.nashss.se.ascendnashville.activity.results.DeleteEventResult;

/**
 * Generates a LambdaResponse.
 */
public class DeleteEventLambda extends LambdaActivityRunner<DeleteEventRequest, DeleteEventResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteEventRequest>, LambdaResponse> {

    /**
     *
     * @param input The Authenticated Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return LambdaResponse
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteEventRequest> input, Context context) {
        DeleteEventRequest unauthenticatedRequest = input.fromBody(DeleteEventRequest.class);

        return super.runActivity(() -> input.fromPath(path ->
                DeleteEventRequest.builder()
                        .withEventId(unauthenticatedRequest.getEventId())
                        .withDate(unauthenticatedRequest.getDate())
                        .withEventDetails(unauthenticatedRequest.getEventDetails())
                        .build()),
                (request, serviceComponent) ->
                serviceComponent.provideDeleteEventActivity().handleRequest(request));
    }
}
