package ascendnashville.dynamoDB;

import ascendnashville.Exceptions.MemberNotFoundException;
import ascendnashville.dynamoDB.models.Member;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MemberDao {

    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public MemberDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Member getMember(String memberId) {
        Member member = this.dynamoDBMapper.load(Member.class, memberId);

        if (member == null) {
            throw new MemberNotFoundException("Could not find member with id " + memberId);
        }

        return member;
    }

    public Member saveMember(Member member) {
        this.dynamoDBMapper.save(member);
        return member;
    }
}
