package com.test.pharmeasy.pharmeasytestapp.model;

import com.test.pharmeasy.pharmeasytestapp.model.pojo.UsersData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface ApiInterface {

    @GET("api/users")
    Call<UsersData> getUsers(@Query("page") Integer page);
}
