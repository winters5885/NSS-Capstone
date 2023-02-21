package com.nashss.se.ascendnashville.lambda;

import com.nashss.se.ascendnashville.activity.requests.GetEventRequest;
import com.nashss.se.ascendnashville.activity.results.GetEventResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Generates a LambdaResponse.
 */
public class GetEventLambda extends  LambdaActivityRunner<GetEventRequest, GetEventResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetEventRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    /**
     * @param input   The Authenticated Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return LambdaResponse
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetEventRequest> input, Context context) {
        log.info("Inside handleRequest for GetEventLambda unauthenticatedRequest:");

        return super.runActivity(() -> input.fromPath(path ->
                GetEventRequest.builder()
                        .withEventId(path.get("eventId"))
                        .build()),
            (request, serviceComponent) ->
                serviceComponent.provideGetEventActivity().handleRequest(request));
    }
}
