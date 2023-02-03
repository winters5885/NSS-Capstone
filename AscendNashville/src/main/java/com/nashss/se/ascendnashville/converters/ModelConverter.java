package com.nashss.se.ascendnashville.converters;

import com.nashss.se.ascendnashville.dynamoDB.models.Member;
import com.nashss.se.ascendnashville.models.MemberModel;

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
                .withName(member.getName())
                .withAge(member.getAge())
                .withGender(member.getGender())
                .withContactInfo(member.getContactInfo())
                .withType(member.getType())
                .build();
    }
}
