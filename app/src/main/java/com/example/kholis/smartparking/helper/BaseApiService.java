package com.example.kholis.smartparking.helper;

import com.example.kholis.smartparking.model.APIUser;
import com.example.kholis.smartparking.model.DataKendaraan;
import com.example.kholis.smartparking.model.DataTempat;
import com.example.kholis.smartparking.model.ListDataHistory;
import com.example.kholis.smartparking.model.ListDataPesan;
import com.example.kholis.smartparking.model.ListRespReg;
import com.example.kholis.smartparking.model.ListTempatParkir;
import com.example.kholis.smartparking.model.ListUser;
import com.example.kholis.smartparking.model.RespRegStatus;
import com.example.kholis.smartparking.model.ResponseKendaraan;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by norkholis on 25/12/17.
 */

public interface BaseApiService {
    @FormUrlEncoded
    @POST("login")
    Call<ListUser> loginRequest(@Field("username") String username,
                                @Field("password")String password);

    @FormUrlEncoded
    @POST("registrasi")
    Call<ListRespReg> registerRequest(@Field("nama_lengkap") String nama_lengkap,
                                        @Field("email") String email,
                                        @Field("alamat") String alamat,
                                        @Field("no_telp") String no_telp,
                                        @Field("username") String username,
                                        @Field("password") String password,
                                        @Field("verifikasi_pengguna")String verifikasi_pengguna,
                                        @Field("disabled_key")String disabled_key);

    @GET("kendaraan/{id}")
    Call<List<DataKendaraan>>getSemuakendaraan(@Path("id") int id
                                            ,@Query("_token")String token);

    @GET("tempat")
    Call<ListTempatParkir>getTempatParkir(@Query("_token")String token);

    @FormUrlEncoded
    @POST("history")
    Call<ListDataHistory>getDataHistory(@Query("_token")String token,
                                        @Field("id_pengguna") int id_pengguna);

    @FormUrlEncoded
    @POST("pesan")
    Call<ListDataPesan>getDataPesan(@Query("_token")String token,
                                    @Field("id_pengguna")int id_pengguna,
                                    @Field("id_kendaraan")int id_kendaraan,
                                    @Field("id_tempat")int id_tempatParkir);

}
