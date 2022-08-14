package com.example.androidtutorial;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service
{
    private Timer timer;
    private int i, maxi;

    public MyService()
    {
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        timer = new Timer();
        i = 0;
        maxi = 10000;

        Intent intent = new Intent("apple");
        intent.putExtra("max", maxi);
        sendBroadcast(intent);

        timer.schedule(new MyTask(), 100, 100);

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        int newi = intent.getIntExtra("newi", -1);
        if (newi > 0)
        {
            i = newi;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        if (timer != null)
        {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }

    private class MyTask extends TimerTask
    {
        @Override
        public void run()
        {
            i++;
            if (i >= maxi)
            {
                cancel();
            }
            else
            {
                Intent intent = new Intent("apple");
                intent.putExtra("i", i);
                sendBroadcast(intent);
            }
        }
    }
}