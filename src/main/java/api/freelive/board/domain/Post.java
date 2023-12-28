package api.freelive.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class Post {

    private Long postNum;

    private List<PostFile> postFiles;

    private Long userNum;

    private Long guestHash;

    private String name;

    private String title;

    private String content;

    private Long likeCount;

    private Long commentCount;

    private Long viewCount;

    private Boolean isDel;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
