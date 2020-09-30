package com.hackathon2020.security;

import com.hackathon2020.dao.UserDao;
import com.hackathon2020.domain.Role;
import com.hackathon2020.domain.User;
import com.hackathon2020.dao.filters.SimpleCondition;
import com.hackathon2020.dao.filters.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@Qualifier("htonUserDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String ROLE_PREFIX = "ROLE_";

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        return new UserDetails("kfmn_login", "kfmn_pwd", true,
//                true, true, true,
//                Collections.singletonList(new SimpleGrantedAuthority(ROLE_PREFIX + Role.CLIENT.name())),
//                Role.CLIENT);
        SimpleCondition condition = new SimpleCondition.Builder()
                .setSearchField("login")
                .setSearchCondition(SearchCondition.EQUALS)
                .setSearchValue(login)
                .build();
        User user = userDao.getByCondition(condition).stream().findFirst().orElse(null);
        if (user != null) {
            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole().name()));
            return new UserDetails(
                    user.getLogin(),
                    user.getPwd(),
                    true,
                    true,
                    true,
                    true,
                    authorities,
                    user.getRole());
        } else {
            throw new BadCredentialsException("Wrong user name");
        }
    }
}
