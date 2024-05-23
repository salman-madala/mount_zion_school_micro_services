package com.zion.school.messaging;

import com.zion.school.model.Student;
import com.zion.school.model.StudentImage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class StudentImageUploadProducer {

    private final RabbitTemplate rabbitTemplate;

    public StudentImageUploadProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendStudentImageUploadMessage(Student student, MultipartFile file) throws IOException {
//        StudentImageMessageQueueObj studentImageMessageQueueObj = new StudentImageMessageQueueObj();
//        studentImageMessageQueueObj.setId(student.getId());
//        studentImageMessageQueueObj.setFile(file);

        StudentImage studentImage = new StudentImage();
        studentImage.setId(student.getId());
        studentImage.setName(file.getOriginalFilename());
        studentImage.setType(file.getContentType());
        studentImage.setPicByte(file.getBytes());

        rabbitTemplate.convertAndSend("studentImageUploadQueue",studentImage);
    }
}
