package com.example.androidtutorial;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyBindService extends Service
{
    private final Binder mBinder = new LocalBinder();
    public boolean isBind;

    public class LocalBinder extends Binder
    {
        MyBindService getService()
        {
            return MyBindService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        isBind = true;
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        isBind = false;
        return super.onUnbind(intent);
    }

    public int lottery()
    {
        return (int) (Math.random() * 49 + 1);
    }
}