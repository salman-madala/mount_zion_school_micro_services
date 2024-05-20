package com.zion.school.service;

import com.zion.school.model.StudentImage;
import com.zion.school.repo.StudentImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentImageServiceImpl implements StudentImageService {

    @Autowired
    private StudentImageRepo studentImageRepo;

    public StudentImage uploadImage(StudentImage studentImage){
        StudentImage student1 = studentImageRepo.save(studentImage);
        return student1;
    }
    public StudentImage updateImage(StudentImage studentImage){
        return studentImageRepo.save(studentImage);
    }
    public void deleteImage(Long id){
        studentImageRepo.deleteById(id);
    }

}
