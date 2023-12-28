package api.freelive.board.application.port.out;

import api.freelive.board.domain.User;
import java.util.Optional;

public interface LoadUserPort {

    Optional<User> loadUserByUserId(String userId);

}
