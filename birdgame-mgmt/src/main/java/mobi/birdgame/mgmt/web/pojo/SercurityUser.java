package mobi.birdgame.mgmt.web.pojo;

import mobi.birdgame.mgmt.persistent.domain.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * {file description}
 * Created by zhouwei on 2016/10/17.
 */
public class SercurityUser extends Users implements UserDetails {
    public SercurityUser(Users users){
        if(users != null){
            this.setUserId(users.getUserId());
            this.setName(users.getName());
            this.setNickName(users.getNickName());
            this.setPassword(users.getPassword());
        }
    }
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getUsername() {
        return null;
    }

    public boolean isAccountNonExpired() {
        return false;
    }

    public boolean isAccountNonLocked() {
        return false;
    }

    public boolean isCredentialsNonExpired() {
        return false;
    }

    public boolean isEnabled() {
        return false;
    }
}
