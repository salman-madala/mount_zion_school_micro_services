package com.zion.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MountZionSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(MountZionSchoolApplication.class, args);
	}

}
