package api.freelive.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Post {

    private Long postNum;

    private Long userNum;

    private Long guestHash;

    private String name;

    private String title;

    private String content;

    private Long likeCount;

    private Long commentCount;

    private Long viewCount;

    private Boolean isDel;

}
