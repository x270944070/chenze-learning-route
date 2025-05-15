package com.chenze.technicalcase.web.controller;

import cn.hutool.core.util.IdUtil;
import com.chenze.common.model.response.BaseResponse;
import com.chenze.technicalcase.user.api.UserServiceApi;
import com.chenze.technicalcase.user.model.request.UserRegisterRequest;
import com.chenze.technicalcase.web.model.request.UserLoginWebReq;
import com.chenze.technicalcase.web.model.request.UserRegisterWebReq;
import com.chenze.technicalcase.web.utils.JwtUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Resource
    private PasswordEncoder passwordEncoder;
    @DubboReference
    private UserServiceApi userServiceApi;
    @Resource
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public BaseResponse<Void> register(@RequestBody UserRegisterWebReq req) {

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername(req.getUsername());
        userRegisterRequest.setPassword(passwordEncoder.encode(req.getPassword()));
        userRegisterRequest.setRoleId(req.getRoleId());

        return userServiceApi.register(userRegisterRequest);
    }


    @GetMapping("/fastRegister")
    public BaseResponse<String> fastRegister() {

        String username = IdUtil.fastSimpleUUID();

        UserRegisterWebReq registerWebReq = new UserRegisterWebReq();
        registerWebReq.setUsername(username);
        registerWebReq.setPassword("123456");
        registerWebReq.setRoleId(1L);
        register(registerWebReq);
        return BaseResponse.success(username);
    }


    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody UserLoginWebReq req) {
        String username = req.getUsername();
        String password = req.getPassword();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = JwtUtil.generateToken(username);
            return BaseResponse.success(token);
        } catch (AuthenticationException e) {
            return BaseResponse.error("用户名或密码错误");
        }
    }

}
