package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {
    @GET("/user/save")
    Call<com.example.myapplication.HttpResult<Boolean>> gotoRegister(@Query("account")String account,@Query("password")String password);
    @GET("/user/login")
    Call<com.example.myapplication.HttpResult<Boolean>> gotoLogin(@Query("account")String account,@Query("password")String password);
    @GET("/news/get")
    Call<com.example.myapplication.HttpResult<List<News>>> getNews();
}
