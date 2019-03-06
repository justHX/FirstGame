package firstgame;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class InternetConnect {
    public  final int port = 6655;
    public Socket socket;
    public String adress = "localhost";
    public ObjectOutputStream objectOutputStream;
    public ObjectInputStream  objectInputStream;


    public InternetConnect() {
        try {
            InetAddress inetAddress = InetAddress.getByName(adress);
            socket = new Socket(inetAddress, port);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendPoint(Point point){
        try {
            objectOutputStream.writeObject(point);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
