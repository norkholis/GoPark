package com.example.kholis.smartparking.helper;

/**
 * Created by norkholis on 25/12/17.
 */

public class ApiUtils {
    public static final String BASE_URL_API = "http://api.igs-indonesia.com/index.php/pengguna";

    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
