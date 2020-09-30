package com.hackathon2020.security;

import com.hackathon2020.dao.CommonDao;
import com.hackathon2020.dao.filters.SearchCondition;
import com.hackathon2020.dao.filters.SimpleCondition;
import com.hackathon2020.domain.Role;
import com.hackathon2020.domain.User;
import com.hackathon2020.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CredentialUtils {

    private final CommonDao<User, UserEntity> userDao;

    @Autowired
    public CredentialUtils(CommonDao<User, UserEntity> userDao) {
        this.userDao = userDao;
    }

    public String getCredentialLogin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal.getClass().equals(UserDetails.class)) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    public Role getCredentialRole() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Role role = null;
        if (principal.getClass().equals(UserDetails.class)) {
            role = ((UserDetails) principal).getRole();
        }
        return role;
    }

    public User getUserInfo() {
        String login = getCredentialLogin();
        SimpleCondition condition = new SimpleCondition.Builder()
                .setSearchField("login")
                .setSearchCondition(SearchCondition.EQUALS)
                .setSearchValue(login)
                .build();

        return userDao.getByCondition(condition).stream().findFirst().orElse(null);
//        return new User("id", "kfm_login", "kfmn_pwd", true, Role.CLIENT);
    }
}
