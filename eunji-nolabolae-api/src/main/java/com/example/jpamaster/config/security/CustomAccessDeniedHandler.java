package com.example.jpamaster.config.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("No Atuthentification roles: {}", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
//        throw new NoAuthenticationException(HttpStatusCode.FORBIDDEN, "권한이 없습니다.");
        response.sendRedirect("/api/denied");
    }
}
