package com.chenze.technical.test.longtransaction.controller;

import com.chenze.technical.test.common.BaseResponse;
import com.chenze.technical.test.longtransaction.model.entity.LongtransactionFrom;
import com.chenze.technical.test.longtransaction.model.request.HelloWorldReq;
import com.chenze.technical.test.longtransaction.model.response.HelloWorldRsp;
import com.chenze.technical.test.longtransaction.service.MainService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


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
