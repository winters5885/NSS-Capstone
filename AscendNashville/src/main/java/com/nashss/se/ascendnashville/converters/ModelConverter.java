package com.nashss.se.ascendnashville.converters;

import com.nashss.se.ascendnashville.dynamoDB.models.Member;
import com.nashss.se.ascendnashville.dynamoDB.models.Route;
import com.nashss.se.ascendnashville.models.MemberModel;
import com.nashss.se.ascendnashville.models.RouteModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts between Data and API models.
 */
public class ModelConverter {

    /**
     * Converts a provided {@link Member} into a {@link MemberModel} representation.
     *
     * @param member the member to convert
     * @return the converted member
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

    /**
     * Converts a provided {@link Route} into a {@link RouteModel} representation.
     *
     * @param route the route to convert
     * @return the converted route
     */
    public RouteModel toRouteModel(Route route) {

        return RouteModel.builder()
                .withRouteId(route.getRouteId())
                .withDifficultyRating(route.getDifficultyRating())
                .withRouteType(route.getRouteType())
                .withMemberRating(route.getMemberRating())
                .build();
    }

    /**
     * Converts a list of route to a list of RouteModels.
     * @param routes The Routes to convert to RouteModels
     * @return The converted list of RouteModels
     */
    public List<RouteModel> toRoutesModelList(List<Route> routes) {
        List<RouteModel> routeModels = new ArrayList<>();
        for (Route route: routes) {
            routeModels.add(toRouteModel(route));
        }
        return routeModels;
    }
}
