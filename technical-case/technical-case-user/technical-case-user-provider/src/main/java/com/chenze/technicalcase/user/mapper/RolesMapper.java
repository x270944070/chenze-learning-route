package com.chenze.technicalcase.user.mapper;

import com.chenze.technicalcase.user.model.entity.Role;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

public interface RolesMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int batchInsert(@Param("list") List<Role> list);
}