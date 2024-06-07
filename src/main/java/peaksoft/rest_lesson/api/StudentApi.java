package peaksoft.rest_lesson.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.rest_lesson.dto.SimpleResponse;
import peaksoft.rest_lesson.dto.StudentRequest;
import peaksoft.rest_lesson.dto.StudentResponse;
import peaksoft.rest_lesson.entity.Student;
import peaksoft.rest_lesson.service.StudentService;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentApi {
    private final StudentService studentService;

    @PostMapping
    public SimpleResponse saveStudent(@RequestBody StudentRequest studentRequest) {
        return studentService.saveStudent(studentRequest);
    }

    @GetMapping("/get/{id}")
    StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping
    List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }


    @GetMapping("/getByEmail")
    Student getStudentByEmail(@RequestParam String email) {
        return studentService.getStudentByEmail(email);
    }


    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id, @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

}
