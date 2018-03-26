package com.flowedu.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Student {

    @JsonProperty("student_id")
    private Long studentId;

    @JsonProperty("student_name")
    private String studentName;

    @JsonProperty("student_gender")
    private String studentGender;

    @JsonProperty("student_birthday")
    private String studentBirthday;

    @JsonProperty("home_tel_number")
    private String homeTelNumber;

    @JsonProperty("student_phone_number")
    private String studentPhoneNumber;

    @JsonProperty("student_email")
    private String studentEmail;

    @JsonProperty("school_name")
    private String schoolName;

    @JsonProperty("school_type")
    private String schoolType;

    @JsonProperty("student_grade")
    private int studentGrade;

    @JsonProperty("student_photo_file")
    private String studentPhotoFile;

    @JsonProperty("student_photo_url")
    private String studentPhotoUrl;

    @JsonProperty("mother_name")
    private String motherName;

    @JsonProperty("mother_phone_number")
    private String motherPhoneNumber;

    @JsonProperty("student_auth_key")
    private String studentAuthKey;
}
