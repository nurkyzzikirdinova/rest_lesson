package peaksoft.rest_lesson.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.rest_lesson.dto.studentDto.SimpleResponse;
import peaksoft.rest_lesson.dto.studentDto.StudentRequest;
import peaksoft.rest_lesson.dto.studentDto.StudentResponse;
import peaksoft.rest_lesson.entity.Student;
import peaksoft.rest_lesson.entity.User;
import peaksoft.rest_lesson.enums.Role;
import peaksoft.rest_lesson.repository.StudentRepo;
import peaksoft.rest_lesson.repository.UserRepo;
import peaksoft.rest_lesson.service.StudentService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    @Override
    public SimpleResponse saveStudent(StudentRequest studentRequest) {
        User user = new User();
        user.setFirstName(studentRequest.getFirstName());
        user.setLastName(studentRequest.getLastName());
        user.setEmail(passwordEncoder.encode(studentRequest.getPassword()));
        user.setPassword(studentRequest.getPassword());
        user.setRole(Role.STUDENT);
        userRepo.save(user);
        Student student = new Student();
        student.setGraduationDate(studentRequest.getGraduationDate());
        student.setCreatedDate(LocalDate.now());
        student.setBlocked(false);
        student.setUser(user);
        studentRepo.save(student);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Student with id: %s successfully saved", student.getId()))
                .build();
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        return null;
//        return studentRepo.getStudentById(id).orElseThrow(
//                () -> new NullPointerException(
//                        String.format("Student with id %d not found", id)));
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return null;
//        return studentRepo.findAllStudents();
    }

    @Override
    public SimpleResponse updateStudent(Long oldStudent, StudentRequest studentRequest) {
        Student eskiStudent = studentRepo.findById(oldStudent).orElseThrow(
                () -> new NullPointerException(
                        String.format("Student with id %d not found", oldStudent)));
//        eskiStudent.setFirstName(studentRequest.getFirstName());
//        eskiStudent.setLastName(studentRequest.getLastName());
//        eskiStudent.setEmail(studentRequest.getEmail());
//        eskiStudent.setPassword(studentRequest.getPassword());
        eskiStudent.setGraduationDate(studentRequest.getGraduationDate());
        studentRepo.save(eskiStudent);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Student with id: %s successfully updated", eskiStudent.getId()))
                .build();

    }

    @Override
    public SimpleResponse deleteStudent(Long id) {
        if (!studentRepo.existsById(id)) {
            throw new NoSuchElementException(
                    "Student with id: " + id + " is not found!");
        }
        studentRepo.deleteById(id);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Student with id: " + id + " is deleted!")
                .build();


    }


    @Override
    public Student getStudentByEmail(String email) {
        return null;
//        studentRepo.getStudentByEmail(email);
    }
}
