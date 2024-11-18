package com.chenze.sharding.learning.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenze.sharding.learning.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
