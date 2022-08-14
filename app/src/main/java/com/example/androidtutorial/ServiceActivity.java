package com.example.androidtutorial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ServiceActivity extends AppCompatActivity
{
    private SeekBar seekBar;
    private MyReceiver myReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity);

        findViewById(R.id.btn_service).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startMyService();
            }
        });

        seekBar = findViewById(R.id.seekbar_service);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                if (b)
                {
                    Intent intent = new Intent(ServiceActivity.this, MyService.class);
                    intent.putExtra("newi", i);
                    startService(intent);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter("apple");
        registerReceiver(myReceiver, filter);
    }

    private void startMyService()
    {

        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    private class MyReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {
            int max = intent.getIntExtra("max", -1);
            int i = intent.getIntExtra("i", -1);
            if (max > 0) seekBar.setMax(max);
            if (i > 0) seekBar.setProgress(i);
        }
    }
}
