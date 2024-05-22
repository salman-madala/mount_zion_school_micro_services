package com.zion.school.controller;


import com.zion.school.helper.ImageHelper;
import com.zion.school.model.StudentImage;
import com.zion.school.repo.StudentImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by Lenovo on 20-05-2024.
 */
@RestController
@RequestMapping(value = "/studentImage")
public class StudentImageController {


    @Autowired
    private StudentImageRepo studentImageRepo;

    @PutMapping(value = "/{id}/update")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public StudentImage updateImage(@PathVariable("id") Long id, @RequestParam("imageFile") MultipartFile file) throws IOException {
        StudentImage img2 = null;
        Optional<StudentImage> studentImage = studentImageRepo.findById(id);
        if (studentImage.isPresent()) {
            System.out.println("Original Image Byte Size - " + file.getBytes().length);
            StudentImage img = new StudentImage(file.getOriginalFilename(), file.getContentType(),
                    ImageHelper.compressBytes(file.getBytes()));
            img.setId(id);
            StudentImage img1 = studentImageRepo.save(img);

            img2 = new StudentImage(img1.getId(), img1.getName(), img1.getType(),
                    ImageHelper.decompressBytes(img1.getPicByte()));
        }
//        return ResponseEntity.status(HttpStatus.OK);
        return img2;
    }

    @DeleteMapping(value = "/{id}/delete")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteImage(@PathVariable("id") Long id) throws IOException {
        Optional<StudentImage> studentImage = studentImageRepo.findById(id);
        if (studentImage != null) {
            studentImageRepo.deleteById(id);
        }

    }


    @PostMapping(value = "/{id}/upload")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public StudentImage uplaodImage(@PathVariable("id") Long id, @RequestParam("imageFile") MultipartFile file) throws IOException {
        StudentImage img2 = null;
        Optional<StudentImage> studentImage = studentImageRepo.findById(id);
        if (!studentImage.isPresent()) {
            System.out.println("Original Image Byte Size - " + file.getBytes().length);
            StudentImage img = new StudentImage(file.getOriginalFilename(), file.getContentType(),
                    ImageHelper.compressBytes(file.getBytes()));
            img.setId(id);
            StudentImage img1 = studentImageRepo.save(img);

            img2 = new StudentImage(img1.getId(), img1.getName(), img1.getType(),
                    ImageHelper.decompressBytes(img1.getPicByte()));
        }
        // return ResponseEntity.status(HttpStatus.OK);

        return img2;
    }

    @GetMapping(path = {"/get/{id}"})
    public StudentImage getImage(@PathVariable("id") Long id) throws IOException {
        final Optional<StudentImage> retrievedImage = studentImageRepo.findById(id);
        if (retrievedImage.isPresent()) {
            StudentImage img = new StudentImage(retrievedImage.get().getId(), retrievedImage.get().getName(), retrievedImage.get().getType(),
                    ImageHelper.decompressBytes(retrievedImage.get().getPicByte()));
            return img;
        } else {
            return null;
        }
    }
}


