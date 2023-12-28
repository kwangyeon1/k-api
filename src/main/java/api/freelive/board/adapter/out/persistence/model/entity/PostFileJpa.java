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
@Table(name = "post_files", catalog = "board")
public class PostFileJpa extends JpaAuditingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_file_num")
    private Long postFileNum;

    private Long postNum;

    private String url;

}
