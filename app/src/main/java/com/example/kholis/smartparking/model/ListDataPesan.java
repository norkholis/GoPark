package com.example.kholis.smartparking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by norkholis on 16/02/18.
 */

public class ListDataPesan {
    @SerializedName("data")
    @Expose
    private List<DataPesan> data = null;


    public List<DataPesan> getData() {
        return data;
    }

    public void setData(List<DataPesan> data) {
        this.data = data;
    }

}
