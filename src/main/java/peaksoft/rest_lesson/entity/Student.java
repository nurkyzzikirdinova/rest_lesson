package peaksoft.rest_lesson.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(generator = "student_gen",
    strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="student_gen",
            sequenceName = "student_seq",
            allocationSize = 1)
    private Long id;
    private LocalDate createdDate;
    private LocalDate graduationDate;
    private boolean isBlocked;
    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.REMOVE,
            CascadeType.DETACH})
    private Group group;
    @OneToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.REMOVE})
    private  User user;
}
