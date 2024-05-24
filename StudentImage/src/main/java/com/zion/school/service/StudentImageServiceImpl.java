package com.zion.school.service;

import com.zion.school.helper.ImageHelper;
import com.zion.school.model.StudentImage;
import com.zion.school.repo.StudentImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StudentImageServiceImpl implements StudentImageService {

    @Autowired
    private StudentImageRepo studentImageRepo;

    public StudentImage uploadImage(StudentImage studentImage) throws IOException {
        StudentImage img2 = null;
        if(studentImage.getId() != null) {
                System.out.println("Original Image Byte Size - " + studentImage.getPicByte().length);
                StudentImage img = new StudentImage(studentImage.getName(), studentImage.getType(),
                        ImageHelper.compressBytes(studentImage.getPicByte()));
                img.setId(studentImage.getId());
                StudentImage img1 = studentImageRepo.save(img);

                img2 = new StudentImage(img1.getId(), img1.getName(), img1.getType(),
                        ImageHelper.decompressBytes(img1.getPicByte()));
            } else {
                return null;
            }
        return img2;
    }

    public StudentImage updateImage(StudentImage studentImage) {
        return studentImageRepo.save(studentImage);
    }

    public void deleteImage(Long id) {
        studentImageRepo.deleteById(id);
    }

}
