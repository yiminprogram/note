package com.example.androidtutorial;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPChat extends AppCompatActivity
{
    private TextView room;
    private UIHandler uiHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.udp_chat);

        EditText input = findViewById(R.id.udp_chat_edit);
        Button send = findViewById(R.id.udp_chat_send);
        room = findViewById(R.id.room);
        uiHandler = new UIHandler();
        receiveUDP();

        send.setOnClickListener(v ->
        {
            new Thread(() ->
            {
                String string = input.getText().toString();
                byte[] data = string.getBytes();
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

                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("message", string);
                    message.setData(bundle);
                    message.what = 0;
                    uiHandler.sendMessage(message);

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }).start();
        });
    }

    private class UIHandler extends Handler
    {
        @Override
        public void handleMessage(@NonNull Message msg)
        {
            super.handleMessage(msg);
            String message = msg.getData().getString("message");
            room.append((msg.what == 0 ? "I say : " : "You say : ") + message + "\n");
        }
    }

    private void receiveUDP()
    {
        new Thread(() ->
        {
            while (true)
            {
                byte[] buf = new byte[1024];
                try
                {
                    DatagramSocket socket = new DatagramSocket(8080);
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    socket.close();
                    byte[] data = packet.getData();
                    String string = new String(data, 0, data.length);
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("message", string);
                    message.setData(bundle);
                    message.what = 1;
                    uiHandler.sendMessage(message);
                }
                catch (Exception e)
                {
                    Log.d("apple", e.toString());
                }
            }
        }).start();
    }
}
