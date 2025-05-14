package com.chenze.spring.security.learning.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * 自定义登录成功之后的处理器
 * @author chenze
 * @date 2024/8/23 23:31
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);

    private final String url;

    public MyAuthenticationSuccessHandler(String url) {
        this.url = url;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();

        String username = user.getUsername();// 当前登录用户的用户名
        String password = user.getPassword();// 安全起见，框架会返回null
        Collection<GrantedAuthority> authorities = user.getAuthorities();// 当前登录用户的权限
        logger.info("username={}, password={}, authorities={}", username, password, authorities);

        httpServletResponse.sendRedirect(url);

    }
}
