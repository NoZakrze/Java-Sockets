package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;

    public Server(int port)
    {
        try
        {
            serverSocket = new ServerSocket(port);
        }
        catch (IOException e)
        {}
    }
    public void Start()
    {
        while(true)
        {
            try
            {
                Socket socket = serverSocket.accept();
                Runnable r = new ClientThreat(socket);
                Thread client = new Thread(r);
                client.start();

            }
            catch(IOException e){}
        }
    }

    public static void main(String[] args)
    {
        Server server = new Server(1989);
        server.Start();
    }
}