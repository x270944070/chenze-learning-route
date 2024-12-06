package com.chenze.work.bits.controller;

import com.chenze.work.bits.common.BaseResponse;
import com.chenze.work.bits.model.entity.LongtransactionFrom;
import com.chenze.work.bits.model.request.HelloWorldReq;
import com.chenze.work.bits.model.response.HelloWorldRsp;
import com.chenze.work.bits.service.MainService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/longtransaction")
public class MainController {

    @Resource
    private MainService mainService;

    @PostMapping("/helloWorld")
    public BaseResponse<HelloWorldRsp> helloWorld(@RequestBody HelloWorldReq req){
        LongtransactionFrom longtransactionFrom = mainService.findOne(req.getUserCode());

        if (longtransactionFrom == null) {
            return BaseResponse.success();
        }

        HelloWorldRsp rsp = new HelloWorldRsp();
        BeanUtils.copyProperties(longtransactionFrom, rsp);
        return BaseResponse.success(rsp);

    }

    @PostMapping("/exe")
    public BaseResponse<Void> exe() {
        new Thread(() -> {
            try {
                mainService.longtransactionExe();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        return BaseResponse.success();

    }

}
