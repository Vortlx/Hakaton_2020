package com.hackathon2020.dao;

import com.hackathon2020.dao.filters.SearchCondition;
import com.hackathon2020.dao.filters.SimpleCondition;
import com.hackathon2020.domain.Group;
import com.hackathon2020.entities.GroupEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupDao extends CommonDao<Group, GroupEntity> {
    @Override
    public Class<GroupEntity> getEntityClass() {
        return GroupEntity.class;
    }

    @Override
    public void remove(String id, List<String> joinIds) {

    }

    @Override
    public void update(Group domain, List<String> removeIds) {

    }

    public List<Group> getAll() {
        SimpleCondition condition = new SimpleCondition
                .Builder()
                .setSearchField("id")
                .setSearchCondition(SearchCondition.NOT_NULL)
                .build();
        return getByCondition(condition);
    }
}
