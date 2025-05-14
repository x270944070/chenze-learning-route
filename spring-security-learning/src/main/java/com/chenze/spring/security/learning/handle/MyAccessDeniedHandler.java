package com.chenze.spring.security.learning.handle;

import cn.hutool.http.HttpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问权限不足
 * @author chenze
 * @date 2024/8/25 8:06
 */
@Configuration
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(403);
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write("{\"status\":\"error\",\"msg\":\"权限不足，请联系管理员\"}");
    }
}
