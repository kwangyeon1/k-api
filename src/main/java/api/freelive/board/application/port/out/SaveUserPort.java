package api.freelive.board.application.port.out;

import api.freelive.board.domain.User;

public interface SaveUserPort {

    Boolean save(User user);

}
