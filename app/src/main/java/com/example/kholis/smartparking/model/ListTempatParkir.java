package com.example.kholis.smartparking.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by norkholis on 12/02/18.
 */

public class ListTempatParkir {
    @SerializedName("data")
    private List<DataTempat> mData;

    public ListTempatParkir(List<DataTempat> mData) {
        this.mData = mData;
    }

    public ListTempatParkir(){

    }

    public List<DataTempat> getmData(){
        return mData;
    }

    public void setmData(List<DataTempat> mData){
        this.mData = mData;
    }
}
