package ascendnashville.activity.requests;

import java.util.List;

public class GetMemberRequest {

    private final String memberId;
    private final String name;
    private final Integer age;
    private final String gender;
    private final List<String> contactInfo;
    private final String type;

    private GetMemberRequest(String memberId, String name, Integer age, String gender, List<String> contactInfo, String type) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contactInfo = contactInfo;
        this.type = type;
    }


    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public List<String> getContactInfo() {
        return contactInfo;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "GetMemberRequest{" +
                "memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", contactInfo=" + contactInfo +
                ", type='" + type + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static GetMemberRequest.Builder builder() {
        return new GetMemberRequest.Builder();
    }

    public static class Builder {
        private String memberId;
        private String name;
        private Integer age;
        private String gender;
        private List<String> contactInfo;
        private String type;


        public Builder withMember(String memberId) {
            this.memberId = memberId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAge(Integer age) {
            this.age = age;
            return this;
        }

        public Builder withGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder withContactInfo(List<String> contactInfo) {
            this.contactInfo = contactInfo;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public GetMemberRequest build() {
            return new GetMemberRequest(memberId, name, age, gender, contactInfo, type);
        }
    }
}
