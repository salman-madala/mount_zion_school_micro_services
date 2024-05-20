package com.zion.school.repo;

import com.zion.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByClassToJoin(String className);

    //------------------------------Rte Students--------------------------
    List<Student> findByRteStudentTrueAndClassToJoinOrderByRegistrationIdAsc(String className);

    List<Student> findByRteStudentTrueOrderByRegistrationIdAsc();

    //------------------------------normal Students--------------------------
    List<Student> findByClassToJoinAndRteStudentFalseOrderByRegistrationIdAsc(String className);

    List<Student> findByRteStudentFalseOrderByRegistrationIdAsc();

    String deleteByRegistrationId(String registrationId);



    Optional<Student> findByRegistrationId(String registrationId);


    List<Student> findAllByOrderByRegistrationIdAsc();
}
