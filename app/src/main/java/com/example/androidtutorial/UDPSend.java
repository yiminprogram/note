package com.example.androidtutorial;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * ----------
 * manifest add
 * <user-permission android:name="android.permission.INTERNET"/>
 * ----------
 * (NetworkMainThreadException)
 * internet action need to put to background execute, cannot on main thread
 * ----------
 */

public class UDPSend extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.udp_send);

        EditText input = findViewById(R.id.udp_input);

        findViewById(R.id.btn_udp_send).setOnClickListener(v ->
        {
            new Thread()
            {
                @Override
                public void run()
                {
                    String message = input.getText().toString();
                    byte[] data = message.getBytes();
                    try
                    {
                        DatagramSocket socket = new DatagramSocket();
                        DatagramPacket packet = new DatagramPacket(
                                data,
                                data.length,
                                InetAddress.getByName("192......"),
                                8080
                        );
                        socket.send(packet);
                        socket.close();
                        Log.d("apple", "Send OK");
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
