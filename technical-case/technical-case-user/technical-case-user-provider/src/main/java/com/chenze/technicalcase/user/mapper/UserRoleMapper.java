package com.chenze.technicalcase.user.mapper;

import com.chenze.technicalcase.user.model.entity.UserRole;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int batchInsert(@Param("list") List<UserRole> list);

    List<UserRole> selectByUserId(@Param("userId") Long userId);
}