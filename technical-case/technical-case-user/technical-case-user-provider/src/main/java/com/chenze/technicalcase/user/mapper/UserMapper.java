package com.chenze.technicalcase.user.mapper;

import com.chenze.technicalcase.user.model.entity.User;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int batchInsert(@Param("list") List<User> list);

    User selectByUsername(@Param("username") String username);

    Long selectIdByMax();

}