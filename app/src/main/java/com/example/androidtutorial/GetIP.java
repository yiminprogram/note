package com.example.androidtutorial;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class GetIP extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_ip);

        findViewById(R.id.btn_get_ip).setOnClickListener(v ->
        {
            try
            {
                Enumeration<NetworkInterface> ipfs = NetworkInterface.getNetworkInterfaces();
                while (ipfs.hasMoreElements())
                {
                    NetworkInterface ipf = ipfs.nextElement();
                    Enumeration<InetAddress> ips = ipf.getInetAddresses();
                    while (ips.hasMoreElements())
                    {
                        InetAddress ip = ips.nextElement();
                        Log.d("apple", "ip : " + ip);
                    }
                    Log.d("apple", "------");
                }
            }
            catch (Exception e)
            {
                Log.d("apple", e.toString());
            }
        });
    }
}
