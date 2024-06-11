package peaksoft.rest_lesson.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.rest_lesson.dto.studentDto.StudentResponse;
import peaksoft.rest_lesson.entity.Student;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    @Query ("select new peaksoft.rest_lesson.dto.studentDto.StudentResponse(" +
            "s.id," +
            "concat(s.firstName,' ',s.lastName) ,"+
            "s.email, " +
            " s.createdDate, " +
            "s.graduationDate," +
            "s.isBlocked)  from Student s")
    List<StudentResponse> findAllStudents();

    Student getStudentByEmail(String email);


    @Query ("select new peaksoft.rest_lesson.dto.studentDto.StudentResponse(" +
            "s.id," +
            "concat(s.firstName,' ',s.lastName) ,"+
            "s.email, " +
            " s.createdDate, " +
            "s.graduationDate," +
            "s.isBlocked)  from Student s where  s.id=:id")
    Optional<StudentResponse> getStudentById(Long id);
}
