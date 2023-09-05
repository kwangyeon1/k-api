package api.freelive.board.application.service;

import api.freelive.board.application.port.in.AuthUseCase;
import api.freelive.board.application.port.out.LoadUserPort;
import api.freelive.board.domain.Auth;
import api.freelive.board.domain.User;
import api.freelive.util.EncryptionValues;
import api.freelive.util.exception.BadRequestException;
import api.freelive.util.message.ErrorMessage;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService implements AuthUseCase {

    private final BCryptPasswordEncoder passwordEncoder;

    private final LoadUserPort loadUserPort;

    @Override
    public String encryptPassword(String pw) {
        String password = passwordEncoder.encode(pw);

        return password;
    }

    @Override
    public Auth login(String userId, String password) {
        User user = loadUserPort.loadUserByUserId(userId)
                .orElseThrow(()->new BadRequestException(ErrorMessage.USER_NOT_FOUND));

        if(!passwordEncoder.matches(password, user.getPw())){
            throw new BadRequestException(ErrorMessage.INVALID_PASSWORD);
        }

        String token = this.createToken(user.getUserNum().toString());
        Auth auth = new Auth(token);

        return auth;
    }

    @Override
    public String createToken(String identifier) {
        Claims claims = Jwts.claims().setSubject(identifier); // JWT payload 에 저장되는 정보단위, 보통 여기서 user를 식별하는 값을 넣는다.
        claims.put("role", "USER"); // 정보는 key / value 쌍으로 저장된다.
        claims.put("userNum", identifier);
        Date now = new Date();

        String token = Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + 30 * 60 * 1000L)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, EncryptionValues.secretKey.getBytes())   // 사용할 암호화 알고리즘과 signature 에 들어갈 secret값 세팅
                .compact();

        return token;
    }

}
