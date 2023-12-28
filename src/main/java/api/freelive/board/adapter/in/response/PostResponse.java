package api.freelive.board.adapter.in.response;

import lombok.Getter;

@Getter
public class PostResponse {

    private Long postNum;

    private Long userNum;

    private Long guestHash;

    private String name;

    private String content;

    private Long likeCount;

    private Long commentCount;

    private Long viewCount;

    private Boolean isDel;

    public PostResponse(Long postNum){
        this.postNum = postNum;
    }
}
