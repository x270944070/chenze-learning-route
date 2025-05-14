package com.chenze.technicalcase.user.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.chenze.common.model.response.BaseResponse;
import com.chenze.technicalcase.user.api.UserServiceApi;
import com.chenze.technicalcase.user.mapper.UserMapper;
import com.chenze.technicalcase.user.mapper.UserRoleMapper;
import com.chenze.technicalcase.user.model.entity.User;
import com.chenze.technicalcase.user.model.entity.UserRole;
import com.chenze.technicalcase.user.model.request.QueryUserInfoRequest;
import com.chenze.technicalcase.user.model.request.UserRegisterRequest;
import com.chenze.technicalcase.user.model.response.UserInfoResponse;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

@DubboService(interfaceClass = UserServiceApi.class)
public class UserService implements UserServiceApi {

//    @Resource
//    private UserMapper userMapper;
//
//    @Resource
//    private UserRoleMapper userRoleMapper;


    @Override
    public UserInfoResponse queryUserInfoByUserName(QueryUserInfoRequest request) {
//        String username = request.getUsername();
//
//        User user = userMapper.selectByUsername(username);
//        if (user == null) {
//            return null;
//        }
//
//        UserInfoResponse userInfoResponse = new UserInfoResponse();
//        userInfoResponse.setId(user.getId());
//        userInfoResponse.setUsername(user.getUsername());
//        userInfoResponse.setPassword(user.getPassword());
//
//        return userInfoResponse;
        return null;
    }

    /**
     * 用户注册
     * chenz 这里注册时，可以初始化一些其他信息用于实现[分布式事务]
     * @param request
     * @return 返回是否成功
     */
    @Override
    @Transactional
    public BaseResponse<Void> register(UserRegisterRequest request) {
//        // 检查用户名是否存在
//
//        User existUser = userMapper.selectByUsername(request.getUsername());
//
//        if (existUser != null) {
//            return BaseResponse.error("用户名已存在");
//        }
//
//        // 构建用户对象
//        User user = new User();
//        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
//        user.setEnabled((byte) 1);
//        user.setAccountNonExpired((byte) 1);
//        user.setAccountNonLocked((byte) 1);
//        user.setCredentialsNonExpired((byte) 1);
//        userMapper.insertSelective(user);
//
//        UserRole userRole = new UserRole();
//        userRole.setUserId(user.getId());
//        userRole.setRoleId(request.getRoleId());
//        userRoleMapper.insertSelective(userRole);

        return BaseResponse.success();
    }

}
