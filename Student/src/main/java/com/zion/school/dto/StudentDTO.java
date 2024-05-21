package com.zion.school.dto;

import com.zion.school.model.SiblingInformation;
import com.zion.school.model.Student;
import com.zion.school.model.StudentImage;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class StudentDTO {
    private Student student;
    private List<SiblingInformation> siblingInformation;
    private StudentImage studentImage;
}
