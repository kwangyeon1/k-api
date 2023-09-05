package api.freelive.config.security.auth;

import api.freelive.board.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtProvider {

    private final String secretKey;

    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token.split(" ")[1].trim())
                    .getBody();

            return claims.containsKey("userNum")
                    && claims.get("userNum") != null
                    && claims.get("role") != null;
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims =  Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token.split(" ")[1].trim()).getBody();

        String userId = claims.get("userNum").toString();
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("role").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(Long.parseLong(userId), authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

}
