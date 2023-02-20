package com.nashss.se.ascendnashville.activityTest;

import com.nashss.se.ascendnashville.activity.GetMemberActivity;
import com.nashss.se.ascendnashville.activity.requests.GetMemberRequest;
import com.nashss.se.ascendnashville.activity.results.GetMemberResult;
import com.nashss.se.ascendnashville.dynamoDB.MemberDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetMemberActivityTest {

    @Mock
    private MemberDao memberDao;
    private GetMemberActivity getMemberActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getMemberActivity = new GetMemberActivity(memberDao);
    }

    @Test
    public void handleRequest_savedMemberFound_returnsMember() {
        // GIVEN
        String memberId = "expectedId";
        String name = "expectedName";
        Integer age = 100;
        String gender = "expectedGender";
        String phoneNumber = "expectedPhoneNumber";
        String address = "expectedAddress";
        String emailAddress = "expectedEmailAddress";
        String type = "expectedType";

        Member member = new Member();
        member.setMemberId(memberId);
        member.setName(name);
        member.setAge(age);
        member.setGender(gender);
        member.setPhoneNumber(phoneNumber);
        member.setAddress(address);
        member.setEmailAddress(emailAddress);
        member.setType(type);

        when(memberDao.getMember(memberId)).thenReturn(member);

        GetMemberRequest request = GetMemberRequest.builder()
                .withMemberId(memberId)
                .build();

        // WHEN
        GetMemberResult result = getMemberActivity.handleRequest(request);

        // THEN
        Assertions.assertEquals(memberId, result.getMember().getMemberId());
        Assertions.assertEquals(name, result.getMember().getName());
        Assertions.assertEquals(age, result.getMember().getAge());
        Assertions.assertEquals(gender, result.getMember().getGender());
        Assertions.assertEquals(phoneNumber, result.getMember().getPhoneNumber());
        Assertions.assertEquals(address, result.getMember().getAddress());
        Assertions.assertEquals(emailAddress, result.getMember().getEmailAddress());
        Assertions.assertEquals(type, result.getMember().getType());

    }
}
