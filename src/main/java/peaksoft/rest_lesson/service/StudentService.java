package peaksoft.rest_lesson.service;

import org.springframework.stereotype.Service;
import peaksoft.rest_lesson.dto.studentDto.SimpleResponse;
import peaksoft.rest_lesson.dto.studentDto.StudentRequest;
import peaksoft.rest_lesson.dto.studentDto.StudentResponse;
import peaksoft.rest_lesson.entity.Student;

import java.util.List;

@Service
public interface StudentService {
    SimpleResponse saveStudent(StudentRequest studentRequest);

    StudentResponse getStudentById(Long studentId);

    List<StudentResponse> getAllStudents();

    SimpleResponse updateStudent(Long oldStudent, StudentRequest studentRequest);

    SimpleResponse deleteStudent(Long id);

    Student getStudentByEmail(String email);

}
