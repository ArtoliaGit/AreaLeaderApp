package com.bsoft.arealeaderapp.api;

import com.bsoft.arealeaderapp.common.CustomApiResult;
import com.bsoft.arealeaderapp.entity.User;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("/user/getUserInfo")
    Call<CustomApiResult<User>> getUser();
}
