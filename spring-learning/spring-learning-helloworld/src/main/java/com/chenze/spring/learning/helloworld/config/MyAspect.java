package com.chenze.spring.learning.helloworld.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author chenze
 * @date 2023/11/18 19:17
 */
@Aspect
public class MyAspect {

    @Around("execution(* com.chenze.spring.learning.helloworld.service..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("执行切面方法-方法执行前");
        Object result = joinPoint.proceed();
        System.out.println("执行切面方法-方法执行后");
        return result;
    }

}
