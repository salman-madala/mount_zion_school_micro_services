package com.zion.school.dto;

import com.zion.school.model.SiblingInformation;
import com.zion.school.model.StudentImage;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class StudentDTO {


    private Long id;

    private String registrationId;

    private String firstName;

    private String lastName;

    private String fatherName;

    private String motherName;

    private Long mobileNumber;

    private String presentAddress;

    private String permanentAddress;

    private String samagraId;

    private Date dateOfAdmission;

    private String classToJoin;

    private String gender;

    private Date dateOfBirth;

    private String marksOfIdentification;

    private String religion;

    private String caste;

    private String castId;

    private Long aadharNumber;

    private Long bankAccountNumber;

    private String ifscCode;

    private Boolean childHandicapped;

    private Boolean fatherMotherExpired;

    private Boolean siblings = false;

    private Boolean rteStudent = false;

    private Set<SiblingInformation> siblingInformation;

    private StudentImage studentImage;

}
