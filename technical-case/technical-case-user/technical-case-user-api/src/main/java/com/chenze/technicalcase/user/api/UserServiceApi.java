package com.chenze.technicalcase.user.api;

import com.chenze.common.model.response.BaseResponse;
import com.chenze.technicalcase.user.model.request.QueryUserInfoRequest;
import com.chenze.technicalcase.user.model.request.UserRegisterRequest;
import com.chenze.technicalcase.user.model.response.UserInfoResponse;
import jakarta.validation.constraints.NotNull;

public interface UserServiceApi {

    UserInfoResponse queryUserInfoByUserName(@NotNull QueryUserInfoRequest request);

    BaseResponse<Void> register(@NotNull UserRegisterRequest request);

}
