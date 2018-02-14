package com.example.kholis.smartparking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by norkholis on 12/02/18.
 */

public class ListRespReg {
    @SerializedName("status")
    @Expose
    private ArrayList<RespRegStatus> statusReg = new ArrayList<>();

    public ArrayList<RespRegStatus> getStatusReg() {
        return statusReg;
    }

    public void setStatusReg(ArrayList<RespRegStatus> statusReg) {
        this.statusReg = statusReg;
    }
}
