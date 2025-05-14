package com.chenze.technicalcase.user.model.entity;

import java.util.Date;
import lombok.Data;

/**
 * 用户-角色关联表
 */
@Data
public class UserRole {
    /**
    * 主键
    */
    private Long id;

    /**
    * 用户ID
    */
    private Long userId;

    /**
    * 角色ID
    */
    private Long roleId;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;
}