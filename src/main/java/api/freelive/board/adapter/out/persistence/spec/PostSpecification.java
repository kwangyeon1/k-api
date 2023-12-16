package api.freelive.board.adapter.out.persistence.spec;

import api.freelive.board.domain.Post;
import org.springframework.data.jpa.domain.Specification;

public class PostSpecification {

    public static Specification<Post> containsTextInTitleOrContent(String searchText) {
        return (root, query, criteriaBuilder) -> {
            String likeSearchText = "%" + searchText + "%";
            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("title"), likeSearchText),
                    criteriaBuilder.like(root.get("content"), likeSearchText)
            );
        };
    }
}
