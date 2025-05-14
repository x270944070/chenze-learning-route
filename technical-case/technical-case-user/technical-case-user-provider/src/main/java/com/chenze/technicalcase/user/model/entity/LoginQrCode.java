package com.chenze.technicalcase.user.model.entity;

import java.util.Date;
import lombok.Data;

/**
 * 扫码登录二维码表
 */
@Data
public class LoginQrCode {
    /**
    * 主键
    */
    private Long id;

    /**
    * 二维码唯一标识（如UUID）
    */
    private String code;

    /**
    * 绑定的用户ID
    */
    private Long userId;

    /**
    * 二维码状态：0-等待中，1-已扫码，2-已确认，3-已过期
    */
    private Byte status;

    /**
    * 二维码有效期
    */
    private Date expireTime;

    /**
    * 创建时间
    */
    private Date createTime;
}