package com.hackathon2020.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meeting {
    private String id;
    private String url;
    private User client;
    private User employee;
    private Service service;
    private LocalDateTime dateTime;
}
