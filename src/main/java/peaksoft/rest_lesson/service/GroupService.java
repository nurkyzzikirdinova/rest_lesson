package peaksoft.rest_lesson.service;

import peaksoft.rest_lesson.dto.groupDto.GroupRequest;
import peaksoft.rest_lesson.dto.groupDto.GroupResponse;
import peaksoft.rest_lesson.dto.groupDto.SearchResponse;
import peaksoft.rest_lesson.dto.groupDto.UpdateGroupRequest;
import peaksoft.rest_lesson.dto.studentDto.SimpleResponse;


import java.util.List;

public interface GroupService {
    SimpleResponse saveGroup(GroupRequest groupRequest);

    GroupResponse getGroupById(Long groupIdd);

    List<GroupResponse> getAllGroups();

    SimpleResponse updateByID(Long oldGroup, UpdateGroupRequest updateGroupRequest);

    SimpleResponse deleteById(Long id);

    SimpleResponse assignStudentToGroup (Long studentId, Long groupId);

    List<SearchResponse> searchGroup(String word);


}


