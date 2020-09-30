package com.hackathon2020.dao;

import com.hackathon2020.dao.filters.SearchCondition;
import com.hackathon2020.dao.filters.SimpleCondition;
import com.hackathon2020.domain.User;
import com.hackathon2020.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao extends CommonDao<User, UserEntity> {
    @Override
    public Class<UserEntity> getEntityClass() {
        return UserEntity.class;
    }

    @Override
    public void remove(String id, List<String> joinIds) {

    }

    public User getByLogin(String login) {
        SimpleCondition condition = new SimpleCondition
                .Builder()
                .setSearchField("login")
                .setSearchCondition(SearchCondition.EQUALS)
                .setSearchValue(login)
                .build();
        return getByCondition(condition).get(0);
    }

    @Override
    public void update(User domain, List<String> removeIds) {

    }
}
