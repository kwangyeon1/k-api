package api.freelive.board.adapter.out.persistence.adapter;

import api.freelive.board.adapter.out.persistence.model.entity.PostFileJpa;
import api.freelive.board.adapter.out.persistence.model.entity.PostJpa;
import api.freelive.board.domain.Post;

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
                .createdAt(postJpa.getCreatedAt())
                .updatedAt(postJpa.getUpdatedAt())
                .postFiles(postJpa.getPostFileJpas())
                .build();
    }

    public static PostJpa toEntity(Post post) {
        return new PostJpa(
                post.getPostNum(),
                post.getUserNum(),
                post.getTitle(),
                post.getContent(),
                post.getPostFiles(),

                // 아래는 아직 사용하지 않는 속성들
                post.getGuestHash(),
                post.getName(),
                post.getLikeCount(),
                post.getCommentCount(),
                post.getViewCount(),
                post.getIsDel()
        );
    }

}
