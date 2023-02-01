package ascendnashville.activity.results;

import ascendnashville.models.MemberModel;

public class CreateMemberResult {
    private final MemberModel member;

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

    public static Builder builder() {
        return new Builder();
    }

    //CHECKSTYLE:OFF:Builder
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
