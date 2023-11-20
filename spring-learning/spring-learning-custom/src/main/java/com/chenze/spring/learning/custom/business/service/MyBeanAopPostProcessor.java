package com.chenze.spring.learning.custom.business.service;

import com.chenze.spring.learning.custom.framework.BeanPostProcessor;
import com.chenze.spring.learning.custom.framework.Component;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
 * 模拟实现AOP逻辑
 * @author chenze
 * @date 2023/11/17 2:22
 */
@Component
public class MyBeanAopPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bean.getClass());
        Object target = bean;
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("切面执行前");
                Object result = method.invoke(target, objects);
                System.out.println("切面执行后");
                return result;
            }
        });
        return enhancer.create();



        /*return Proxy.newProxyInstance(this.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("切面执行前");
                Object result = method.invoke(bean, args);
                System.out.println("切面执行后");
                return result;
            }
        });*/

    }
}
