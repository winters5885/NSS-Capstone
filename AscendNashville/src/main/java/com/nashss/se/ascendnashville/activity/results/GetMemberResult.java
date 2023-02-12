package com.nashss.se.ascendnashville.activity.results;

import com.nashss.se.ascendnashville.models.MemberModel;

public class GetMemberResult {
    private final MemberModel member;

    /**
     * Constructor for GetMemberResult
     *
     * @param member member model to covert.
     */
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
