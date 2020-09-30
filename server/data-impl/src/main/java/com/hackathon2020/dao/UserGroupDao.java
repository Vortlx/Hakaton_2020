package com.hackathon2020.dao;

import com.hackathon2020.dao.filters.SearchCondition;
import com.hackathon2020.dao.filters.SimpleCondition;
import com.hackathon2020.domain.UserGroup;
import com.hackathon2020.entities.UserGroupEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserGroupDao extends CommonDao<UserGroup, UserGroupEntity> {
    @Override
    public Class<UserGroupEntity> getEntityClass() {
        return UserGroupEntity.class;
    }

    @Override
    public void remove(String id, List<String> joinIds) {

    }

    @Override
    public void update(UserGroup domain, List<String> removeIds) {

    }

    public List<UserGroup> getByUserId(String userId) {
        SimpleCondition condition = new SimpleCondition.Builder()
                .setSearchField("userId")
                .setSearchCondition(SearchCondition.EQUALS)
                .setSearchValue(userId)
                .build();
        return getByCondition(condition);
    }

    public List<UserGroup> getAll() {
        SimpleCondition condition = new SimpleCondition
                .Builder()
                .setSearchField("id")
                .setSearchCondition(SearchCondition.NOT_NULL)
                .build();
        return getByCondition(condition);
    }
}
