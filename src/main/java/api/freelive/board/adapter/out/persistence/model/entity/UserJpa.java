package api.freelive.board.adapter.out.persistence.model.entity;

import api.freelive.board.adapter.out.persistence.model.common.JpaAuditingModel;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "user", catalog = "board")
public class UserJpa extends JpaAuditingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_num")
    private Long userNum;

    private String userId;

    private String username;

    private String pw;

    @Column(columnDefinition = "bit")
    private Boolean isAuthenticated;

    @Column(columnDefinition = "bit")
    private Boolean isActive;

    private String birth;

    private String gender;

    private String phone;

    private String blackList;

    @Column(columnDefinition = "TINYINT(3)")
    private Long role;
}
