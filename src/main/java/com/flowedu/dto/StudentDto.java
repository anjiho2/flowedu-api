package com.flowedu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StudentDto {

    private Long studentId;

    private String studentName;

    private String studentGender;

    private String studentBirthday;

    private String homeTelNumber;

    private String studentPhoneNumber;

    private String studentEmail;

    private String schoolName;

    private String schoolType;

    private int studentGrade;

    private String studentPhotoFile;

    private String studentPhotoUrl;

    private String motherName;

    private String motherPhoneNumber;

    private String studentAuthKey;
}
