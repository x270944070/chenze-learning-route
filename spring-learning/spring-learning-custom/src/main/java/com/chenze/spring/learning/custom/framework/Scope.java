package com.chenze.spring.learning.custom.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.chenze.spring.learning.custom.framework.Constants.SCOPE_SINGLETON;

/**
 * @author chenze
 * @date 2023/11/17 0:36
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Scope {

    String value() default SCOPE_SINGLETON;

}
