package ascendnashville.lambda;

import ascendnashville.activity.requests.GetMemberRequest;
import ascendnashville.activity.results.GetMemberResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetMemberLambda extends LambdaActivityRunner<GetMemberRequest, GetMemberResult>
        implements RequestHandler<LambdaRequest<GetMemberRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetMemberRequest> input, Context context) {
        return super.runActivity(() -> input.fromPathAndQuery((path, query) ->
                GetMemberRequest.builder()
                        .withMember(path.get("memberId"))
                        .build()), (request, serviceComponent) ->
                serviceComponent.provideGetMemberActivity().handleRequest(request));
    }
}
