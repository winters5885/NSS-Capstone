package com.nashss.se.ascendnashville.lambda;

import com.nashss.se.ascendnashville.activity.requests.GetMemberRequest;
import com.nashss.se.ascendnashville.activity.results.GetMemberResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Generates a LambdaResponse.
 */
public class GetMemberLambda extends LambdaActivityRunner<GetMemberRequest, GetMemberResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetMemberRequest>, LambdaResponse> {

    /**
     *
     * @param input The Authenticated Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return LambdaResponse
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetMemberRequest> input, Context context) {
        return super.runActivity(() -> input.fromPathAndQuery((path, query) ->
                GetMemberRequest.builder()
                        .withMember(path.get("memberId"))
                        .build()), (request, serviceComponent) ->
                serviceComponent.provideGetMemberActivity().handleRequest(request));
    }
}
