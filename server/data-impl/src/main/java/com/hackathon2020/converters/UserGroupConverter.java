package com.hackathon2020.converters;

import com.hackathon2020.domain.UserGroup;
import com.hackathon2020.entities.UserGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserGroupConverter extends Converter<UserGroup, UserGroupEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private GroupConverter groupConverter;

    @Override
    public Class<UserGroup> getDomainClass() {
        return UserGroup.class;
    }

    @Override
    public Class<UserGroupEntity> getEntityClass() {
        return UserGroupEntity.class;
    }

//    @Override
//    public void toDomainObject(UserGroupEntity entity, UserGroup domain) {
//        domain.setId(entity.getId());
//        User user = new User();
//        userConverter.toDomainObject(entity.getUser(), user);
//        domain.setUser(user);
//        Group group = new Group();
//        groupConverter.toDomainObject(entity.getGroup(), group);
//        domain.setGroup(group);
//    }
//
//    @Override
//    public void toEntityObject(UserGroup domain, UserGroupEntity entity) {
//        entity.setId(domain.getId());
//        UserEntity user = new UserEntity();
//        userConverter.toEntityObject(domain.getUser(), user);
//        entity.setUser(user);
//        GroupEntity group = new GroupEntity();
//        groupConverter.toEntityObject(domain.getGroup(), group);
//        entity.setGroup(group);
//    }
}
