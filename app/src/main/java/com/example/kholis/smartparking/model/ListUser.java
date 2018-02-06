package com.example.kholis.smartparking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by norkholis on 05/02/18.
 */

public class ListUser {
    @SerializedName("data")
    @Expose
    private ArrayList<APIUser> user = new ArrayList<>();

    public ArrayList<APIUser> getData() {
        return user;
    }

    public void setData(ArrayList<APIUser> user) {
        this.user = user;
    }
}
