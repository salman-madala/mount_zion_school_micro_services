package com.zion.school.service;

import com.zion.school.clients.SiblingInformationClient;
import com.zion.school.clients.StudentImageClient;
import com.zion.school.dto.StudentDTO;
import com.zion.school.model.SiblingInformation;
import com.zion.school.model.Student;
import com.zion.school.model.StudentImage;
import com.zion.school.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;
    SiblingInformationClient siblingInformationClient;
    StudentImageClient studentImageClient;

    public StudentServiceImpl(StudentRepository studentRepository, SiblingInformationClient siblingInformationClient, StudentImageClient studentImageClient) {
        this.studentRepository = studentRepository;
        this.siblingInformationClient = siblingInformationClient;
        this.studentImageClient = studentImageClient;
    }


    @Override
    public boolean create(Student student) throws Exception {
        String result;
        Optional<Student> existStudent = studentRepository.findByRegistrationId(student.getRegistrationId());
        if (existStudent.isPresent()) {
            result = MessageFormat.format("Student RegistrationId " + existStudent.get().getRegistrationId() + " Already exist.", existStudent.get().getRegistrationId());
            throw new Exception(result);
        }
        try {
            studentRepository.save(student);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean update(Long id, Student student) {
        Optional<Student> optionalCompanie = studentRepository.findById(id);
        if (optionalCompanie.isPresent()) {
            Student student1 = updateDate(student);
            student1.setId(id);
            studentRepository.save(student1);
            return true;
        }
        return false;


//        String message=" ";
//        Optional<Student> existStudent = studentRepository.findById(student.getId());
//        if (existStudent.isPresent()) {
//            return  studentRepository.save(student);
////            message = MessageFormat.format("Student RegistrationId " + existStudent.get().getRegistrationId() + " Already exist.", existStudent.get().getRegistrationId());
////            throw new SchoolException(message);
//        }else {
//            message = MessageFormat.format("Student RegistrationId " + existStudent.get().getRegistrationId() + " Already exist.", existStudent.get().getRegistrationId());
//            throw new SchoolException(message);
//        }

    }

    public Student updateDate(Student student) {
        Student student1 = new Student();
        student1.setRegistrationId(student.getRegistrationId());
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setFatherName(student.getFatherName());
        student1.setMotherName(student.getMotherName());
        student1.setMobileNumber(student.getMobileNumber());
        student1.setPresentAddress(student.getPresentAddress());
        student1.setPermanentAddress(student.getPermanentAddress());
        student1.setSamagraId(student.getSamagraId());
        student1.setDateOfAdmission(student.getDateOfAdmission());
        student1.setClassToJoin(student.getClassToJoin());
        student1.setGender(student.getGender());
        student1.setDateOfBirth(student.getDateOfBirth());
        student1.setMarksOfIdentification(student.getMarksOfIdentification());
        student1.setReligion(student.getReligion());
        student1.setCaste(student.getCaste());
        student1.setCastId(student.getCastId());
        student1.setAadharNumber(student.getAadharNumber());
        student1.setBankAccountNumber(student.getBankAccountNumber());
        student1.setIfscCode(student.getIfscCode());
        student1.setChildHandicapped(student.getChildHandicapped());
        student1.setFatherMotherExpired(student.getFatherMotherExpired());
        student1.setSiblings(student.getSiblings());
        student1.setRteStudent(student1.getRteStudent());
        return student1;
    }

    @Override
    public boolean delete(long id) {
        if (studentRepository.existsById(id)) {
            Optional<StudentImage> stuImage = studentImageClient.getStudentImage(id);
            if (stuImage.isPresent()) {
                studentImageClient.deleteStudentImage(id);
            }
            studentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }


    }

    @Override
    public List<StudentDTO> getAll() {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        try {
            List<Student> students = studentRepository.findAllByOrderByRegistrationIdAsc();
            students.forEach(student -> {
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setStudent(student);
                studentDTO.setSiblingInformation(siblingInformationClient.siblingsInformation(student.getId()));
                studentDTO.setStudentImage(studentImageClient.getStudentImage(student.getId()).orElse(null));
                studentDTOS.add(studentDTO);
            });
            return studentDTOS;
        } catch (Exception e) {
            System.out.println(e);
            return studentDTOS;
        }
    }

    @Override
    public Student get(Long registrationId) {
        Optional<Student> student = studentRepository.findById(registrationId);
        return student.get();
    }

    //    public Page<Student> getAllStudentsByPage(Pageable pageable){
//        Page<Student> students=studentRepository.findAll(pageable);
//        return  students;
//    }

    //--------------------Normal Students--------------------------------------


    @Override
    public Integer getTotalNormalStudentsCount() {
        return studentRepository.findByRteStudentFalseOrderByRegistrationIdAsc().size();
    }

    @Override
    public List<Student> getTotalNormalStudents() {
        List<Student> students = studentRepository.findByRteStudentFalseOrderByRegistrationIdAsc();
//        for (Student student : students) {
//            Optional<StudentImage> studentImage = studentImageRepo.findById(student.getId());
//            if (studentImage.isPresent()) {
//                StudentImage studentImage1 = studentImage.get();
//                StudentImage img = new StudentImage(studentImage1.getId(), studentImage1.getName(), studentImage1.getType(),
//                        ImageHelper.decompressBytes(studentImage1.getPicByte()));
//
//                student.setStudentImage(img);
//            } else {
//                student.setStudentImage(null);
//            }
//        }

        return students;
    }

    @Override
    public Integer getClassStudentsCount(String className) {
        return studentRepository.findByClassToJoinAndRteStudentFalseOrderByRegistrationIdAsc(className).size();
    }

    @Override
    public List<Student> getIndividualNormalClassStudents(String className) {
        List<Student> students = studentRepository.findByClassToJoinAndRteStudentFalseOrderByRegistrationIdAsc(className);
//        for (Student student : students) {
//            Optional<StudentImage> studentImage = studentImageRepo.findById(student.getId());
//            if (studentImage.isPresent()) {
//                StudentImage studentImage1 = studentImage.get();
//                StudentImage img = new StudentImage(studentImage1.getId(), studentImage1.getName(), studentImage1.getType(),
//                        ImageHelper.decompressBytes(studentImage1.getPicByte()));
//
//                student.setStudentImage(img);
//            } else {
//                student.setStudentImage(null);
//            }
//        }

        return students;
    }


    //--------------------RTE--------------------------------------

    @Override
    public Integer getClassRteStudentsCount(String className) {
        return studentRepository.findByRteStudentTrueAndClassToJoinOrderByRegistrationIdAsc(className).size();
    }

    @Override
    public List<Student> getIndividualRteClassStudents(String className) {

        List<Student> students = studentRepository.findByRteStudentTrueAndClassToJoinOrderByRegistrationIdAsc(className);
//        for (Student student : students) {
//            Optional<StudentImage> studentImage = studentImageRepo.findById(student.getId());
//            if (studentImage.isPresent()) {
//                StudentImage studentImage1 = studentImage.get();
//                StudentImage img = new StudentImage(studentImage1.getId(), studentImage1.getName(), studentImage1.getType(),
//                        ImageHelper.decompressBytes(studentImage1.getPicByte()));
//
//                student.setStudentImage(img);
//            } else {
//                student.setStudentImage(null);
//            }
//        }

        return students;


    }

    @Override
    public List<Student> getTotalRteStudents() {
        List<Student> students = studentRepository.findByRteStudentTrueOrderByRegistrationIdAsc();
        for (Student student : students) {
//            Optional<StudentImage> studentImage = studentImageRepo.findById(student.getId());
//            if (studentImage.isPresent()) {
//                StudentImage studentImage1 = studentImage.get();
//                StudentImage img = new StudentImage(studentImage1.getId(), studentImage1.getName(), studentImage1.getType(),
//                        ImageHelper.decompressBytes(studentImage1.getPicByte()));
//
//                student.setStudentImage(img);
//            } else {
//                student.setStudentImage(null);
//            }
        }
        return students;
    }

    @Override
    public Integer getTotalRteStudentsCount() {
        return studentRepository.findByRteStudentTrueOrderByRegistrationIdAsc().size();
    }
}
