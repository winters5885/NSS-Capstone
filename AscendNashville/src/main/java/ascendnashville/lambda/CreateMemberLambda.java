package ascendnashville.lambda;

import ascendnashville.activity.requests.CreateMemberRequest;
import ascendnashville.activity.results.CreateMemberResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Generates a LambdaResponse.
 */
public class CreateMemberLambda
        extends LambdaActivityRunner<CreateMemberRequest, CreateMemberResult>
        implements RequestHandler<LambdaRequest<CreateMemberRequest>, LambdaResponse> {

    /**
     *
     * @param input The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return LambdaResponse
     */
    @Override
    public LambdaResponse handleRequest(LambdaRequest<CreateMemberRequest> input, Context context) {
        return super.runActivity(() -> input.fromBody(CreateMemberRequest.class), (request, serviceComponent) ->
                serviceComponent.provideCreateMemberActivity().handleRequest(request));
    }
}
