package com.sd.style.common.api;

import com.sd.style.module.user.response.LoginResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;

/**
 * @author: rae.ho
 * @description:  http接口
 * @date 2017/7/31  19:16
 */

public interface ApiRetrofit {

    @GET("/")
    Observable<LoginResponse> login(@FieldMap Map<String, String> map);
}
