package com.zion.school.model;

import lombok.Data;

@Data
public class StudentImage {
    private Long id;
    private String name;
    private String type;
    private byte[] picByte;
}
