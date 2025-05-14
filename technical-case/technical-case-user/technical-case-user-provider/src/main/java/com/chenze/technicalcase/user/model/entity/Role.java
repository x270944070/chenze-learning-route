package com.chenze.technicalcase.user.model.entity;

import java.util.Date;
import lombok.Data;

/**
 * 角色表
 */
@Data
public class Role {
    /**
    * 主键
    */
    private Long id;

    /**
    * 角色名称（如ROLE_USER、ROLE_ADMIN）
    */
    private String name;

    /**
    * 角色描述
    */
    private String description;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;
}