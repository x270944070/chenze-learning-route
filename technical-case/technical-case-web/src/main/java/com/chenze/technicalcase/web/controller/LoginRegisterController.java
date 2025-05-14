package com.chenze.technicalcase.web.controller;

import com.chenze.technicalcase.user.api.UserServiceApi;
import com.chenze.technicalcase.user.model.request.QueryUserInfoRequest;
import com.chenze.technicalcase.user.model.response.UserInfoResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loginRegister")
public class LoginRegisterController {


    @DubboReference
    private UserServiceApi userServiceApi;

    @GetMapping("/{username}")
    public UserInfoResponse getUserById(@PathVariable String username) {
        QueryUserInfoRequest queryUserInfoRequest = new QueryUserInfoRequest();
        queryUserInfoRequest.setUsername(username);
        return userServiceApi.queryUserInfoByUserName(queryUserInfoRequest);
    }


}
