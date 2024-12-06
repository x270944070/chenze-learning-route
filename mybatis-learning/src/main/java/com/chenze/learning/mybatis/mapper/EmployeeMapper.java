package com.chenze.learning.mybatis.mapper;

import com.chenze.learning.mybatis.model.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface EmployeeMapper {

    // 查询数据总数
    long countAllForLimit();

    List<Employee> selectAllForLimit(@Param("start") long start,
                                     @Param("pageSize") long pageSize);

    List<Employee> selectAllForRowBounds(RowBounds rowBounds);

    List<Employee> findAll();

}
