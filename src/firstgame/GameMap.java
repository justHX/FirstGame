package firstgame;


@SuppressWarnings("WeakerAccess")
public class GameMap {

    public final PointType[][] map;


    public GameMap(final int mapWidth, final int mapHeight) {
        this.map = new PointType[mapWidth][mapHeight];
    }

}
