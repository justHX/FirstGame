package firstgame.client;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


@SuppressWarnings("WeakerAccess")
public class InternetConnect {

    public static final int PORT = 6655;
    public static final String ADDRESS = "localhost";

    public Socket socket;
    public ObjectOutputStream objectOutputStream;
    public ObjectInputStream objectInputStream;


    public InternetConnect() throws IOException {
        InetAddress inetAddress = InetAddress.getByName(ADDRESS);
        socket = new Socket(inetAddress, PORT);
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    }


    public void sendPoint(Point point){
        try {
            objectOutputStream.writeObject(point);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        objectInputStream.close();
        objectOutputStream.close();
        socket.close();
    }
}
