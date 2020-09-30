package com.hackathon2020.converters;

import com.hackathon2020.domain.Meeting;
import com.hackathon2020.domain.Service;
import com.hackathon2020.domain.User;
import com.hackathon2020.entities.MeetingEntity;
import com.hackathon2020.entities.ServiceEntity;
import com.hackathon2020.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeetingConverter extends Converter<Meeting, MeetingEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ServiceConverter serviceConverter;

    @Override
    public Class<Meeting> getDomainClass() {
        return Meeting.class;
    }

    public void toDomainObject(MeetingEntity entity, Meeting domain) {
        User client = new User();
        userConverter.toDomainObject(entity.getClient(), client);
        User employee = null;
        if (entity.getEmployee() != null) {
            employee = new User();
            userConverter.toDomainObject(entity.getEmployee(), employee);
        }
        Service service = new Service();
        serviceConverter.toDomainObject(entity.getService(), service);
        domain.setId(entity.getId());
        domain.setUrl(entity.getUrl());
        domain.setClient(client);
        domain.setEmployee(employee);
        domain.setService(service);
        domain.setDateTime(entity.getDateTime());
    }

    public void toEntityObject(Meeting domain, MeetingEntity entity) {
        UserEntity clientEntity = new UserEntity();
        userConverter.toEntityObject(domain.getClient(), clientEntity);
        UserEntity employeeEntity = null;
        if (domain.getEmployee() != null) {
            employeeEntity = new UserEntity();
            userConverter.toEntityObject(domain.getEmployee(), employeeEntity);
        }
        ServiceEntity service = new ServiceEntity();
        serviceConverter.toEntityObject(domain.getService(), service);
        entity.setId(domain.getId());
        entity.setUrl(domain.getUrl());
        entity.setClient(clientEntity);
        entity.setEmployee(employeeEntity);
        entity.setService(service);
        entity.setDateTime(domain.getDateTime());
    }

    @Override
    public Class<MeetingEntity> getEntityClass() {
        return MeetingEntity.class;
    }
}
