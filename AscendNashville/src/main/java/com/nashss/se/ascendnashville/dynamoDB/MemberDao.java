package com.nashss.se.ascendnashville.dynamoDB;

import com.nashss.se.ascendnashville.Exceptions.MemberNotFoundException;
import com.nashss.se.ascendnashville.dynamoDB.models.Member;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for a member using {@link Member} to represent the model in DynamoDB.
 */
@Singleton
public class MemberDao {

    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Instantiates a MemberDao object.
     *
     * @param dynamoDBMapper   the {@link DynamoDBMapper} used to interact with the member table
     */
    @Inject
    public MemberDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    /**
     * Returns the {@link Member} corresponding to the specified id.
     *
     * @param memberId the Member ID
     * @return the stored Member, or null if none was found.
     */
    public Member getMember(String memberId) {
        Member member = this.dynamoDBMapper.load(Member.class, memberId);

        if (member == null) {
            throw new MemberNotFoundException("Could not find member with id " + memberId);
        }

        return member;
    }

    /**
     * Saves (creates or updates) the given member.
     *
     * @param member The member to save
     * @return The Member object that was saved
     */
    public Member saveMember(Member member) {
        this.dynamoDBMapper.save(member);
        return member;
    }
}
