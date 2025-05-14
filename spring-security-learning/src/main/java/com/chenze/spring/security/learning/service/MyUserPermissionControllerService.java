package com.chenze.spring.security.learning.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface MyUserPermissionControllerService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);


}
