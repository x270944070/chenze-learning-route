package com.chenze.work.bits.dao;

import com.chenze.work.bits.model.entity.LongtransactionFrom;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LongtransactionFromMapper {
    int deleteByPrimaryKey(String userCode);

    int insertSelective(LongtransactionFrom record);

    LongtransactionFrom selectByPrimaryKey(String userCode);

    List<LongtransactionFrom> selectLimit(@Param("start") int start, @Param("pageSize") int pageSize);

    int updateByPrimaryKeySelective(LongtransactionFrom record);

    int batchInsert(@Param("list") List<LongtransactionFrom> list);




}