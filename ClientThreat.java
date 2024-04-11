package org.example;

import com.sun.corba.se.spi.activation.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class ClientThreat implements Runnable
{
    private Socket socket;
    private ObjectInputStream Input;
    private ObjectOutputStream Output;
    private int dlugosc = 0;
    private Logger LOGGER = Logger.getLogger(Server.class.getName());

    public ClientThreat(Socket s)
    {
        this.socket = s;
        try {
            Output = new ObjectOutputStream(socket.getOutputStream());
            Input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {}
    }
    @Override
    public void run()
    {
        try
        {
            String m = "Ready";
            LOGGER.info("Ready");
            Output.writeObject(m);
            Output.flush();
            Object dana = Input.readObject();
            if(dana instanceof Integer)
            {
                 dlugosc =  (Integer)dana;
                 String mss = "Ready for message";
                 LOGGER.info("Ready for message");
                 Output.writeObject(mss);
                 Output.flush();
            }
            for(int i=0;i<dlugosc;i++)
            {
                Object msg = Input.readObject();
                if(msg instanceof Message)
                {
                    LOGGER.info("Otrzymano wiadomosc " +((Message) msg).getNumber() + " : "  + ((Message) msg).getContent());
                }
            }
            String m1 ="Finished";

            LOGGER.info(m1);
            Output.writeObject(m1);
            Output.flush();
            socket.close();
        }
        catch (IOException  | ClassNotFoundException e) {}

    }
}
