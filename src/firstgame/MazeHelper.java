package firstgame;

import java.awt.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import static java.awt.Color.*;
import static firstgame.PointType.*;


/**
 * @author hulk - big green man
 */
@SuppressWarnings("WeakerAccess")
public class MazeHelper {

    public static final Color DEFAULT_AVAILABLE_COLOR;
    public static final Color DEFAULT_NOT_AVAILABLE_COLOR = BLACK;
    private static final Random RANDOM = new Random();

    /**
     * The list of colors that fill the current position of the square
     */
    private static final Color[] RANDOM_COLOR;


    static {
        Color[] colors = new Color[] {
            CYAN, YELLOW, ORANGE, BLUE, WHITE, RED, PINK, GREEN
        };
        int randomIndex = RANDOM.nextInt(colors.length);
        DEFAULT_AVAILABLE_COLOR = colors[randomIndex];

        RANDOM_COLOR = new Color[colors.length - 1];
        int index = 0;
        for (Color color : colors) {
            if (DEFAULT_AVAILABLE_COLOR != color) {
                RANDOM_COLOR[index] = color;
                index++;
            }
        }
    }


    /**
     * @return random color from {@link #RANDOM_COLOR} list
     */
    public static Color getRandomColor() {
        return RANDOM_COLOR[RANDOM.nextInt(RANDOM_COLOR.length)];
    }

    /**
     * Forms cells in the form of squares.The size of the square depends on the smallest side of the panel
     *
     * @param gameMap   object of game map
     * @param panelSize the size of the panel to which the game map will be added
     * @return prefer size
     */
    public static Dimension getCellPreferSize(GameMap gameMap, Dimension panelSize) {
        int cellSizeByWidth = panelSize.width / gameMap.map.length;
        int cellSizeByHeight = panelSize.height / gameMap.map[0].length;
        int cellSize = cellSizeByWidth < cellSizeByHeight ? cellSizeByWidth : cellSizeByHeight;
        return new Dimension(cellSize, cellSize);
    }

    /**
     * Randomly selects the next item from the list.
     *
     * @param points       list of positions you can go to
     * @param maze         generated maze
     * @param currentPoint current position in cycle
     * @return next position for cycle
     */
    public static Point goToNextRandomPoint(List<Point> points, PointType[][] maze, Point currentPoint) {
        Point nextPoint = points.get(RANDOM.nextInt(points.size()));
        maze[nextPoint.x][nextPoint.y] = AVAILABLE;
        maze[nextPoint.x != currentPoint.x ? nextPoint.x > currentPoint.x ? currentPoint.x + 1 : currentPoint.x - 1 : currentPoint.x]
            [nextPoint.y != currentPoint.y ? nextPoint.y > currentPoint.y ? currentPoint.y + 1 : currentPoint.y - 1 : currentPoint.y] = AVAILABLE;
        return nextPoint;
    }

    /**
     * @param maze generated maze
     */
    public static void generateLattice(PointType[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j] = i % 2 == 0 || j % 2 == 0 ? NOT_AVAILABLE : PRE_AVAILABLE;
            }
        }
    }

    /**
     * @param maze generated maze
     */
    public static Point generateStartAndEndPosition(PointType[][] maze) {
        maze[0][3] = AVAILABLE;
        Point endPoint = new Point(maze.length - 1, maze[0].length - 4);
        maze[endPoint.x][endPoint.y] = AVAILABLE;
        return endPoint;
    }

    /**
     * Searches for all available points closest to the current point.
     *
     * @param maze            generated maze
     * @param currentPosition the position next to which you need to find available cells for the transition
     * @return positions found
     */
    public static List<Point> getNearestAvailablePoints(PointType[][] maze, Point currentPosition) {
        List<Point> points = new ArrayList<>();
        if (currentPosition.x - 1 != 0 && maze[currentPosition.x - 2][currentPosition.y] == PRE_AVAILABLE) {
            points.add(new Point(currentPosition.x - 2, currentPosition.y));
        }
        if (currentPosition.y - 1 != 0 && maze[currentPosition.x][currentPosition.y - 2] == PRE_AVAILABLE) {
            points.add(new Point(currentPosition.x, currentPosition.y - 2));
        }
        if (currentPosition.x + 1 < maze.length - 1 && maze[currentPosition.x + 2][currentPosition.y] == PRE_AVAILABLE) {
            points.add(new Point(currentPosition.x + 2, currentPosition.y));
        }
        if (currentPosition.y + 1 < maze[0].length - 1 && maze[currentPosition.x][currentPosition.y + 2] == PRE_AVAILABLE) {
            points.add(new Point(currentPosition.x, currentPosition.y + 2));
        }
        return points;
    }

    /**
     * Generate the path while there are fields to which you can go
     *
     * @param maze generated maze
     */
    public static void generatePath(PointType[][] maze, Point currentPosition) {
        List<Point> points;
        do {
            points = getNearestAvailablePoints(maze, currentPosition);
            if (points.size() > 0) {
                currentPosition = goToNextRandomPoint(points, maze, new Point(currentPosition.x, currentPosition.y));
            }
        } while (points.size() != 0);
        moveToAvailablePosition(maze);
    }
    
    /**
     * Looks for a position available for the transition and generate a path from it.
     *
     * @param maze generated maze
     */
    private static void moveToAvailablePosition(PointType[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (i % 2 != 0 && j % 2 != 0) {
                    List<Point> points = getNearestAvailablePoints(maze, new Point(i, j));
                    if (points.size() > 0) {
                        generatePath(maze, new Point(i, j));
                        return;
                    }
                }
            }
        }
    }
}
