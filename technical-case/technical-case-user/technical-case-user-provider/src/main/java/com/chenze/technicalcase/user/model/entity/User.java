package com.chenze.technicalcase.user.model.entity;

import java.util.Date;
import lombok.Data;

/**
 * 用户表
 */
@Data
public class User {
    /**
    * 主键
    */
    private Long id;

    /**
    * 用户名
    */
    private String username;

    /**
    * 加密后的密码（BCrypt）
    */
    private String password;

    /**
    * 是否启用：0-禁用，1-启用
    */
    private Byte enabled;

    /**
    * 账号是否未过期：0-已过期，1-未过期
    */
    private Byte accountNonExpired;

    /**
    * 凭证是否未过期：0-已过期，1-未过期
    */
    private Byte credentialsNonExpired;

    /**
    * 账号是否未锁定：0-已锁定，1-未锁定
    */
    private Byte accountNonLocked;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;
}