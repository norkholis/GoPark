package com.example.kholis.smartparking.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by norkholis on 25/12/17.
 */

public class SharedPrefManager {
    public static final String SP_GOPARK_APP = "spGoparkApp";

    public static final String SP_NAMA = "spName";
    public static final String SP_USERNAME = "spUsername";
    public static final String SP_EMAIL = "spEmail";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_GOPARK_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSpNama(){
        return sp.getString(SP_NAMA,"");
    }

    public String getSpUsername(){
        return sp.getString(SP_USERNAME,"");
    }

    public String getSpEmail(){
        return sp.getString(SP_EMAIL,"");
    }

    public Boolean getSpSudahLogin(){
        return  sp.getBoolean(SP_SUDAH_LOGIN,false);
    }

}
