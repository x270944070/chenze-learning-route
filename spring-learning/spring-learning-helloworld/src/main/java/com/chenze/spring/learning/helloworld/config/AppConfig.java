package com.chenze.spring.learning.helloworld.config;

import com.chenze.spring.learning.helloworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author chenze
 * @date 2023/11/18 10:47
 */
@ComponentScan("com.chenze.spring.learning.helloworld.service")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class AppConfig {

    /*@Bean
    public UserService userService(){
        return new UserService();
    }*/

    /**
     * 接口切面
     */
    @Bean
    public MyAspect myAspect(){
        return new MyAspect();
    }

    /**
     * 数据源
     */
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://124.71.47.75:3306/chenze_learning_route?characterEncoding=utf-8&sslMode=DISABLED");
        dataSource.setUsername("root");
        dataSource.setPassword("Cz1234");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Autowired DataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}
