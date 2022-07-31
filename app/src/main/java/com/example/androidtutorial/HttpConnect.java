package com.example.androidtutorial;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * manifests
 * <user-permission android:name="android.permission.INTERNET"/>
 *
 * use http
 * application add
 * android:useCleartextTraffic="true"
 */

public class HttpConnect extends AppCompatActivity
{
    TextView textView;
    UIHandler uiHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_connect);

        Button btn = findViewById(R.id.http_btn);
        textView = findViewById(R.id.http_text_view);
        uiHandler = new UIHandler();

        btn.setOnClickListener(v ->
        {
            new Thread()
            {
                @Override
                public void run()
                {
                    fetchData();
                }
            }.start();
        });
    }

    private StringBuffer stringBuffer;

    private void fetchData()
    {
        try
        {
            URL url = new URL("https://");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            String line;
            stringBuffer = new StringBuffer();

            while ((line = reader.readLine()) != null)
            {
                Log.d("apple", line);
                stringBuffer.append(line).append("\n");
            }
            reader.close();

            uiHandler.sendEmptyMessage(0);
        }
        catch (Exception e)
        {
            Log.d("apple", e.toString());
        }
    }

    private class UIHandler extends Handler
    {
        @Override
        public void handleMessage(@NonNull Message msg)
        {
            super.handleMessage(msg);
            textView.setText(stringBuffer);
        }
    }
}
