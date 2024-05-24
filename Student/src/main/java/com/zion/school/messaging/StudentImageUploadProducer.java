package com.zion.school.messaging;

import com.zion.school.model.SiblingInformation;
import com.zion.school.model.Student;
import com.zion.school.model.StudentImage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StudentImageUploadProducer {

    private final RabbitTemplate rabbitTemplate;

    public StudentImageUploadProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendStudentImageUploadMessage(Student student, MultipartFile file) throws IOException {
        StudentImage studentImage = new StudentImage();
        studentImage.setId(student.getId());
        studentImage.setName(file.getOriginalFilename());
        studentImage.setType(file.getContentType());
        studentImage.setPicByte(file.getBytes());

        rabbitTemplate.convertAndSend("studentImageUploadQueue",studentImage);
    }


    public void sendStudentSiblingInformationMessage(Long studentId, List<SiblingInformation> siblingsInformation) throws IOException {
        siblingsInformation.forEach(siblingInformation -> {
            siblingInformation.setStudentId(studentId);
        });
        rabbitTemplate.convertAndSend("createStudentSiblingInformationQueue",siblingsInformation);
    }
}
