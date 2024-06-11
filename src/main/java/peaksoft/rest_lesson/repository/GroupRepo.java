package peaksoft.rest_lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.rest_lesson.dto.groupDto.GroupResponse;
import peaksoft.rest_lesson.dto.groupDto.SearchResponse;
import peaksoft.rest_lesson.entity.Group;
import java.util.List;

public interface GroupRepo extends JpaRepository<Group, Long> {
  @Query("select new peaksoft.rest_lesson.dto.groupDto.GroupResponse(g.id, g.groupName, g.imageLink, g.description) from Group g")
    List<GroupResponse> getAllGroups();

  List<SearchResponse> searchGroupsByGroupName(String word);

}
