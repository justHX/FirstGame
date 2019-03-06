package Server;

import firstgame.GameMap;
import firstgame.MazeHelper;
import firstgame.PointMy;
import firstgame.PointType;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainServer {
    private static ServerSocket serverSocet;
    private static final int port = 6655;
    private static Socket socket;
    private static GameMap gm = new GameMap(15,15);
//    private static ObjectInputStream objectInputStream;
//    private static ObjectOutputStream objectOutputStream;
    private static Point point = new Point();
    private static List<Clients> clientsList = new ArrayList<>();


    public static void main(String[] args) {

System.out.println("Welcome to My server!");


        MazeHelper.generateLattice(gm.map);
        gm.map[1][1] = PointType.AVAILABLE;
        PointMy currentPosition = MazeHelper.generateStartAndEndPosition(gm.map);
        MazeHelper.generatePath(gm.map, currentPosition);


        try {
            serverSocet = new ServerSocket(port);


            while(true){
                try {
                socket = serverSocet.accept();
                System.out.println("New person connected!");
                Clients client = new Clients(socket);

                client.objectOutputStream.writeObject(gm);
                client.objectOutputStream.flush();
                clientsList.add(client);



                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while(true) {
                                point = (Point) client.objectInputStream.readObject();

                                for(Clients clientsOut : clientsList){
                                    if(!clientsOut.socket.equals(client.socket)) {
                                        clientsOut.objectOutputStream.writeObject(point);
                                        clientsOut.objectOutputStream.flush();
                                    }
                                }

                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
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
}
