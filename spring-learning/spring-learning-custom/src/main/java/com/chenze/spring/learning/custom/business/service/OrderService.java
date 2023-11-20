package com.chenze.spring.learning.custom.business.service;

import com.chenze.spring.learning.custom.business.model.User;
import com.chenze.spring.learning.custom.framework.Autowired;
import com.chenze.spring.learning.custom.framework.Component;
import com.chenze.spring.learning.custom.framework.Scope;

import static com.chenze.spring.learning.custom.framework.Constants.*;

/**
 * @author chenze
 * @date 2023/11/17 0:21
 */
@Component("orderService")
@Scope(SCOPE_PROTOTYPE)
public class OrderService {

    @Autowired
    private UserService userService;


    public void shop(){
        User user = userService.getUser();
        System.out.println("用户：" + user);

        System.out.println("----------开始购物----------");
        System.out.println("----------完成购物----------");
    }



}
