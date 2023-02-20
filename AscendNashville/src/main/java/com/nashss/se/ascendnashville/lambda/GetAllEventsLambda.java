package com.nashss.se.ascendnashville.lambda;

import com.nashss.se.ascendnashville.activity.requests.GetAllEventsRequest;
import com.nashss.se.ascendnashville.activity.results.GetAllEventsResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Generates a LambdaResponse.
 */
public class GetAllEventsLambda extends  LambdaActivityRunner<GetAllEventsRequest, GetAllEventsResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetAllEventsRequest>, LambdaResponse> {

    /**
     *
     * @param input The Authenticated Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return LambdaResponse
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetAllEventsRequest> input, Context context) {
        return super.runActivity(() -> input.fromPath(path ->
                GetAllEventsRequest.builder()
                        .build()), (request, serviceComponent) ->
                serviceComponent.provideGetAllEventsActivity().handleRequest());
    }
}
