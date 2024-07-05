package com.chenze.technical.test.longtransaction.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class HelloWorldRsp implements Serializable {
    private static final long serialVersionUID = 8630799011759508801L;


    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 登录账号
     */
    private String userUid;

    /**
     * 登录密码
     */
    private String userPass;

    /**
     * 用户类别
     */
    private Integer userType;

    /**
     * 用户状态
     */
    private Integer userState;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String userNick;

    /**
     * 登陆手机
     */
    private String userMobile;

    /**
     * 登陆邮箱
     */
    private String userEmail;

    /**
     * 个性签名
     */
    private String userSign;

    /**
     * 头像地址
     */
    private String userLogourl;

    /**
     * 用户等级
     */
    private Integer userRank;

    /**
     * 用户信誉度
     */
    private Integer userCredit;

    /**
     * 用户活跃度
     */
    private Integer userVitality;

    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 是否通过认证
     */
    private Byte certified;

    /**
     * 请求认证说明
     */
    private String certifyMemo;

    /**
     * 请求认证文件
     */
    private String certifyFile;

    /**
     * 认证通过时间
     */
    private Long certifyDate;

    /**
     * 加入是否需要验证
     */
    private Byte needConfirm;

    /**
     * 总共登陆次数
     */
    private Integer loginTimes;

    /**
     * 最后登陆IP
     */
    private String updateAddr;

    /**
     * 最后登陆设备
     */
    private String updateDev;

    /**
     * 最后登陆时间
     */
    private Long updateDate;

    /**
     * 账号创建时间
     */
    private Long createDate;

    /**
     * 有效标识
     */
    private Byte isvalid;

    /**
     * 最后登陆App
     */
    private String updateApp;

    /**
     * 最后登陆位置
     */
    private String updateLoc;

    private String idcard;

    private String voipExp;

    private String voipLoc;

    private Integer certifyState;

    private String cancelReason;

    /**
     * 注销凭证
     */
    private String cancelImg;

    /**
     * 最后更新人
     */
    private String updateUser;

    /**
     * 密码安全等级 0：初始值 1:低级 2：高级
     */
    private Byte passwordLevel;

    private String reverseUserMobile;

    /**
     * 留底照片
     */
    private String photo;

}
