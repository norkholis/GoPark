package com.example.kholis.smartparking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by norkholis on 14/02/18.
 */

public class ListDataHistory {

    @SerializedName("status")
    @Expose
    private StatusHistory status;
    @SerializedName("data")
    @Expose
    private List<DataHistory> data = null;

    public StatusHistory getStatus() {
        return status;
    }

    public void setStatus(StatusHistory status) {
        this.status = status;
    }

    public List<DataHistory> getData() {
        return data;
    }

    public void setData(List<DataHistory> data) {
        this.data = data;
    }
}
