package api.freelive.board.application.port.in;

import api.freelive.board.domain.Auth;

public interface AuthUseCase {

    String encryptPassword(String password);

    Auth login(String userId, String password);

    String createToken(String identifier);
}