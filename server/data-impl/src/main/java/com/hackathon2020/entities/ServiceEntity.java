package com.hackathon2020.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "SERVICE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceEntity implements BaseEntity {
    @Id
    private String id;
    private String name;
    private String groupId;

    @Override
    public List<String> getBaseFields() {
        return Arrays.asList("id", "name", "groupId");
    }

    @Override
    public List<String> getJoinFields() {
        return new ArrayList<>(0);
    }
}
