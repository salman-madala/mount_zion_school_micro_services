package com.zion.school.controller;

import com.zion.school.model.SiblingInformation;
import com.zion.school.service.SiblingInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/siblingInformation")
public class SiblingInformationController {


    @Autowired
    SiblingInformationService siblingInformationService;

    @GetMapping
    public ResponseEntity<List<SiblingInformation>> getAll() {
        List<SiblingInformation> companies = siblingInformationService.getAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody SiblingInformation siblingInformation) {
        boolean res = siblingInformationService.create(siblingInformation);
        if (res)
            return new ResponseEntity<>("SiblingInformation created successfully", HttpStatus.CREATED);
        return new ResponseEntity<>("SiblingInformation created failure", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody SiblingInformation siblingInformation) {
        boolean res = siblingInformationService.update(id, siblingInformation);
        if (res) {
            return new ResponseEntity<>("SiblingInformation Successfully updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("SiblingInformation  failure in update", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean res = siblingInformationService.delete(id);
        if (res) {
            return new ResponseEntity<>("SiblingInformation Successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("SiblingInformation  failure in deletion", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SiblingInformation> getById(@PathVariable Long id) {
        SiblingInformation siblingInformation = siblingInformationService.getById(id);
        if(siblingInformation != null)
            return new ResponseEntity<>(siblingInformation, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
