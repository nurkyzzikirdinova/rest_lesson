package peaksoft.rest_lesson.dto.groupDto;

import lombok.Builder;

@Builder
public record UpdateGroupRequest(
        String groupName,

        String description) {

}
