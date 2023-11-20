package com.chenze.spring.learning.custom.business.service;

import com.chenze.spring.learning.custom.business.model.User;
import com.chenze.spring.learning.custom.framework.Component;
import com.chenze.spring.learning.custom.framework.InitializingBean;
import com.chenze.spring.learning.custom.framework.MyValue;
import com.chenze.spring.learning.custom.framework.Scope;

/**
 * @author chenze
 * @date 2023/11/17 0:16
 */
@Component
@Scope("singleton")
public class UserService implements InitializingBean {

    @MyValue("李四")
    private String name;

    public User getUser(){
        User user = new User();
        user.setId(2L);
        user.setUserName(name);
        user.setAge(30);
        return user;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("-------------afterPropertiesSet-----------");
    }
}
