package com.hackathon2020.converters;

import com.hackathon2020.domain.Group;
import com.hackathon2020.entities.GroupEntity;
import org.springframework.stereotype.Component;

@Component
public class GroupConverter extends Converter<Group, GroupEntity> {
    @Override
    public Class<Group> getDomainClass() {
        return Group.class;
    }

    @Override
    public Class<GroupEntity> getEntityClass() {
        return GroupEntity.class;
    }
}
