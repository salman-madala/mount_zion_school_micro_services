package com.zion.school.service;

import com.zion.school.model.StudentImage;

import java.util.List;

public interface StudentImageService {

    public StudentImage uploadImage(StudentImage studentImage);
    public StudentImage updateImage(StudentImage studentImage);
    public void deleteImage(Long id);

}
