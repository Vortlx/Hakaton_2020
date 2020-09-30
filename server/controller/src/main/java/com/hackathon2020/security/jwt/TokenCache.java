package com.hackathon2020.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TokenCache {
    private static Logger log = LoggerFactory.getLogger(TokenCache.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private Map<String, String> tokenCache = new HashMap<>();

    @Scheduled(fixedRate = 60_000)
    public void clearCache() {
        log.info("Clearing cache");
        tokenCache.entrySet().removeIf(entry -> jwtTokenUtil.isTokenExpired(entry.getValue()));
        log.info("Cache cleared");
    }

    public String getToken(String login) {
        return tokenCache.get(login);
    }

    public void addToken(String login, String token) {
        log.info("Adding token for login {}", login);
        tokenCache.putIfAbsent(login, token);
    }

    public void removeToken(String login) {
        log.info("Removing token by login {}", login);
        tokenCache.remove(login);
    }
}
