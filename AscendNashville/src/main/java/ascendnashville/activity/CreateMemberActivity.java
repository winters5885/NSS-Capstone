package ascendnashville.activity;

import ascendnashville.activity.requests.GetMemberRequest;
import ascendnashville.activity.results.CreateMemberResult;
import ascendnashville.dynamoDB.MemberDao;

public class CreateMemberActivity {
    private final MemberDao memberDao;

    public CreateMemberActivity(MemberDao memberDao) {
        this.memberDao = memberDao;
    }


    public CreateMemberResult handleRequest(final GetMemberRequest getMemberRequest) {

        return null;

    }
}

