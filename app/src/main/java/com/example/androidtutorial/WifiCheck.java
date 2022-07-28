package com.example.androidtutorial;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * <user-permission android:name="android.permission.INTERNET"/>
 * <user-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 */

public class WifiCheck extends AppCompatActivity {
    private ConnectivityManager connectivityManager;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (isConnectNetWork()) {
            Log.d("apple", "is wifi " + isWifiConnected());
        }
    }

    private boolean isConnectNetWork() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    private boolean isWifiConnected() {
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return networkInfo.isConnected();
    }
}
