package api.freelive.board.dto;

import lombok.Getter;

@Getter
public class PostDto {

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

    public PostDto(Long postNum) {
        this.postNum = postNum;
    }
}
