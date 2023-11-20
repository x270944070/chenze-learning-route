package com.chenze.spring.learning.helloworld.test;

import com.chenze.spring.learning.helloworld.config.AppConfig;
import com.chenze.spring.learning.helloworld.service.OrderService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author chenze
 * @date 2023/11/18 10:31
 */
public class MainTest {

    /**
     * ClassPathApplicationContext的测试类
     */
    @Test
    public void classPathApplicationContextTest(){
        ApplicationContext applicationContext =  new ClassPathXmlApplicationContext("classpath:spring.xml");
        OrderService orderService = (OrderService)applicationContext.getBean("orderService");
        orderService.shop();
    }

    /**
     * AnnotationConfigApplicationContext的测试类
     */
    @Test
    public void annotationConfigApplicationContextTest(){
        ApplicationContext applicationContext =  new AnnotationConfigApplicationContext(AppConfig.class);
        /**
         * org.springframework.beans.factory.BeanFactory#getBean(java.lang.String)
         * 这个方法获取的并不是OrderService本身，而是获取的代理对象。
         * 可以理解成获取的实际是OrderService的子类
         */
        OrderService orderService = (OrderService)applicationContext.getBean("orderService");
        orderService.shop();
    }

    @Test
    public void transactionalTest(){
        ApplicationContext applicationContext =  new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService = (OrderService)applicationContext.getBean("orderService");
        /**
         * 1. 判断方法是否使用了@Transactional
         * 2. 创建一个数据库连接connection（事务管理器创建）
         * 3. connection.autocommit = false
         * 4. 方法执行完成后，执行connection.commit()
         * 5. 如果方法报错，执行connection.rollback()
         */
        orderService.exeShop();
    }


}
