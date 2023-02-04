package com.nashss.se.ascendnashville.activityTest;

import com.nashss.se.ascendnashville.activity.CreateMemberActivity;
import com.nashss.se.ascendnashville.activity.requests.CreateMemberRequest;
import com.nashss.se.ascendnashville.activity.results.CreateMemberResult;
import com.nashss.se.ascendnashville.dynamoDB.MemberDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateMemberActivityTest {

    @Mock
    private MemberDao memberDao;

    private CreateMemberActivity createMemberActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        createMemberActivity = new CreateMemberActivity(memberDao);
    }

    @Test
    public void handleRequest_withMemberId_createsAndSavesMember() {
        // GIVEN
        String memberId = "expectedId";
        String name = "expectedName";
        Integer age = 100;
        String gender = "expectedGender";
        String phoneNumber = "expectedPhoneNumber";
        String address = "expectedAddress";
        String emailAddress = "expectedEmailAddress";
        String type = "expectedType";

        CreateMemberRequest request = CreateMemberRequest.builder()
                .withMemberId(memberId)
                .withName(name)
                .withAge(age)
                .withGender(gender)
                .withPhoneNumber(phoneNumber)
                .withAddress(address)
                .withEmailAddress(emailAddress)
                .withType(type)
                .build();
        // WHEN
        CreateMemberResult result = createMemberActivity.handleRequest(request);

        // THEN
        verify(memberDao).saveMember(any(Member.class));

        Assertions.assertNotNull(result.getMember().getMemberId());
        Assertions.assertEquals(name, result.getMember().getName());
        Assertions.assertEquals(age, result.getMember().getAge());
        Assertions.assertEquals(gender, result.getMember().getGender());
        Assertions.assertEquals(phoneNumber, result.getMember().getPhoneNumber());
        Assertions.assertEquals(address, result.getMember().getAddress());
        Assertions.assertEquals(emailAddress, result.getMember().getEmailAddress());
        Assertions.assertEquals(type, result.getMember().getType());
    }
}
