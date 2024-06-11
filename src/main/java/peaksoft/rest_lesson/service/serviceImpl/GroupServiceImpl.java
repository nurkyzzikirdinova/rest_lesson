package peaksoft.rest_lesson.service.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.rest_lesson.dto.groupDto.GroupRequest;
import peaksoft.rest_lesson.dto.groupDto.GroupResponse;
import peaksoft.rest_lesson.dto.groupDto.SearchResponse;
import peaksoft.rest_lesson.dto.groupDto.UpdateGroupRequest;
import peaksoft.rest_lesson.dto.studentDto.SimpleResponse;
import peaksoft.rest_lesson.entity.Group;
import peaksoft.rest_lesson.entity.Student;
import peaksoft.rest_lesson.repository.GroupRepo;
import peaksoft.rest_lesson.repository.StudentRepo;
import peaksoft.rest_lesson.service.GroupService;

import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional
public class GroupServiceImpl implements GroupService {
    private final GroupRepo groupRepository;
    private final StudentRepo studentRepository;


    @Override
    public SimpleResponse saveGroup(GroupRequest groupRequest) {
        Group group = new Group();
        group.setGroupName(groupRequest.groupName());
        group.setImageLink(groupRequest.imageLink());
        group.setDescription(groupRequest.description());
        groupRepository.save(group);
        return SimpleResponse
                .builder()
                .message("Success")
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public List<GroupResponse> getAllGroups() {
        return groupRepository.getAllGroups();
    }

    @Override
    public GroupResponse getGroupById(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));

        return new GroupResponse(
                group.getId(),
                group.getGroupName(),
                group.getImageLink(),
                group.getDescription()
        );
    }

    @Override
    public SimpleResponse updateByID(Long id, UpdateGroupRequest updateGroupRequest) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));
        group.setGroupName(updateGroupRequest.groupName());
        group.setDescription(updateGroupRequest.description());
        groupRepository.save(group);
        return SimpleResponse
                .builder()
                .message("Success updated")
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));
        groupRepository.delete(group);
        return SimpleResponse
                .builder()
                .message("Success deleted")
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public SimpleResponse assignStudentToGroup(Long studentId, Long groupId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new NullPointerException(String.format("Student with id %d not found", studentId)));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Group with id %d not found", groupId)));
        student.setGroup(group);
        studentRepository.save(student);
        return SimpleResponse
                .builder()
                .message("Success assigned")
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public List<SearchResponse> searchGroup(String word) {
        return groupRepository.searchGroupsByGroupName(word);
    }
}