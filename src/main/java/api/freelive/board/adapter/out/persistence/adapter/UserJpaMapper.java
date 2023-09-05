package api.freelive.board.adapter.out.persistence.adapter;

import api.freelive.board.adapter.out.persistence.model.entity.UserJpa;
import api.freelive.board.domain.User;

public class UserJpaMapper {

    public static User toDomain(UserJpa userJpa) {
        return User.builder()
                .userNum(userJpa.getUserNum())
                .userId(userJpa.getUserId())
                .username(userJpa.getUsername())
                .pw(userJpa.getPw())
                .isAuthenticated(userJpa.getIsAuthenticated())
                .isActive(userJpa.getIsActive())
                .birth(userJpa.getBirth())
                .gender(userJpa.getGender())
                .phone(userJpa.getPhone())
                .blackList(userJpa.getBlackList())
                .role(userJpa.getRole())
                .build();
    }

    public static UserJpa toEntity(User user) {
        return UserJpa.builder()
                .userNum(user.getUserNum())
                .userId(user.getUserId())
                .username(user.getUsername())
                .pw(user.getPw())
                .isAuthenticated(user.getIsAuthenticated())
                .isActive(user.getIsActive())
                .birth(user.getBirth())
                .gender(user.getGender())
                .phone(user.getPhone())
                .blackList(user.getBlackList())
                .role(user.getRole())
                .build();
    }
}
