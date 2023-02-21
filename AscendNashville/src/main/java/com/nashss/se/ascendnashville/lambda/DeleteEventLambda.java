package com.nashss.se.ascendnashville.lambda;

import com.nashss.se.ascendnashville.activity.requests.DeleteEventRequest;
import com.nashss.se.ascendnashville.activity.results.DeleteEventResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Generates a LambdaResponse.
 */
public class DeleteEventLambda extends LambdaActivityRunner<DeleteEventRequest, DeleteEventResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteEventRequest>, LambdaResponse> {
    private final Logger log = LogManager.getLogger();

    /**
     * @param input The Authenticated Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return LambdaResponse
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteEventRequest> input, Context context) {
        log.info("Inside handleRequest for DeleteEventLambda unauthenticatedRequest:");

        return super.runActivity(() -> input.fromPath(path ->
                DeleteEventRequest.builder()
                        .withEventId(path.get("eventId"))
                        .build()),
            (request, serviceComponent) ->
                serviceComponent.provideDeleteEventActivity().handleRequest(request));
    }
}
