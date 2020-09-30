package com.hackathon2020.domain;

import lombok.Data;

import java.util.List;

@Data
public class Group {
    private String id;
    private List<Service> services;
}
