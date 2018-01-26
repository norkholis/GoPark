package com.example.kholis.smartparking.helper;

/**
 * Created by norkholis on 25/12/17.
 */

public class ApiUtils {
    public static final String BASE_URL_API = "80.211.135.231/mobile_parking/public/api/";

    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
