package com.example.androidtutorial;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class StopWatch extends AppCompatActivity
{
    private boolean isStart;
    private TextView timerText;
    private Button btnLeft, btnRight;
    private ListView lapList;
    private Timer timer = new Timer();
    private int counter;
    private MyTimerTask myTimerTask;
    private UIHandler uiHandler = new UIHandler();
    private SimpleAdapter simpleAdapter;
    private LinkedList<HashMap<String, String>> data = new LinkedList<>();
    private String[] from = {"rec"};
    private int[] to = {R.layout.stop_watch_list_view_item};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop_watch);

        timerText = findViewById(R.id.timer);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);
        lapList = findViewById(R.id.lap_list_view);

        btnLeft.setOnClickListener(btnLeftListener);
        btnRight.setOnClickListener(btnRightListener);

        initLapList();
    }

    @SuppressLint("ResourceType")
    private void initLapList()
    {
        simpleAdapter = new SimpleAdapter(this, data, R.layout.stop_watch_list_view_item, from, to);
        lapList.setAdapter(simpleAdapter);
    }

    private final View.OnClickListener btnLeftListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            if (isStart)
            {
                doLap();
            }
            else
            {
                doReset();
            }
        }
    };

    private final View.OnClickListener btnRightListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            isStart = !isStart;
            if (isStart)
            {
                btnLeft.setText("LAP");
                btnRight.setText("STOP");
                doStart();
            }
            else
            {
                btnLeft.setText("RESET");
                btnRight.setText("START");
                doStop();
            }
        }
    };

    private void doReset()
    {
        counter = 0;
        uiHandler.sendEmptyMessage(0);
        data.clear();
        simpleAdapter.notifyDataSetChanged();
    }

    private void doLap()
    {
        HashMap<String, String> row = new HashMap<>();
        row.put(from[0], timerText.getText().toString());
        data.add(0, row);
        simpleAdapter.notifyDataSetChanged();
    }

    private void doStop()
    {
        if (myTimerTask != null)
        {
            myTimerTask.cancel();
            myTimerTask = null;
        }
    }

    private void doStart()
    {
        myTimerTask = new MyTimerTask();
        timer.schedule(myTimerTask, 10, 10);
    }

    private class MyTimerTask extends TimerTask
    {
        @Override
        public void run()
        {
            counter++;
            uiHandler.sendEmptyMessage(0);
        }
    }

    private class UIHandler extends Handler
    {
        @Override
        public void handleMessage(@NonNull Message msg)
        {
            super.handleMessage(msg);
            timerText.setText(counterToClock(counter));
        }
    }

    public static String counterToClock(int i)
    {
        int hs = i % 100; // 小數點後
        int ts = i / 100; // 總秒數
        int hh = ts / (60 * 60);
        int mm = (ts - hh * 60 * 60) / 60;
        int ss = ts % 60;
        return String.format("%d:%d:%d.%d", hh, mm, ss, hs);
    }
}
