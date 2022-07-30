package com.example.androidtutorial;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class TCPSend extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tcp_send);

        EditText input = findViewById(R.id.tcp_input);

        findViewById(R.id.btn_tcp_send).setOnClickListener(v ->
        {
            new Thread()
            {
                @Override
                public void run()
                {
                    try
                    {
                        String message = input.getText().toString();
                        byte[] data = message.getBytes();
                        Socket socket = new Socket(InetAddress.getByName("192......"), 8080);
                        socket.getOutputStream().write(data);
                        socket.close();
                        Log.d("apple", "TCP Secnd OK");
                    }
                    catch (Exception e)
                    {
                        Log.d("apple", e.toString());
                    }
                }
            }.start();
        });
    }
}
