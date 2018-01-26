package com.example.kholis.smartparking.model;

import com.example.kholis.smartparking.item.SemuaKendaraanItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by norkholis on 26/01/18.
 */

public class ResponseKendaraan {
    @SerializedName("semuakendaraan")
    private List<SemuaKendaraanItem> semuakendaraan;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public void setSemuakendaraan(List<SemuaKendaraanItem> semuakendaraan){
        this.semuakendaraan = semuakendaraan;
    }

    public List<SemuaKendaraanItem> getSemuakendaraan(){
        return semuakendaraan;
    }

    public void setError(boolean error){
        this.error=error;
    }

    public boolean isError(){
        return error;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    @Override
    public String toString() {
        return
                "ResponseKendaraan{"+
                        "semuakendaraan='"+semuakendaraan +'\''+
                        ",error = '" + error + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}
