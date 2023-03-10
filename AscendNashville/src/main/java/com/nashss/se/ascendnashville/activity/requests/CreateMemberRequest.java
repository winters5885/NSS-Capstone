package com.nashss.se.ascendnashville.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Instantiates a new CreateMemberRequest object.
 *
 */
@JsonDeserialize(builder = CreateMemberRequest.Builder.class)
public class CreateMemberRequest {

    private final String memberId;
    private final String name;
    private final Integer age;
    private final String gender;
    private final String phoneNumber;
    private final String address;
    private final String emailAddress;
    private final String type;

    /**
     * Instantiates a new CreateMemberRequest object.
     *
     * @param memberId A memberId tied to a member.
     * @param name The name submitted by member input.
     * @param age The age submitted by member input.
     * @param gender The gender submitted by the member.
     * @param phoneNumber The phone number submitted by the member.
     * @param address The address submitted by the customer.
     * @param emailAddress The email address submitted by the customer.
     * @param type The user type, either "member" or "employee."
     */
    public CreateMemberRequest(String memberId, String name, Integer age,
                               String gender, String phoneNumber, String address, String emailAddress,
                               String type) {
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
        return emailAddress; }

    @Override
    public String toString() {
        return "CreateMemberRequest{" +
                "memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
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

        public CreateMemberRequest build() {
            return new CreateMemberRequest(memberId, name, age, gender, phoneNumber,
                    address, emailAddress, type);
        }
    }
}
