package com.chenze.technicalcase.web.service;

import com.chenze.technicalcase.user.api.UserServiceApi;
import com.chenze.technicalcase.user.model.request.QueryUserInfoRequest;
import com.chenze.technicalcase.user.model.response.UserInfoResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @DubboReference
    private UserServiceApi userServiceApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("用户：{} 执行登录请求", username);

        QueryUserInfoRequest queryUserInfoRequest = new QueryUserInfoRequest();
        queryUserInfoRequest.setUsername(username);
        UserInfoResponse userInfo = userServiceApi.queryUserInfoByUserName(queryUserInfoRequest);

        if (userInfo == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        return new User(username, userInfo.getPassword(), AuthorityUtils.createAuthorityList(userInfo.getRoleNames()));
    }


}
