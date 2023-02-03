package com.nashss.se.ascendnashville.models;

import java.util.Objects;

/**
 * MemberModel class.
 */
public class MemberModel {

    private final String memberId;
    private final String name;
    private final Integer age;
    private final String gender;
    private final String phoneNumber;
    private final String address;
    private final String emailAddress;
    private final String type;

    private MemberModel(String memberId, String name, Integer age, String gender, String phoneNumber,
                        String address, String emailAddress, String type) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.emailAddress = emailAddress;
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

    public String getType() {
        return type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberModel that = (MemberModel) o;
        return Objects.equals(memberId, that.memberId) && Objects.equals(name, that.name) &&
                Objects.equals(age, that.age) && Objects.equals(gender, that.gender) &&
                Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(address, that.address) &&
                Objects.equals(emailAddress, that.emailAddress) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, name, age, gender, phoneNumber, address, emailAddress, type);
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
        private String phoneNumber;
        private String address;
        private String emailAddress;
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

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public MemberModel build() {
            return new MemberModel(memberId, name, age, gender, phoneNumber, address, emailAddress, type);
        }
    }
}
