package com.example.kholis.smartparking.helper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by norkholis on 25/12/17.
 */

public interface BaseApiService {
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("password")String password);

    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registerRequest(@Field("nama_lengkap") String nama_lengkap,
                                       @Field("email") String email,
                                       @Field("username") String username,
                                       @Field("password") String password);



}
