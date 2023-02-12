package com.nashss.se.ascendnashville.activity.results;

import com.nashss.se.ascendnashville.models.MemberModel;

/**
 * CreateMemberResult Class.
 */
public class CreateMemberResult {
    private final MemberModel member;

    /**
     * Constructor for CreateMemberResult.
     *
     * @param member member model to covert.
     */
    private CreateMemberResult(MemberModel member) {
        this.member = member;
    }

    public MemberModel getMember() {
        return member;
    }

    @Override
    public String toString() {
        return "CreateMemberResult{" +
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

        public CreateMemberResult build() {
            return new CreateMemberResult(member);
        }
    }
}
