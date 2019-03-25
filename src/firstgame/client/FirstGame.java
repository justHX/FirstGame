package firstgame.client;


import firstgame.application.FieldGame;
import firstgame.application.GameMap;

import java.awt.*;
import java.io.IOException;


public class FirstGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            try {
                InternetConnect connect = new InternetConnect();
                GameMap gm = (GameMap) connect.objectInputStream.readObject();
                FieldGame fg = new FieldGame(gm, connect::sendPoint);
                fg.setVisible(true);

                new Thread(() -> {
                    while(true) {
                        try {
                            fg.getPaintMap().drawEnemyPosition((Point) connect.objectInputStream.readObject());
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                break;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            Thread.sleep(10000);
        }
    }
    
}
