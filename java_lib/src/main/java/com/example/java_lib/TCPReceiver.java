package com.example.java_lib;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPReceiver
{
    public static void main(String[] args)
    {
        try
        {
            ServerSocket server = new ServerSocket(8080);
            Socket socket = server.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = reader.readLine();
            System.out.println("Server OK");
            System.out.println(socket.getInetAddress().getHostAddress() + " : " + message);
            server.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
}
