package com.chenze.spring.learning.custom.framework;

/**
 * @author chenze
 * @date 2023/11/17 2:14
 */
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;


}
