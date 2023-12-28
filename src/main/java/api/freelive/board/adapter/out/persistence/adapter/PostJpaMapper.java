package api.freelive.board.adapter.out.persistence.adapter;

import api.freelive.board.adapter.out.persistence.model.entity.PostFileJpa;
import api.freelive.board.adapter.out.persistence.model.entity.PostJpa;
import api.freelive.board.domain.Post;
import api.freelive.board.domain.PostFile;

public class PostJpaMapper {

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
