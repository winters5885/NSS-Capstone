package com.nashss.se.ascendnashville.activity;

import com.nashss.se.ascendnashville.activity.requests.GetMemberRequest;
import com.nashss.se.ascendnashville.activity.results.GetMemberResult;
import com.nashss.se.ascendnashville.converters.ModelConverter;
import com.nashss.se.ascendnashville.dynamoDB.MemberDao;

import com.nashss.se.ascendnashville.dynamoDB.models.Member;
import com.nashss.se.ascendnashville.models.MemberModel;
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
