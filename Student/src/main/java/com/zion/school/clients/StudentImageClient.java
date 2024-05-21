package com.zion.school.clients;

import com.zion.school.model.SiblingInformation;
import com.zion.school.model.StudentImage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "STUDENTIMAGE-MS",url = "http://localhost:8085")
public interface StudentImageClient {

    @GetMapping(value = "/studentImage/get/{id}")
    Optional<StudentImage> getStudentImage(@PathVariable("id") Long studentId);

    @DeleteMapping(value = "/studentImage")
    void deleteStudentImage(long id);
}
