package com.chenze.sharding.learning.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName
@Data
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String uname;


}
