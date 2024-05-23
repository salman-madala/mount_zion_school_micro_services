package com.zion.school.messaging;

import com.zion.school.model.StudentImage;
import com.zion.school.service.StudentImageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StudentImageConsumer {

    @Autowired
    StudentImageService studentImageService;

    @RabbitListener(queues = "studentImageUploadQueue")
    public void getStudentImageUpload(StudentImage studentImage) throws IOException {
        studentImageService.uploadImage(studentImage);
    }
}
