package api.freelive.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Builder
@AllArgsConstructor
public class User implements UserDetails {

    private Long userNum;

    private String userId;

    private String username;

    private String pw;

    private Boolean isAuthenticated;

    private Boolean isActive;

    private String birth;

    private String gender;

    private String phone;

    private String blackList;

    private Long role;

    private Collection<? extends GrantedAuthority> authorities;

//    public Long getUserId() {
//        return Long.parseLong(this.userNum);
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User(Long userNum, Collection<? extends GrantedAuthority> authorities){
        this.userNum = userNum;
        this.authorities = authorities;
    }

}
