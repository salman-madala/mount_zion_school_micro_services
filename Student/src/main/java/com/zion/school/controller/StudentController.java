package com.zion.school.controller;

import com.zion.school.model.Student;
import com.zion.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {


    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/new")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> create(@RequestBody Student student) throws Exception {
        boolean res = studentService.create(student);
        if (res)
            return new ResponseEntity<>("Student created successfully", HttpStatus.CREATED);
        return new ResponseEntity<>("Student created failure", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Student>>getAllStudents() {
        List<Student> students = studentService.getAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        boolean res = studentService.delete(id);
        if (res) {
            return new ResponseEntity<>("Student Successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Student  failure in deletion", HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        Student student= studentService.get(id);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Student student) {
        boolean res = studentService.update(id,student);
        if (res) {
            return new ResponseEntity<>("Student Successfully updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Student  failure in update", HttpStatus.NOT_FOUND);
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public Page<Student> getAllStudentsByPage(PageRequest pageRequest){
//    	  Pageable pageable = pageRequestConverter.convert(pageRequest);
//        return studentService.getAllStudentsByPage(pageable);
//    }


    //----------------------Normal Students----------------------------

    /*----------- Get the Total Normal Students Count---------*/
    @GetMapping(value = "/normal/all/count")
    public Integer getTotalStudentsCount() {
        return studentService.getTotalNormalStudentsCount();
    }

    /*----------- Get the Total Normal Students ---------*/
    @GetMapping(value = "/normal/all")
    public List<Student> getTotalNormalStudents() {
        return studentService.getTotalNormalStudents();
    }

    /*
        Get the Students Count based on the class name
        like first class, second class, third class .... like wise
    */
    @GetMapping(value = "/normal/{className}/classCount")
    public Integer getClassStudentsCount(@PathVariable("className") String className) {
        return studentService.getClassStudentsCount(className);
    }

    /*
       Get the Students based on the class name
       like first class, second class, third class .... like wise
   */
    @GetMapping(value = "/normal/class/{className}")
    public List<Student> getIndividualClassStudents(@PathVariable("className") String className) {
        return studentService.getIndividualNormalClassStudents(className);
    }


    //----------------------RTE Student----------------------------

    /*----------- Get the Total RTE Students Count---------*/
    @GetMapping(value = "/rte/all/count")
    public Integer getTotalRteStudentsCount() {
        return studentService.getTotalRteStudentsCount();
    }


    /*
       Get the RTE Students Count based on the class name
       like first class, second class, third class .... like wise
   */
    @GetMapping(value = "/rte/{className}/classCount")
    public Integer getRteClassStudentsCount(@PathVariable("className") String className) {
        return studentService.getClassRteStudentsCount(className);
    }

    /*----------- Get the Total RTE Students Count---------*/
    @GetMapping(value = "/rte/all")
    public List<Student> getTotalRteStudents() {
        return studentService.getTotalRteStudents();
    }


    /*
       Get the Rte Students based on the class name
       like first class, second class, third class .... like wise
   */
    @GetMapping(value = "/rte/class/{className}")
    public List<Student> getIndividualRteClassStudents(@PathVariable("className") String className) {
        return studentService.getIndividualRteClassStudents(className);
    }

}
