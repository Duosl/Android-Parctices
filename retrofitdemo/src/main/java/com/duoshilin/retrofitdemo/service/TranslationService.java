package com.duoshilin.retrofitdemo.service;

import com.duoshilin.retrofitdemo.model.Translation;
import com.duoshilin.retrofitdemo.model.Translation1;
import com.duoshilin.retrofitdemo.model.Translation2;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by duoshilin on 2019/2/18.
 */

public interface TranslationService {

    @GET("ajax.php?a=fy&f=auto&t=auto")
    Call<Translation> translate(@Query("w") String target);

    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Call<Translation1> translate1(@Field("i") String target);

    //接口需要salt等字段
    @POST("translate_o?smartresult=dict&smartresult=rule")
    @FormUrlEncoded
    Call<Translation2> translate2(@Field("i") String target);
}
