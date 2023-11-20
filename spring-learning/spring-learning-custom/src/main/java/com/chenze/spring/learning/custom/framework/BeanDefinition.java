package com.chenze.spring.learning.custom.framework;

/**
 * @author chenze
 * @date 2023/11/17 0:34
 */
public class BeanDefinition {

    private String beanName;

    private Class<?> clazz;

    private String scope;


    // 暂不管Lazy


    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
