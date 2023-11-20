package com.chenze.spring.learning.custom.test;

import com.chenze.spring.learning.custom.business.config.AppConfig;
import com.chenze.spring.learning.custom.business.service.OrderService;
import com.chenze.spring.learning.custom.business.service.UserService;
import com.chenze.spring.learning.custom.framework.MyApplicationContext;

/**
 * @author chenze
 * @date 2023/11/17 0:16
 */
public class MainTest {

    public static void main(String[] args) throws Exception {
        MyApplicationContext applicationContext = new MyApplicationContext(AppConfig.class);

        OrderService orderService = (OrderService)applicationContext.getBean("orderService");
        OrderService orderService1 = (OrderService)applicationContext.getBean("orderService");
        UserService userService = (UserService)applicationContext.getBean("userService");

        System.out.println(orderService);
        System.out.println(orderService1);

        orderService.shop();


    }

}
