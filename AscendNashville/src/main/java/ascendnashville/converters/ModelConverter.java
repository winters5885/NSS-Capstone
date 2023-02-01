package ascendnashville.converters;

import ascendnashville.dynamoDB.models.Member;
import ascendnashville.models.MemberModel;

public class ModelConverter {

    public MemberModel toMemberModel(Member member) {

        return MemberModel.builder()
                .withMemberId(member.getMemberId())
                .withAge(member.getAge())
                .withGender(member.getGender())
                .withContactInfo(member.getContactInfo())
                .withType(member.getType())
                .build();
    }
}
