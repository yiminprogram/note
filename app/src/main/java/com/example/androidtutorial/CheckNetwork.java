package com.example.androidtutorial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CheckNetwork extends AppCompatActivity {
    private MyNetworkReceiver myNetworkReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_network);

        getNetworkStatus();
    }

    @Override
    protected void onStart() {
        super.onStart();

        myNetworkReceiver = new MyNetworkReceiver();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(myNetworkReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (myNetworkReceiver != null) {
            unregisterReceiver(myNetworkReceiver);
        }
    }

    private void getNetworkStatus() {
        Log.d("apple", "check network");
    }

    private class MyNetworkReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            getNetworkStatus();
        }
    }
}
