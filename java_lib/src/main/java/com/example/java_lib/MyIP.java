package com.example.java_lib;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class MyIP
{
    public static void main(String[] args)
    {
        try
        {
            Enumeration<NetworkInterface> networkInterfaceEnum = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaceEnum.hasMoreElements())
            {
                NetworkInterface networkInterface = networkInterfaceEnum.nextElement();
                Enumeration<InetAddress> ips = networkInterface.getInetAddresses();
                while (ips.hasMoreElements())
                {
                    InetAddress ip = ips.nextElement();
                    System.out.println(ip.getHostAddress());
                }
                System.out.println("------");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
}
