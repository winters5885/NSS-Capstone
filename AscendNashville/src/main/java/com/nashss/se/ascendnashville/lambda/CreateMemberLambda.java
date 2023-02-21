package com.nashss.se.ascendnashville.lambda;

import com.nashss.se.ascendnashville.activity.requests.CreateMemberRequest;
import com.nashss.se.ascendnashville.activity.results.CreateMemberResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Generates a LambdaResponse.
 */
public class CreateMemberLambda
        extends LambdaActivityRunner<CreateMemberRequest, CreateMemberResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateMemberRequest>, LambdaResponse> {

    /**
     *
     * @param input The Authenticated Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return LambdaResponse
     */
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateMemberRequest> input, Context context) {
        return super.runActivity(() -> {
            CreateMemberRequest unauthenticatedRequest =
                            input.fromBody(CreateMemberRequest.class);
            return input.fromUserClaims(claims -> CreateMemberRequest.builder()
                                .withMemberId(unauthenticatedRequest.getMemberId())
                                .withName(claims.get("name"))
                                .withAge(unauthenticatedRequest.getAge())
                                .withGender(unauthenticatedRequest.getGender())
                                .withPhoneNumber(unauthenticatedRequest.getPhoneNumber())
                                .withAddress(unauthenticatedRequest.getAddress())
                                .withEmailAddress(claims.get("email"))
                                .withType(unauthenticatedRequest.getType())
                                .build());
        } ,
            (request, serviceComponent) ->
                                serviceComponent.provideCreateMemberActivity().handleRequest(request));
    }
}
