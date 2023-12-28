package api.freelive.board.adapter.out.persistence.model.entity;

import api.freelive.board.adapter.out.persistence.model.common.JpaAuditingModel;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@DynamicInsert
@Table(name = "post", catalog = "board")
public class PostJpa extends JpaAuditingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_num")
    private Long postNum;

    private Long userNum;

    private Long guestHash;

    private String name;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "INT(10)")
    private Long likeCount;

    @Column(columnDefinition = "INT(10)")
    private Long commentCount;

    private Long viewCount;

    @Column(columnDefinition = "TINYINT")
    private Boolean isDel;
}
