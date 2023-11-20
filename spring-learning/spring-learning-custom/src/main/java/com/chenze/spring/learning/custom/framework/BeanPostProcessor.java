package com.chenze.spring.learning.custom.framework;

/**
 * @author chenze
 * @date 2023/11/17 2:15
 */
public interface BeanPostProcessor {


    default Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    default Object postProcessAfterInitialization(Object bean, String beanName) throws Exception{
        return bean;
    }



}
