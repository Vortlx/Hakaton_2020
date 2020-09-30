package com.hackathon2020.converters;

import com.hackathon2020.domain.Service;
import com.hackathon2020.entities.ServiceEntity;
import org.springframework.stereotype.Component;

@Component
public class ServiceConverter extends Converter<Service, ServiceEntity> {
    @Override
    public Class<Service> getDomainClass() {
        return Service.class;
    }

    @Override
    public Class<ServiceEntity> getEntityClass() {
        return ServiceEntity.class;
    }
}
