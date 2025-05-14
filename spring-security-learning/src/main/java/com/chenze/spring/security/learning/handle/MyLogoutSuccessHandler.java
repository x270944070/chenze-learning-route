package com.chenze.spring.security.learning.handle;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    private final String url;

    public MyLogoutSuccessHandler(String url) {
        this.url = url;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.sendRedirect(url);

    }
}
