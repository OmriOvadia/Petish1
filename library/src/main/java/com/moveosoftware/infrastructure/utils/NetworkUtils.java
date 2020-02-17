package com.moveosoftware.infrastructure.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by eliran on 03/02/2018.
 */

public class NetworkUtils {

    private static NetworkUtils instance;
    private Context mContex;

    private NetworkUtils(Context context) {
        this.mContex = context;
    }

    public static NetworkUtils getInstance(Context context) {
        if (instance == null) {
            instance = new NetworkUtils(context);
        }
        return instance;
    }

    public boolean isNetworkStatusAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContex.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

    }
}
