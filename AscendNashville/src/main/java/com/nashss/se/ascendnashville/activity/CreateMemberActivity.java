package com.nashss.se.ascendnashville.activity;

import com.nashss.se.ascendnashville.activity.requests.CreateMemberRequest;
import com.nashss.se.ascendnashville.activity.results.CreateMemberResult;
import com.nashss.se.ascendnashville.converters.ModelConverter;
import com.nashss.se.ascendnashville.dynamoDB.MemberDao;

import com.nashss.se.ascendnashville.dynamoDB.models.Member;
import com.nashss.se.ascendnashville.models.MemberModel;

import com.nashss.se.ascendnashville.utils.AscendNashvilleUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
     * with the provided memberId, name, age, gender, phone number, address,
     * email address, and type from the request.
     * <p>
     * It then returns the newly created member.
     * <p>
     * If the provided memberId, name, age, gender, phone number, address,
     * email address, or type has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createMemberRequest request object containing the memberId,
     *                            name, age, gender, phone number, address,
     *                            email address, and type
     *                            associated with it
     * @return createMemberResult result object containing the API defined {@link MemberModel}
     */
    public CreateMemberResult handleRequest(final CreateMemberRequest createMemberRequest) {
        log.info("In the CreateMemberActivity handleRequest.");

        Member newMember = new Member();
        newMember.setMemberId(AscendNashvilleUtils.generateMemberId());
        newMember.setName(createMemberRequest.getName());
        newMember.setAge(createMemberRequest.getAge());
        newMember.setGender(createMemberRequest.getGender());
        newMember.setPhoneNumber(createMemberRequest.getPhoneNumber());
        newMember.setAddress(createMemberRequest.getAddress());
        newMember.setEmailAddress(createMemberRequest.getEmailAddress());
        newMember.setType(createMemberRequest.getType());

        memberDao.saveMember(newMember);

        MemberModel memberModel = new ModelConverter().toMemberModel(newMember);
        return CreateMemberResult.builder()
                .withMember(memberModel)
                .build();
    }
}

