package com.chenze.spring.security.learning.service;

import com.chenze.spring.security.learning.dao.UserInfoDao;
import com.chenze.spring.security.learning.model.entiry.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("用户：{} 执行登录请求", username);

        UserInfo userInfo = userInfoDao.selectOne(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户不存在");
        }


        return new User(username, userInfo.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(userInfo.getAuthorities()));
    }
}
