package ascendnashville.activity;

import ascendnashville.activity.requests.GetMemberRequest;
import ascendnashville.activity.results.GetMemberResult;
import ascendnashville.converters.ModelConverter;
import ascendnashville.dynamoDB.MemberDao;

import ascendnashville.dynamoDB.models.Member;
import ascendnashville.models.MemberModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the CreatePlaylistActivity for the MusicPlaylistService's CreatePlaylist API.
 * <p>
 * This API allows the customer to create a new playlist with no songs.
 */
public class GetMemberActivity {

    private final Logger log = LogManager.getLogger();
    private final MemberDao memberDao;

    /**
     * Instantiates a new GetPlaylistActivity object.
     *
     * @param memberDao MemberDao to access the playlist table.
     */
    @Inject
    public GetMemberActivity(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    /**
     * This method handles the incoming request by retrieving the categories from the database.
     * <p>
     * It then returns the categories.
     * <p>
     * If the category does not exist, this should throw a CategoryNotFoundException.
     *
     * @param getMemberRequest request object containing memberId.
     * @return GetMemberResult result object
     */
    public GetMemberResult handleRequest(final GetMemberRequest getMemberRequest) {
        log.info("In the GetMemberActivity handleRequest");
        String requestedMemberId = getMemberRequest.getMemberId();
        Member member = memberDao.getMember(requestedMemberId);
        MemberModel memberModel = new ModelConverter().toMemberModel(member);

        return GetMemberResult.builder()
                .withMember(memberModel)
                .build();
    }
}
