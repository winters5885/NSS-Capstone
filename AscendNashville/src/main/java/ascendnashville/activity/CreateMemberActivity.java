package ascendnashville.activity;

import ascendnashville.activity.requests.CreateMemberRequest;
import ascendnashville.activity.results.CreateMemberResult;
import ascendnashville.converters.ModelConverter;
import ascendnashville.dynamoDB.MemberDao;

import ascendnashville.dynamoDB.models.Member;
import ascendnashville.models.MemberModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.AscendNashvilleUtils;

import javax.inject.Inject;

public class CreateMemberActivity {
    private final Logger log = LogManager.getLogger();
    private final MemberDao memberDao;

    @Inject
    public CreateMemberActivity(MemberDao memberDao) {
        this.memberDao = memberDao;
    }


    public CreateMemberResult handleRequest(final CreateMemberRequest createMemberRequest) {
        log.info("In the CreateMemberActivity handleRequest.");

        Member newMember = new Member();
        newMember.setMemberId(AscendNashvilleUtils.generateMemberId());
        newMember.setName(createMemberRequest.getName());
        newMember.setAge(createMemberRequest.getAge());
        newMember.setGender(createMemberRequest.getGender());
        newMember.setContactInfo(createMemberRequest.getContactInfo());
        newMember.setType(createMemberRequest.getType());

        memberDao.saveMember(newMember);

        MemberModel memberModel = new ModelConverter().toMemberModel(newMember);
        return CreateMemberResult.builder()
                .withMember(memberModel)
                .build();
    }
}

