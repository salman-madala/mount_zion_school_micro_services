package com.zion.school.service;

import com.zion.school.dto.StudentDTO;
import com.zion.school.model.Student;

import java.util.List;

public interface StudentService {
    public boolean create(Student student) throws Exception;
    public boolean update(Long id,Student student);
    public boolean delete(long id);
    public List<StudentDTO> getAll();
    public Student get(Long registrationId);
    public Integer getTotalNormalStudentsCount();
    public List<Student> getTotalNormalStudents();
    public Integer getClassStudentsCount(String className);
    public List<Student> getIndividualNormalClassStudents(String className);
    public Integer getClassRteStudentsCount(String className);
    public List<Student> getIndividualRteClassStudents(String className);
    public List<Student> getTotalRteStudents();
    public Integer getTotalRteStudentsCount();
}
