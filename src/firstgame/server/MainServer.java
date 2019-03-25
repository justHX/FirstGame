package firstgame.server;

import firstgame.application.GameMap;
import firstgame.application.MazeHelper;
import firstgame.application.PointMy;
import firstgame.application.PointType;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class MainServer {

    private static final int PORT = 6655;

    private static GameMap gm = new GameMap(15, 15);

    private static List<Client> clientsList = new ArrayList<>();

    private static volatile int idSequence = 0;


    public static void main(String[] args) {
        MazeHelper.generateLattice(gm.map);
        gm.map[1][1] = PointType.AVAILABLE;
        PointMy currentPosition = MazeHelper.generateStartAndEndPosition(gm.map);
        MazeHelper.generatePath(gm.map, currentPosition);

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);

            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("New person connected!");
                    Client client = new Client(socket);

                    client.objectOutputStream.writeObject(gm);
                    client.objectOutputStream.flush();
                    clientsList.add(client);


                    new Thread(() -> {
                        try {
                            while (true) {
                                Point point = (Point) client.objectInputStream.readObject();

                                for (Client clientOut : clientsList) {
                                    if (!clientOut.socket.equals(client.socket)) {
                                        clientOut.objectOutputStream.writeObject(point);
                                        clientOut.objectOutputStream.flush();
                                    }
                                }

                            }
                        } catch (IOException | ClassNotFoundException e) {
                            clientsList.remove(client);
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static synchronized int getId() {
        idSequence++;
        return idSequence;
    }
}
