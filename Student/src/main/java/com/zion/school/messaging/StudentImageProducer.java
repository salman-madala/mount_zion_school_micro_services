package com.zion.school.messaging;

import com.zion.school.model.StudentImage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentImageUploadProducer {

    private RabbitTemplate rabbitTemplate;

    public StudentImageUploadProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(StudentImage studentImage){

        rabbitTemplate.convertAndSend("studentImageUploadQueue",studentImage);
    }
}
