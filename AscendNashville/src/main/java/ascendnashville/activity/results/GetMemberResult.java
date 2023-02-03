package ascendnashville.activity.results;

import ascendnashville.models.MemberModel;

/**
 * Implementation of the GetDestinationsActivity for Digital Nomad's GetDestinations API.
 *
 * This API allows the customer to get a list of destinations.
 */
public class GetMemberResult {
    private final MemberModel member;

    private GetMemberResult(MemberModel member) {
        this.member = member;
    }

    public MemberModel getMember() {
        return member;
    }

    @Override
    public String toString() {
        return "GetMembersResult{" +
                "member=" + member +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MemberModel member;

        public Builder withMember(MemberModel member) {
            this.member = member;
            return this;
        }

        public GetMemberResult build() {
            return new GetMemberResult(member);
        }
    }
}
