package com.example.kholis.smartparking.helper;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

import com.pixplicity.easyprefs.library.Prefs;

/**
 * Created by norkholis on 28/12/17.
 */

public class GoParkApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        GoParkApplication.context = getApplicationContext();

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static Context getAppContext(){
        return GoParkApplication.context;
    }
}
