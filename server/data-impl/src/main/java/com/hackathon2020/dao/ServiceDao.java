package com.hackathon2020.dao;

import com.hackathon2020.dao.filters.SearchCondition;
import com.hackathon2020.dao.filters.SimpleCondition;
import com.hackathon2020.domain.Service;
import com.hackathon2020.entities.ServiceEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceDao extends CommonDao<Service, ServiceEntity> {
    @Override
    public Class<ServiceEntity> getEntityClass() {
        return ServiceEntity.class;
    }

    @Override
    public void remove(String id, List<String> joinIds) {

    }

    @Override
    public void update(Service domain, List<String> removeIds) {

    }

    public List<Service> getAll() {
        SimpleCondition condition = new SimpleCondition
                .Builder()
                .setSearchField("id")
                .setSearchCondition(SearchCondition.NOT_NULL)
                .build();
        return getByCondition(condition);
    }

    public List<Service> getByGroupId(String groupId) {
        SimpleCondition condition = new SimpleCondition
                .Builder()
                .setSearchField("groupId")
                .setSearchCondition(SearchCondition.EQUALS)
                .setSearchValue(groupId)
                .build();
        return getByCondition(condition);
    }

}
