package com.nashss.se.ascendnashville.dynamoDB.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Objects;

/**
 * Represents a record in the member table.
 */
@DynamoDBTable(tableName = "members")
public class Member {
    private String memberId;
    private String name;
    private Integer age;
    private String gender;
    private List<String> contactInfo;
    private String type;

    /**
     * Empty constructor for Category POJO.
     */
    public Member() {}

    /**
     * Non-empty constructor for Category POJO.
     * @param memberId memberId parameter
     * @param name name parameter
     * @param age age parameter
     * @param gender gender parameter
     * @param contactInfo contact information parameter
     * @param type type parameter
     */
    public Member(String memberId, String name, Integer age,
                  String gender, List<String> contactInfo, String type) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contactInfo = contactInfo;
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

    @DynamoDBAttribute(attributeName = "contactInfo")
    public List<String> getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(List<String> contactInfo) {
        this.contactInfo = contactInfo;
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
                Objects.equals(gender, member.gender) &&
                Objects.equals(contactInfo, member.contactInfo) && Objects.equals(type, member.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, name, age, gender, contactInfo, type);
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", contactInfo=" + contactInfo +
                ", type='" + type + '\'' +
                '}';
    }
}
