package com.chenze.technicalcase.user.mapper;

import com.chenze.technicalcase.user.model.entity.LoginQrCode;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

public interface LoginQrCodeMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(LoginQrCode record);

    LoginQrCode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LoginQrCode record);

    int batchInsert(@Param("list") List<LoginQrCode> list);
}