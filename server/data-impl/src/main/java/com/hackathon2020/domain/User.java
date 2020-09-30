package com.hackathon2020.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    private String id;

    private String login;

    @Setter(onMethod = @__(@JsonProperty))
    @Getter(onMethod = @__(@JsonIgnore))
    private String pwd;

    private Role role;
}
