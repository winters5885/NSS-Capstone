package com.nashss.se.ascendnashville.converters;

import com.nashss.se.ascendnashville.dynamoDB.models.Member;
import com.nashss.se.ascendnashville.dynamoDB.models.Route;
import com.nashss.se.ascendnashville.models.MemberModel;
import com.nashss.se.ascendnashville.models.RouteModel;

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
                .withPhoneNumber(member.getPhoneNumber())
                .withAddress(member.getAddress())
                .withEmailAddress(member.getEmailAddress())
                .withType(member.getType())
                .build();
    }

    public RouteModel toRouteModel (Route route) {

        return RouteModel.builder()
                .withRouteId(route.getRouteId())
                .withDifficultyRating(route.getDifficultyRating())
                .withRouteType(route.getRouteType())
                .withMemberRating(route.getMemberRating())
                .build();
    }
}
