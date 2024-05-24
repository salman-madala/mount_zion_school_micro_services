package com.zion.school.messaging;

import com.zion.school.model.SiblingInformation;
import com.zion.school.service.SiblingInformationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SiblingInformationConsumer {

    @Autowired
    SiblingInformationService siblingInformationService;

    @RabbitListener(queues = "createStudentSiblingInformationQueue")
    public void getStudentImageUpload(List<SiblingInformation> siblingInformations) {
        Long studentId = siblingInformations.get(0).getStudentId();
        siblingInformationService.create(studentId,siblingInformations);
    }
}
