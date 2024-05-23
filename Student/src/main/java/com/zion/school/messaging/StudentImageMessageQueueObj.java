package com.zion.school.messaging;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class StudentImageMessageQueueObj {
    private Long id;
    private MultipartFile file;
}
