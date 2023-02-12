package com.nashss.se.ascendnashville.dynamoDB.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

/**
 * Represents a record in the member table.
 */
@DynamoDBTable(tableName = "member")
public class Member {
    private String memberId;
    private String name;
    private Integer age;
    private String gender;
    private String phoneNumber;
    private String address;
    private String emailAddress;
    private String type;

    /**
     * Empty constructor for Member POJO.
     */
    public Member() {}

    /**
     * Non-empty constructor for Member POJO.
     * @param memberId memberId parameter
     * @param name name parameter
     * @param age age parameter
     * @param gender gender parameter
     * @param phoneNumber contact information parameter
     * @param address address for customer
     * @param emailAddress email for customer
     * @param type type parameter
     */
    public Member(String memberId, String name, Integer age,
                  String gender, String phoneNumber, String address, String emailAddress, String type) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.emailAddress = emailAddress;
        this.type = type;
    }
    @DynamoDBHashKey(attributeName = "memberId")
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @DynamoDBAttribute(attributeName = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @DynamoDBAttribute(attributeName = "phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @DynamoDBAttribute(attributeName = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @DynamoDBAttribute(attributeName = "emailAddress")
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @DynamoDBAttribute(attributeName = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Member member = (Member) o;
        return Objects.equals(memberId, member.memberId) &&
                Objects.equals(name, member.name) && Objects.equals(age, member.age) &&
                Objects.equals(gender, member.gender) && Objects.equals(phoneNumber, member.phoneNumber) &&
                Objects.equals(address, member.address) && Objects.equals(emailAddress, member.emailAddress) &&
                Objects.equals(type, member.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, name, age, gender, phoneNumber, address, emailAddress, type);
    }

    @Override
    public String toString() {
        return "Member{" +
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
}
