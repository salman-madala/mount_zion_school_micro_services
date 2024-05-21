package com.zion.school.clients;

import com.zion.school.model.SiblingInformation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "SIBLINGINFORMATION-MS",url = "http://localhost:8085")
public interface SiblingInformationClient {

    @GetMapping(value = "/siblingInformation")
    List<SiblingInformation> siblingsInformation(@RequestParam Long studentId);


}
