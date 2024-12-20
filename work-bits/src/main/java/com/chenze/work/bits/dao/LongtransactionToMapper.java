package com.chenze.work.bits.dao;

import com.chenze.work.bits.model.entity.LongtransactionTo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LongtransactionToMapper {
    int deleteByPrimaryKey(String userCode);

    int insertSelective(LongtransactionTo record);

    LongtransactionTo selectByPrimaryKey(String userCode);

    int updateByPrimaryKeySelective(LongtransactionTo record);

    int batchInsert(@Param("list") List<LongtransactionTo> list);
}