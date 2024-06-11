package peaksoft.rest_lesson.dto.groupDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchResponse {
    private  String groupName;
    private  String imageLink;
}
