package com.example.androidtutorial;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BindService extends AppCompatActivity
{
    private MyBindService myBindService;

    private ServiceConnection mConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder)
        {
            MyBindService.LocalBinder binder = (MyBindService.LocalBinder) iBinder;
            myBindService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName)
        {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bind_service);

        findViewById(R.id.btn_bind_service).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mConnection != null)
                {
                    int lottery = myBindService.lottery();
                    Log.d("YiMin", "" + lottery);
                }
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Intent intent = new Intent(this, MyBindService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        unbindService(mConnection);
    }
}
