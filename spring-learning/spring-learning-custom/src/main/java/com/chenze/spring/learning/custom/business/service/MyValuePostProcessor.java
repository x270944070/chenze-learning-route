package com.chenze.spring.learning.custom.business.service;

import com.chenze.spring.learning.custom.framework.BeanPostProcessor;
import com.chenze.spring.learning.custom.framework.Component;
import com.chenze.spring.learning.custom.framework.MyValue;

import java.lang.reflect.Field;

/**
 * 实现@Value初始化赋值
 * @author chenze
 * @date 2023/11/19 14:39
 */
@Component
public class MyValuePostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {

        for (Field field : bean.getClass().getSuperclass().getDeclaredFields()) {
            if (field.isAnnotationPresent(MyValue.class)) {
                field.setAccessible(true);
                field.set(bean, field.getAnnotation(MyValue.class).value());
            }
        }

        return bean;
    }
}
