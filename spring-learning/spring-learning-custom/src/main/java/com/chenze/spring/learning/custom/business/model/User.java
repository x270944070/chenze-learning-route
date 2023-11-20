package com.chenze.spring.learning.custom.business.model;

import java.io.Serializable;

/**
 * @author chenze
 * @date 2023/11/17 0:17
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1822201365341462746L;

    private long id;

    private String userName;

    private int age;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
