package com.nashss.se.ascendnashville.convertersTest;

import com.nashss.se.ascendnashville.converters.ModelConverter;
import com.nashss.se.ascendnashville.dynamoDB.models.Member;
import com.nashss.se.ascendnashville.models.MemberModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ModelConverterTest {
    private ModelConverter modelConverter = new ModelConverter();

    @Test
    void toMemberModel_withMemberId_convertsMember() {
        Member member = new Member();
        member.setMemberId("memberId");
        member.setName("name");
        member.setAge(1);
        member.setGender("gender");
        member.setPhoneNumber("phone number");
        member.setAddress("address");
        member.setEmailAddress("email address");
        member.setType("type");

        MemberModel memberModel = modelConverter.toMemberModel(member);
        Assertions.assertEquals(member.getMemberId(), memberModel.getMemberId());
        Assertions.assertEquals(member.getName(), memberModel.getName());
        Assertions.assertEquals(member.getAge(), memberModel.getAge());
        Assertions.assertEquals(member.getGender(), memberModel.getGender());
        Assertions.assertEquals(member.getPhoneNumber(), memberModel.getPhoneNumber());
        Assertions.assertEquals(member.getAddress(), memberModel.getAddress());
        Assertions.assertEquals(member.getEmailAddress(), memberModel.getEmailAddress());
        Assertions.assertEquals(member.getType(), memberModel.getType());
    }
}
