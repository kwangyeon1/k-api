package api.freelive.board.adapter.out.persistence.adapter;

import api.freelive.board.adapter.out.persistence.model.entity.UserJpa;
import api.freelive.board.adapter.out.persistence.repository.UserJpaRepository;
import api.freelive.board.application.port.out.LoadUserPort;
import api.freelive.board.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserJpaAdapter implements LoadUserPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> loadUserByUserId(String userId) {
        UserJpa entity = userJpaRepository.findByUserId(userId);
        if(entity == null){
           return Optional.empty();
        }

        return Optional.ofNullable(UserJpaMapper.toDomain(entity));
    }

}
