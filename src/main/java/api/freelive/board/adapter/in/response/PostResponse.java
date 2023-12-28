package api.freelive.board.adapter.in.response;

import api.freelive.board.domain.Post;
import api.freelive.board.domain.PostFile;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostResponse {

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

    public PostResponse(Long postNum){
        this.postNum = postNum;
    }

    public PostResponse(Post post) {
        this.postNum = post.getPostNum();
        this.userNum = post.getUserNum();
        this.guestHash = post.getGuestHash();
        this.name = post.getName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.likeCount = post.getLikeCount();
        this.commentCount = post.getCommentCount();
        this.viewCount = post.getViewCount();
        this.isDel = post.getIsDel();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();

        this.postFiles = post.getPostFiles();
    }

    public PostResponse(Post post, Boolean visibleContent) {
        this(post);
        if(!visibleContent){
            this.content = null;
        }
    }
}
