package com.chenze.spring.security.learning.service;

import cn.hutool.core.collection.CollUtil;
import com.chenze.spring.security.learning.dao.UserPermissionURIDao;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserPermissionControllerServiceImpl implements MyUserPermissionControllerService {

    @Resource
    private UserPermissionURIDao userPermissionURIDao;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            Collection<GrantedAuthority> authorities = user.getAuthorities();
            List<String> roles = authorities.stream().map(GrantedAuthority::getAuthority)/*.filter(
                    authority -> authority.startsWith("ROLE_")
            )*/.collect(Collectors.toList());

            for (String role : roles) {
                List<String> permissions = userPermissionURIDao.selectOne(role);
                if (CollUtil.isEmpty(permissions)) {
                    continue;
                }

                for (String permission : permissions) {
                    if (request.getRequestURI().contains(permission)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
