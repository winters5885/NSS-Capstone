package com.nashss.se.ascendnashville.convertersTest;

import com.nashss.se.ascendnashville.converters.ModelConverter;
import com.nashss.se.ascendnashville.dynamoDB.models.Event;
import com.nashss.se.ascendnashville.dynamoDB.models.Member;
import com.nashss.se.ascendnashville.dynamoDB.models.Route;
import com.nashss.se.ascendnashville.models.EventModel;
import com.nashss.se.ascendnashville.models.MemberModel;
import com.nashss.se.ascendnashville.models.RouteModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ModelConverterTest {
    private ModelConverter modelConverter = new ModelConverter();

    @Test
    void toMemberModel_withMemberId_convertsMember() {
        // GIVEN
        Member member = new Member();
        member.setMemberId("memberId");
        member.setName("name");
        member.setAge(1);
        member.setGender("gender");
        member.setPhoneNumber("phone number");
        member.setAddress("address");
        member.setEmailAddress("email address");
        member.setType("type");

        // WHEN
        MemberModel memberModel = modelConverter.toMemberModel(member);

        // THEN
        Assertions.assertEquals(member.getMemberId(), memberModel.getMemberId());
        Assertions.assertEquals(member.getName(), memberModel.getName());
        Assertions.assertEquals(member.getAge(), memberModel.getAge());
        Assertions.assertEquals(member.getGender(), memberModel.getGender());
        Assertions.assertEquals(member.getPhoneNumber(), memberModel.getPhoneNumber());
        Assertions.assertEquals(member.getAddress(), memberModel.getAddress());
        Assertions.assertEquals(member.getEmailAddress(), memberModel.getEmailAddress());
        Assertions.assertEquals(member.getType(), memberModel.getType());
    }

    @Test
    void toRouteModel_withRouteId_convertsRoute() {
        // GIVEN
        Route route = new Route();
        route.setRouteId("routeId");
        route.setDifficultyRating("V1");
        route.setRouteType("Boulder");
        route.setMemberRating(5);

        // WHEN
        RouteModel routeModel = modelConverter.toRouteModel(route);

        // THEN
        Assertions.assertEquals(route.getRouteId(), routeModel.getRouteId());
        Assertions.assertEquals(route.getDifficultyRating(), routeModel.getDifficultyRating());
        Assertions.assertEquals(route.getRouteType(), routeModel.getRouteType());
        Assertions.assertEquals(route.getMemberRating(), routeModel.getMemberRating());
    }

    @Test
    void toEventModel_withEventId_convertsEvent() {
        // GIVEN
        Event event = new Event();
        event.setEventId("eventId");
        event.setDate("eventDate");
        event.setEventDetails("eventDetails");

        // WHEN
        EventModel eventModel = modelConverter.toEventModel(event);

        // THEN
        Assertions.assertEquals(event.getEventId(), eventModel.getEventId());
        Assertions.assertEquals(event.getDate(), eventModel.getDate());
        Assertions.assertEquals(event.getEventDetails(), eventModel.getEventDetails());
    }

    @Test
    void toEventsModelList_withListOfEvents_convertsEvents() {
        // GIVEN
        Event event = new Event();
        event.setEventId("eventId");
        event.setDate("eventDate");
        event.setEventDetails("eventDetails");

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);
        // WHEN
        List<EventModel> eventModelList = modelConverter.toEventsModelList(eventList);

        // THEN
        Assertions.assertEquals(eventList.get(0).getEventId(), eventModelList.get(0).getEventId());
        Assertions.assertEquals(eventList.get(0).getDate(), eventModelList.get(0).getDate());
        Assertions.assertEquals(eventList.get(0).getEventDetails(), eventModelList.get(0).getEventDetails());
    }
}
