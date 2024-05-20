package com.zion.school.service;

import com.zion.school.model.Student;
import com.zion.school.model.StudentImage;
import com.zion.school.repo.StudentRepository;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    StudentRepository studentRepository;


    public StudentServiceImpl(final StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
    public boolean update(Long id,Student student) {
        Optional<Student> optionalCompanie = studentRepository.findById(id);
        if (optionalCompanie.isPresent()) {
            Student student1 = new Student();
            student1.setFirstName(student.getFirstName());
            student1.setLastName(student.getLastName());
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

    @Override
    public boolean delete(long id) {
//        Optional<StudentImage> stuImage = studentImageRepo.findById(id);
//        if (stuImage.isPresent()) {
//            studentImageRepo.deleteById(id);
//        }

        if(studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }


    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAllByOrderByRegistrationIdAsc();
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
