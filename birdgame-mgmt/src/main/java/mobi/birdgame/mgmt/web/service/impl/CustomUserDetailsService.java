package mobi.birdgame.mgmt.web.service.impl;

import mobi.birdgame.mgmt.persistent.domain.Users;
import mobi.birdgame.mgmt.persistent.mapper.UsersMapper;
import mobi.birdgame.mgmt.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * {file description}
 * Created by zhouwei on 2016/10/17.
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    /**
     * 根据用户名加载用户信息
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users user = userService.getUserByUserName(userName);
        if (user != null ){
            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                    true, true, true, true, getGrantedAuthorities(user));
        }
        return null;
    }

    /**
     * 处理用户的角色
     * @param user  用户信息
     * @return  用户角色组
     */
    private Collection<? extends GrantedAuthority> getGrantedAuthorities(Users user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        String roles = user.getRoles();
        if(roles != null && !"".equals(roles)){
            String[] roleArrays = roles.split(",");
            for (String role :roleArrays){
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }
        }
        return authorities;
    }
}
