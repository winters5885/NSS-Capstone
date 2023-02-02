package ascendnashville.activity;

import ascendnashville.activity.requests.GetMemberRequest;
import ascendnashville.activity.results.GetMemberResult;
import ascendnashville.converters.ModelConverter;
import ascendnashville.dynamoDB.MemberDao;

import ascendnashville.dynamoDB.models.Member;
import ascendnashville.models.MemberModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetMemberActivity {

    private final Logger log = LogManager.getLogger();
    private final MemberDao memberDao;

    @Inject
    public GetMemberActivity(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public GetMemberResult handleRequest(final GetMemberRequest getMemberRequest) {
        log.info("In the GetMemberActivity handleRequest");
        String requestedMemberId = getMemberRequest.getMemberId();
        Member member = memberDao.getMember(requestedMemberId);
        MemberModel memberModel = new ModelConverter().toMemberModel(member);

        return GetMemberResult.builder()
                .withMember(memberModel)
                .build();
    }
}
