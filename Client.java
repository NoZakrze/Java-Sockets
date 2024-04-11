package org.example;

import com.sun.corba.se.spi.activation.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class Client
{
    public static void main(String[] args)
    {
        try
        {
            Logger LOGGER = Logger.getLogger(Server.class.getName());
            Socket socket = new Socket("localhost",1989);
            ObjectOutputStream Output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream Input = new ObjectInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
            while(true)
            {
                Object dana = Input.readObject();
                if (dana instanceof String)
                {
                    if(dana.equals("Ready"))
                    {
                        LOGGER.info("Message receive: Ready");
                        break;
                    }
                }
            }
            int ilosc = scanner.nextInt();
            Integer m = ilosc;
            Output.writeObject(m);
            Output.flush();
            while(true)
            {
                Object dana = Input.readObject();
                if (dana instanceof String)
                {
                    if(dana.equals("Ready for message"))
                    {
                        LOGGER.info("Message receive: Ready for message");
                        break;
                    }
                }
            }
            String smiec = scanner.nextLine();
            for(int i=0;i<ilosc;i++)
            {
                String msg = scanner.nextLine();
                Message ms = new Message(i+1,msg);
                Output.writeObject(ms);
                Output.flush();
            }
            while (true)
            {
                Object koniec = Input.readObject();
                if (koniec instanceof String)
                {
                    LOGGER.info("Message receive:" + koniec);
                    socket.close();
                    break;
                }

            }
        }
        catch(IOException  | ClassNotFoundException e){}
    }
}