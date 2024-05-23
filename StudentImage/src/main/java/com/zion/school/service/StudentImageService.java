package com.zion.school.service;

import com.zion.school.model.StudentImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentImageService {

    public StudentImage uploadImage(StudentImage studentImage) throws IOException;
    public StudentImage updateImage(StudentImage studentImage);
    public void deleteImage(Long id);

}
