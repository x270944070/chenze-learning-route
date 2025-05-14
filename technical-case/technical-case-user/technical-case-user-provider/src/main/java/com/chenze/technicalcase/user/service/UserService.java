package com.chenze.technicalcase.user.service;

import com.chenze.technicalcase.user.api.UserServiceApi;
import com.chenze.technicalcase.user.model.request.QueryUserInfoRequest;
import com.chenze.technicalcase.user.model.response.UserInfoResponse;

public class UserService implements UserServiceApi {

    @Override
    public UserInfoResponse queryUserInfoByUserName(QueryUserInfoRequest request) {
        return null;
    }
}
