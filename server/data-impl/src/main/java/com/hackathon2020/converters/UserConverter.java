package com.hackathon2020.converters;

import com.hackathon2020.domain.User;
import com.hackathon2020.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends Converter<User, UserEntity> {

    @Override
    public Class<User> getDomainClass() {
        return User.class;
    }

    @Override
    public Class<UserEntity> getEntityClass() {
        return UserEntity.class;
    }

}
