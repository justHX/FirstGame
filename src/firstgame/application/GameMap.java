package firstgame.application;


import java.io.Serializable;

@SuppressWarnings("WeakerAccess")
public class GameMap  implements Serializable {

    public final PointType[][] map;


    public GameMap(final int mapWidth, final int mapHeight) {
        this.map = new PointType[mapWidth][mapHeight];
    }

}
