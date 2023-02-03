package ascendnashville.lambda;

import ascendnashville.activity.requests.GetMemberRequest;
import ascendnashville.activity.results.GetMemberResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Generates a LambdaResponse.
 */
public class GetMemberLambda extends LambdaActivityRunner<GetMemberRequest, GetMemberResult>
        implements RequestHandler<LambdaRequest<GetMemberRequest>, LambdaResponse> {

    /**
     *
     * @param input The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return LambdaResponse
     */
    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetMemberRequest> input, Context context) {
        return super.runActivity(() -> input.fromPathAndQuery((path, query) ->
                GetMemberRequest.builder()
                        .withMember(path.get("memberId"))
                        .build()), (request, serviceComponent) ->
                serviceComponent.provideGetMemberActivity().handleRequest(request));
    }
}
