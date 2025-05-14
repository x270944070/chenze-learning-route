create table user
(
    id                      bigint auto_increment primary key comment '主键',
    username                varchar(50)  not null default '' comment '用户名',
    password                varchar(255) not null default '' comment '加密后的密码（BCrypt）',
    enabled                 tinyint      not null default 1 comment '是否启用：0-禁用，1-启用',
    account_non_expired     tinyint      not null default 1 comment '账号是否未过期：0-已过期，1-未过期',
    credentials_non_expired tinyint      not null default 1 comment '凭证是否未过期：0-已过期，1-未过期',
    account_non_locked      tinyint      not null default 1 comment '账号是否未锁定：0-已锁定，1-未锁定',
    create_time             timestamp    not null default current_timestamp comment '创建时间',
    update_time             timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',
    unique key uniq_users_username (username)
) comment ='用户表';

create table role
(
    id          bigint auto_increment primary key comment '主键',
    name        varchar(50)  not null default '' comment '角色名称（如ROLE_USER、ROLE_ADMIN）',
    description varchar(100) not null default '' comment '角色描述',
    create_time timestamp    not null default current_timestamp comment '创建时间',
    update_time timestamp    not null default current_timestamp on update current_timestamp comment '更新时间',
    unique key uniq_roles_name (name)
) comment ='角色表';


create table user_role
(
    id          bigint auto_increment primary key comment '主键',
    user_id     bigint    not null default 0 comment '用户ID',
    role_id     bigint    not null default 0 comment '角色ID',
    create_time timestamp not null default current_timestamp comment '创建时间',
    update_time timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
    unique key uniq_user_roles_user_role (user_id, role_id),
    key idx_user_roles_user_id (user_id),
    key idx_user_roles_role_id (role_id)
) comment ='用户-角色关联表';


create table login_qr_code
(
    id          bigint auto_increment primary key comment '主键',
    code        varchar(100) not null default '' comment '二维码唯一标识（如UUID）',
    user_id     bigint       not null default 0 comment '绑定的用户ID',
    status      tinyint      not null default 0 comment '二维码状态：0-等待中，1-已扫码，2-已确认，3-已过期',
    expire_time timestamp    not null comment '二维码有效期',
    create_time timestamp    not null default current_timestamp comment '创建时间',
    unique key uniq_login_qr_code_code (code),
    key idx_login_qr_code_user_id (user_id)
) comment ='扫码登录二维码表';
