package api.freelive.board.adapter.out.persistence.model.entity;

import api.freelive.board.adapter.out.persistence.model.common.JpaAuditingModel;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@DynamicInsert
@Table(name = "post", catalog = "board")
public class PostJpa extends JpaAuditingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_num")
    private Long postNum;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postJpa", targetEntity = PostFileJpa.class)
    private List<PostFileJpa> postFileJpas;

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

    public PostJpa(Long postNum, Long userNum, String title, String content, List<PostFileJpa> postFiles, Long guestHash, String name, Long likeCount, Long commentCount, Long viewCount, Boolean isDel) {
        this.postNum = postNum;
        this.userNum = userNum;
        this.title = title;
        this.content = content;
        setPostFiles(postFiles);

        // 아래는 아직 사용하지 않는 속성들
        this.guestHash = guestHash;
        this.name = name;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
        this.isDel = isDel;
    }

    private void setPostFiles(List<PostFileJpa> postFiles) {
        this.postFileJpas = postFiles;
        for(PostFileJpa postFile : this.postFileJpas){
            if(postFile.getPostFileNum() == null){
                postFile.setPostJpa(this);
            }
        }
    }

}
