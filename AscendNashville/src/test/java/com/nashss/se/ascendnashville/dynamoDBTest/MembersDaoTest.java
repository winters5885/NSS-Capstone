package com.nashss.se.ascendnashville.dynamoDBTest;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.ascendnashville.Exceptions.MemberNotFoundException;
import com.nashss.se.ascendnashville.dynamoDB.MemberDao;
import com.nashss.se.ascendnashville.dynamoDB.models.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class MembersDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    private MemberDao memberDao;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        memberDao = new MemberDao(dynamoDBMapper);
    }

    @Test
    void getMember_withMemberId_callsMapperWithPartitionKey() {
        // GIVEN
        String memberId = "memberId";
        when(dynamoDBMapper.load(Member.class, memberId)).thenReturn(new Member());

        // WHEN
        Member member = memberDao.getMember(memberId);

        // THEN
        assertNotNull(member);
        verify(dynamoDBMapper).load(Member.class, memberId);
    }

    @Test
    public void getMember_memberIdNotFound_throwsMemberNotFoundException() {
        // GIVEN
        String nonexistentMemberId = "nonExistentMemberId";
        when(dynamoDBMapper.load(Member.class, nonexistentMemberId)).thenReturn(null);

        // WHEN + THEN
        assertThrows(MemberNotFoundException.class, () -> memberDao.getMember(nonexistentMemberId));
    }

    @Test
    public void saveMember_callsMapperWithMember() {
        // GIVEN
        Member member = new Member();

        // WHEN
        Member result = memberDao.saveMember(member);

        // THEN
        verify(dynamoDBMapper).save(member);
        assertEquals(member, result);
    }
}
