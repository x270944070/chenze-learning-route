package com.chenze.technicalcase.user.mapper;

import com.chenze.technicalcase.user.model.entity.Role;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int batchInsert(@Param("list") List<Role> list);

    List<Role> selectByIdList(@Param("idList") List<Long> idList);

}