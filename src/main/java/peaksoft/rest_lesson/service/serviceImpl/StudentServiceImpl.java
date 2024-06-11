package peaksoft.rest_lesson.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.rest_lesson.dto.studentDto.SimpleResponse;
import peaksoft.rest_lesson.dto.studentDto.StudentRequest;
import peaksoft.rest_lesson.dto.studentDto.StudentResponse;
import peaksoft.rest_lesson.entity.Student;
import peaksoft.rest_lesson.repository.StudentRepo;
import peaksoft.rest_lesson.service.StudentService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;

    @Override
    public SimpleResponse saveStudent(StudentRequest studentRequest) {
        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setPassword(studentRequest.getPassword());
        student.setGraduationDate(studentRequest.getGraduationDate());
        student.setCreatedDate(LocalDate.now());
        student.setBlocked(false);
        studentRepo.save(student);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Student with id: %s successfully saved", student.getId()))
                .build();

    }

    @Override
    public StudentResponse getStudentById(Long id) {
        return studentRepo.getStudentById(id).orElseThrow(
                () -> new NullPointerException(
                        String.format("Student with id %d not found", id)));
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepo.findAllStudents();
    }

    @Override
    public SimpleResponse updateStudent(Long oldStudent, StudentRequest studentRequest) {
        Student eskiStudent = studentRepo.findById(oldStudent).orElseThrow(
                () -> new NullPointerException(
                        String.format("Student with id %d not found", oldStudent)));
        eskiStudent.setFirstName(studentRequest.getFirstName());
        eskiStudent.setLastName(studentRequest.getLastName());
        eskiStudent.setEmail(studentRequest.getEmail());
        eskiStudent.setPassword(studentRequest.getPassword());
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
        return studentRepo.getStudentByEmail(email);
    }
}
