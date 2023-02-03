package ascendnashville.converters;

import ascendnashville.dynamoDB.models.Member;
import ascendnashville.models.MemberModel;

/**
 * Converts between Data and API models.
 */
public class ModelConverter {

    /**
     * Converts a provided {@link Member} into a {@link MemberModel} representation.
     *
     * @param member the playlist to convert
     * @return the converted playlist
     */
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
