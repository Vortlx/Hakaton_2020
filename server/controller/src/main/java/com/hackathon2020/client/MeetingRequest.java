package com.hackathon2020.client;

public class MeetingRequest {

    private String jwt;

    public MeetingRequest(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}