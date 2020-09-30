package com.hackathon2020.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LogoutSuccessHandler implements LogoutHandler {

    private final TokenCache tokenCache;

    private final JwtTokenUtil jwtTokenUtil;

    public LogoutSuccessHandler(TokenCache tokenCache, JwtTokenUtil jwtTokenUtil) {
        this.tokenCache = tokenCache;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public void logout(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       Authentication authentication) {
        String token = httpServletRequest.getHeaders("Authorization").nextElement().substring(5);
        String login = jwtTokenUtil.getUsernameFromToken(token);
        log.info("logout user {}", login);
        tokenCache.removeToken(login);
    }
}
