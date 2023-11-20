package com.chenze.spring.learning.helloworld.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * @author chenze
 * @date 2023/11/18 10:33
 */
@Component
public class OrderService implements InitializingBean {

    @Autowired
    private UserService userService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init(){
        System.out.println("OrderService初始化......使用@PostConstruct执行初始化方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("OrderService初始化......使用InitializingBean执行初始化方法");
    }

    public OrderService() {
        System.out.println("当前类实例化.....空方法");
    }

    /**
     * 执行使用了Autowired方法进行实例化
     */
    @Autowired
    public OrderService(UserService userService) {
        System.out.println("当前类实例化.....使用@Autowired注解");
        this.userService = userService;
    }


    public OrderService(String str) {
        System.out.println("当前类实例化.....str");
    }




    public void shop(){
        System.out.println("业务方法执行中......");
        userService.test();
        System.out.println("购物中....");
        System.out.println("购物完成");
    }


    @Transactional
    public void exeShop(){
        System.out.println("减少库存......");
        jdbcTemplate.execute("update goods_info set num = num - 1 where id = '1'");
        System.out.println("减少库存完成");
        System.out.println("减少钱包余额......");
        jdbcTemplate.execute("update user_info set account_price = (account_price - (select price from goods_info where id = '1')) where id = '1'");
        throw new NullPointerException();
    }


}
