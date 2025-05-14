package com.chenze.spring.security.learning.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PreDestroy;

/**
 * 登录Controller
 * @author chenze
 * @date 2024/8/23 21:49
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/toMain", method = RequestMethod.POST)
    public String toMain(){
        logger.info("登录成功");
        return "redirect:main.html";
    }

    @RequestMapping(value = "/toError", method = RequestMethod.POST)
    public String toError(){
        logger.info("登录失败");
        return "redirect:error.html";
    }

    /**
     * 方法注解判断权限
     * @return
     */
    @RequestMapping(value = "/annotationSecured")
    @ResponseBody
    @Secured("ROLE_admin")
    public String annotationSecured(){
        logger.info("注解权限控制Secured");
        return "{\"msg\":\"注解权限控制Secured\"}";
    }


    /**
     * 先判断权限，再执行业务代码
     */
    @RequestMapping(value = "/annotationPreAuthorize")
    @ResponseBody
    @PreAuthorize("hasRole(\"admin\")")
    public String annotationPreAuthorize(){
        logger.info("注解权限控制PreAuthorize");
        return "{\"msg\":\"注解权限控制PreAuthorize\"}";
    }

    /**
     * 先执行业务代码，再判断权限
     */
    @RequestMapping(value = "/annotationPostAuthorize")
    @ResponseBody
    @PostAuthorize("hasRole(\"admin\")")
    public String annotationPostAuthorize(){
        logger.info("注解权限控制PostAuthorize");
        return "{\"msg\":\"注解权限控制PostAuthorize\"}";
    }



    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/showLogin")
    public String showLogin(){
        return "login";
    }


}
