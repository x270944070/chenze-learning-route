package com.chenze.spring.security.learning.dao;

import com.chenze.spring.security.learning.model.entiry.UserInfo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 假设的用户dao层
 * @author chenze
 * @date 2024/8/23 22:22
 */
@Repository
public class UserInfoDao {

    @Resource
    private PasswordEncoder passwordEncoder;

    private Map<String, UserInfo> dataMap;

    @PostConstruct
    public void init(){
        dataMap = new HashMap<>();
        /**
         * ROLE_：是固定写法，告诉Security这是个角色
         */
        dataMap.put("admin", new UserInfo("admin", passwordEncoder.encode("admin123"), "admin,normal,ROLE_admin"));
        dataMap.put("zhangsan", new UserInfo("zhangsan", passwordEncoder.encode("zhangsan123"), "normal"));
        dataMap.put("lisi", new UserInfo("lisi", passwordEncoder.encode("lisi123"), "normal"));
        dataMap.put("wangwu", new UserInfo("wangwu", passwordEncoder.encode("wangwu123"), "normal"));
    }


    public UserInfo selectOne(String userName){
        return dataMap.get(userName);
    }


}
