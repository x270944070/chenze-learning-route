package com.chenze.learning.mybatis.model;

import lombok.Data;

@Data
public class Employee {

    private Integer id;
    private String lastName; //注意：与数据表字段不一样,可以使用别名
    private String email;
    private String gender;

}
