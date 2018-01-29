package com.example.kholis.smartparking.helper;

import com.example.kholis.smartparking.model.APIUser;
import com.example.kholis.smartparking.model.ResponseKendaraan;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by norkholis on 25/12/17.
 */

public interface BaseApiService {
    @FormUrlEncoded
    @POST("login")
    Call<List<APIUser>> loginRequest(@Field("username") String username,
                                     @Field("password")String password);

    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registerRequest(@Field("nama_lengkap") String nama_lengkap,
                                       @Field("email") String email,
                                       @Field("alamat") String alamat,
                                       @Field("no_telp") String no_telp,
                                       @Field("username") String username,
                                       @Field("password") String password);

    @GET("kendaraan")
    Call<ResponseKendaraan>getSemuakendaraan();



}
