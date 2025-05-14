package com.chenze.technicalcase.user.api;

import com.chenze.technicalcase.user.model.request.QueryUserInfoRequest;
import com.chenze.technicalcase.user.model.response.UserInfoResponse;

public interface UserServiceApi {

    UserInfoResponse queryUserInfoByUserName(QueryUserInfoRequest request);


}
