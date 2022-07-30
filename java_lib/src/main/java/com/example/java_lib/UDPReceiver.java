package com.example.java_lib;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiver
{
    public static void main(String[] args)
    {
        byte[] buf = new byte[1024];
        try
        {
            DatagramSocket socket = new DatagramSocket(8080);
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            socket.receive(packet);

            System.out.println("Receive OK");

            byte[] data = packet.getData();

            System.out.println(packet.getAddress().getHostAddress() + " : " + new String(data, 0, data.length));

            socket.close();
        }
        catch (Exception e)
        {
            // can use socket auto close, this class use try catch
            System.out.println(e.toString());
        }
    }
}
