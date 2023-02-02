package ascendnashville.lambda;

import ascendnashville.activity.requests.CreateMemberRequest;
import ascendnashville.activity.results.CreateMemberResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateMemberLambda
        extends LambdaActivityRunner<CreateMemberRequest, CreateMemberResult>
        implements RequestHandler<LambdaRequest<CreateMemberRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<CreateMemberRequest> input, Context context) {
        return super.runActivity(() -> input.fromBody(CreateMemberRequest.class),
                (request, serviceComponent) ->
                serviceComponent.provideCreateMemberActivity().handleRequest(request));
    }
}
