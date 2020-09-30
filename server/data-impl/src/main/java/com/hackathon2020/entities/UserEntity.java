package com.hackathon2020.entities;

import com.hackathon2020.domain.Role;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "USER")
@Data
public class UserEntity implements BaseEntity {
    @Id
    private String id;

    private String login;

    private String pwd;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public List<String> getBaseFields() {
        return Arrays.asList("id", "login", "pwd", "role");
    }

    @Override
    public List<String> getJoinFields() {
        return new ArrayList<>(0);
    }
}
