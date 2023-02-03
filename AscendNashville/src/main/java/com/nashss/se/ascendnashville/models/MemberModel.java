package com.nashss.se.ascendnashville.models;

import java.util.List;
import java.util.Objects;

/**
 * MemberModel class.
 */
public class MemberModel {

    private final String memberId;
    private final String name;
    private final Integer age;
    private final String gender;
    private final List<String> contactInfo;
    private final String type;

    private MemberModel(String memberId, String name, Integer age, String gender,
                        List<String> contactInfo, String type) {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemberModel that = (MemberModel) o;
        return Objects.equals(memberId, that.memberId) && Objects.equals(name, that.name) &&
                Objects.equals(age, that.age) && Objects.equals(gender, that.gender) &&
                Objects.equals(contactInfo, that.contactInfo) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, name, age, gender, contactInfo, type);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private String memberId;
        private String name;
        private Integer age;
        private String gender;
        private List<String> contactInfo;
        private String type;
        public Builder withMemberId(String memberId) {
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

        public MemberModel build() {
            return new MemberModel(memberId, name, age, gender, contactInfo, type);
        }
    }
}
