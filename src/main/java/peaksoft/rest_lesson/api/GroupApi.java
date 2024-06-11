package peaksoft.rest_lesson.api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.rest_lesson.dto.groupDto.GroupRequest;
import peaksoft.rest_lesson.dto.groupDto.GroupResponse;
import peaksoft.rest_lesson.dto.groupDto.SearchResponse;
import peaksoft.rest_lesson.dto.groupDto.UpdateGroupRequest;
import peaksoft.rest_lesson.dto.studentDto.SimpleResponse;
import peaksoft.rest_lesson.service.GroupService;
import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupApi {
    private  final GroupService groupService;
    @GetMapping("/getAll")
    public List<GroupResponse> getAllGroups() {
        return groupService.getAllGroups();
    }
    @PostMapping
    public SimpleResponse saveGroup(@RequestBody GroupRequest groupRequest){
        return groupService.saveGroup(groupRequest);
    }
    @GetMapping("/getById/{id}")
    public GroupResponse getGroupById(@PathVariable("id") Long id) {
        return groupService.getGroupById(id);
    }
    @PostMapping("/update/{id}")
    public SimpleResponse updateGroup(@PathVariable("id") Long id,
                                      @RequestBody UpdateGroupRequest updateGroupRequest){
        return groupService.updateByID(id,updateGroupRequest);
    }
    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteCourse(@PathVariable("id") Long id){
        return groupService.deleteById(id);
    }
    @PostMapping("/assign/{studentId}/{groupId}")
    public SimpleResponse assignStudentToGroup(@PathVariable Long studentId,@PathVariable Long groupId){
        return groupService.assignStudentToGroup(studentId,groupId);
    }
    @GetMapping("/search")
    public List<SearchResponse> searchGroupByName (@RequestParam String word){
        return (List<SearchResponse>) groupService.searchGroup(word);
    }
}
