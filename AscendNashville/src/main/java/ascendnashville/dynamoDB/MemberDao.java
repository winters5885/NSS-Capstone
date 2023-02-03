package ascendnashville.dynamoDB;

import ascendnashville.Exceptions.MemberNotFoundException;
import ascendnashville.dynamoDB.models.Member;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for a playlist using {@link Member} to represent the model in DynamoDB.
 */
@Singleton
public class MemberDao {

    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Instantiates a PlaylistDao object.
     *
     * @param dynamoDBMapper   the {@link DynamoDBMapper} used to interact with the playlists table
     */
    @Inject
    public MemberDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    /**
     * Returns the {@link Member} corresponding to the specified id.
     *
     * @param memberId the Playlist ID
     * @return the stored Playlist, or null if none was found.
     */
    public Member getMember(String memberId) {
        Member member = this.dynamoDBMapper.load(Member.class, memberId);

        if (member == null) {
            throw new MemberNotFoundException("Could not find member with id " + memberId);
        }

        return member;
    }

    /**
     * Saves (creates or updates) the given playlist.
     *
     * @param member The playlist to save
     * @return The Playlist object that was saved
     */
    public Member saveMember(Member member) {
        this.dynamoDBMapper.save(member);
        return member;
    }
}
