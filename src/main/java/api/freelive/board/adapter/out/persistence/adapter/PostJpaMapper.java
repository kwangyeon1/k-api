package api.freelive.board.adapter.out.persistence.adapter;

import api.freelive.board.adapter.out.persistence.model.entity.PostFileJpa;
import api.freelive.board.adapter.out.persistence.model.entity.PostJpa;
import api.freelive.board.domain.Post;
import api.freelive.board.domain.PostFile;

public class PostJpaMapper {

    public static Post toDomain(PostJpa postJpa) {
        return Post.builder()
                .postNum(postJpa.getPostNum())
                .userNum(postJpa.getUserNum())
                .guestHash(postJpa.getGuestHash())
                .name(postJpa.getName())
                .title(postJpa.getTitle())
                .content(postJpa.getContent())
                .likeCount(postJpa.getLikeCount())
                .commentCount(postJpa.getCommentCount())
                .viewCount(postJpa.getViewCount())
                .isDel(postJpa.getIsDel())
                .build();
    }

    public static PostJpa toEntity(Post post) {
        return PostJpa.builder()
                .postNum(post.getPostNum())
                .userNum(post.getUserNum())
                .guestHash(post.getGuestHash())
                .name(post.getName())
                .title(post.getTitle())
                .content(post.getContent())
                .likeCount(post.getLikeCount())
                .commentCount(post.getCommentCount())
                .viewCount(post.getViewCount())
                .isDel(post.getIsDel())
                .build();
    }

    public static PostFileJpa toEntity(PostFile postFile) {
        return PostFileJpa.builder()
                .postNum(postFile.getPostNum())
                .url(postFile.getUrl())
                .build();
    }
}
