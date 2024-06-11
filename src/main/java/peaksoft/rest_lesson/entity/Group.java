package peaksoft.rest_lesson.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "groups")
@Setter @Getter
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(generator = "group_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "group_gen", sequenceName = "group_seq", allocationSize = 1)
    private  Long id;
    private  String groupName;
    private  String imageLink;
    private  String description;
    @OneToMany(mappedBy = "group", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.DETACH})
    List<Student> studentList;
}
