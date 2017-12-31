package com.example.kholis.smartparking.helper;

import com.example.kholis.smartparking.model.APIUser;
import com.pixplicity.easyprefs.library.Prefs;

/**
 * Created by norkholis on 28/12/17.
 */

public class Helper {
    public static final String KEY_SESSION = "session";
    public static final String KEY_NAME = "nama_lengkap";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_UID = "uid";
    public static final String KEY_ID = "id_pengguna";
    public static final String KEY_PHONE = "telpon";
    public static final String KEY_USERNAME = "username";
    public static final String PUSH_NOTIF_RECEIVED = "notifReceived";
    public static final String PUSH_NOTIF_OPENED = "notifOpened";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_ALAMAT = "alamat";

    public static void setActiveUser(APIUser apiuser){
        Prefs.putString(KEY_USERNAME, apiuser.getUsername());
        Prefs.putString(KEY_ID, apiuser.getIdPengguna());
        Prefs.putString(KEY_NAME, apiuser.getNamaLengkap());
        Prefs.putString(KEY_EMAIL, apiuser.getEmail());
        Prefs.putString(KEY_PASSWORD, apiuser.getPassword());
        Prefs.putString(KEY_PHONE, apiuser.getNoTelp());
        Prefs.putString(KEY_IMAGE, apiuser.getFoto());
        Prefs.putString(KEY_ALAMAT, apiuser.getAlamat());
    }

    public static void unsetActivUser(){
        Prefs.putString(KEY_ID, "");
        Prefs.putString(KEY_NAME, "");
        Prefs.putString(KEY_EMAIL, "");
        Prefs.putString(KEY_PASSWORD, "");
        Prefs.putString(KEY_PHONE,"");
        Prefs.putString(KEY_IMAGE, "");
        Prefs.putString(KEY_ALAMAT,"");
        Prefs.clear();
    }

    public static APIUser getActiveUser(){
        return new APIUser(Prefs.getString(KEY_USERNAME,""),
                Prefs.getString(KEY_ID,""),
                Prefs.getString(KEY_NAME,""),
                Prefs.getString(KEY_ALAMAT,""),
                Prefs.getString(KEY_IMAGE,""),
                Prefs.getString(KEY_EMAIL,""),
                Prefs.getString(KEY_PASSWORD,""),
                Prefs.getString(KEY_PHONE,""));
    }
}
