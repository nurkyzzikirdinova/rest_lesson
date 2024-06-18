package peaksoft.rest_lesson.api;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.rest_lesson.dto.studentDto.SimpleResponse;
import peaksoft.rest_lesson.dto.studentDto.StudentRequest;
import peaksoft.rest_lesson.dto.studentDto.StudentResponse;
import peaksoft.rest_lesson.entity.Student;
import peaksoft.rest_lesson.service.StudentService;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
//@PreAuthorize("hasAnyAuthority('ADMIN')")
public class StudentApi {
    private final StudentService studentService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public SimpleResponse saveStudent(@RequestBody StudentRequest studentRequest) {
        return studentService.saveStudent(studentRequest);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PermitAll
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
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse delete(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

}
