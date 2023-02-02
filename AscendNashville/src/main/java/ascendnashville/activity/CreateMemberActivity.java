package ascendnashville.activity;

import ascendnashville.activity.requests.CreateMemberRequest;
import ascendnashville.activity.results.CreateMemberResult;
import ascendnashville.converters.ModelConverter;
import ascendnashville.dynamoDB.MemberDao;

import ascendnashville.dynamoDB.models.Member;
import ascendnashville.models.MemberModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ascendnashville.utils.AscendNashvilleUtils;

import javax.inject.Inject;

/**
 * Implementation of the CreateMemberActivity for Ascend Nashville's CreateMember API.
 * <p>
 * This API allows the customer to create a new member profile that includes their information.
 */
public class CreateMemberActivity {
    private final Logger log = LogManager.getLogger();
    private final MemberDao memberDao;

    /**
     * Instantiates a new CreateMemberActivity object.
     *
     * @param memberDao MemberDao to access the member table.
     */
    @Inject
    public CreateMemberActivity(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    /**
     * This method handles the incoming request by persisting a new member
     * with the provided playlist name and customer ID from the request.
     * <p>
     * It then returns the newly created playlist.
     * <p>
     * If the provided playlist name or customer ID has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createMemberRequest request object containing the playlist name and customer ID
     *                              associated with it
     * @return createMemberResult result object containing the API defined {@link MemberModel}
     */
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

