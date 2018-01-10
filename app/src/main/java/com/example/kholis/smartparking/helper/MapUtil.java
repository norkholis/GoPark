package com.example.kholis.smartparking.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by norkholis on 07/01/18.
 */

public final class MapUtil {
    public static final class Operation{
        private Operation() throws InstantiationException {
            throw new InstantiationException("This class is not for instantiation");
        }

        public static boolean isOnline(Context context){
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                return true;
            }
            return false;
        }
    }
    private MapUtil()throws InstantiationException {
        throw new InstantiationException("This class is not for instantiation");
    }
}
