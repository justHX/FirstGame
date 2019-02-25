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
            CYAN, YELLOW, ORANGE, BLUE, RED, PINK, GREEN
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
     * @param pointMIES       list of positions you can go to
     * @param maze         generated maze
     * @param currentPointMy current position in cycle
     * @return next position for cycle
     */
    public static PointMy goToNextRandomPoint(List<PointMy> pointMIES, PointType[][] maze, PointMy currentPointMy) {
        PointMy nextPointMy = pointMIES.get(RANDOM.nextInt(pointMIES.size()));
        maze[nextPointMy.x][nextPointMy.y] = AVAILABLE;
        maze[nextPointMy.x != currentPointMy.x ? nextPointMy.x > currentPointMy.x ? currentPointMy.x + 1 : currentPointMy.x - 1 : currentPointMy.x]
            [nextPointMy.y != currentPointMy.y ? nextPointMy.y > currentPointMy.y ? currentPointMy.y + 1 : currentPointMy.y - 1 : currentPointMy.y] = AVAILABLE;
        return nextPointMy;
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
    public static PointMy generateStartAndEndPosition(PointType[][] maze) {
        maze[0][3] = AVAILABLE;
        PointMy endPointMy = new PointMy(maze.length - 1, maze[0].length - 4);
        maze[endPointMy.x][endPointMy.y] = AVAILABLE;
        return endPointMy;
    }

    /**
     * Searches for all available points closest to the current PointMy.
     *
     * @param maze            generated maze
     * @param currentPosition the position next to which you need to find available cells for the transition
     * @return positions found
     */
    public static List<PointMy> getNearestAvailablePoints(PointType[][] maze, PointMy currentPosition) {
        List<PointMy> pointMIES = new ArrayList<>();
        if (currentPosition.x - 1 != 0 && maze[currentPosition.x - 2][currentPosition.y] == PRE_AVAILABLE) {
            pointMIES.add(new PointMy(currentPosition.x - 2, currentPosition.y));
        }
        if (currentPosition.y - 1 != 0 && maze[currentPosition.x][currentPosition.y - 2] == PRE_AVAILABLE) {
            pointMIES.add(new PointMy(currentPosition.x, currentPosition.y - 2));
        }
        if (currentPosition.x + 1 < maze.length - 1 && maze[currentPosition.x + 2][currentPosition.y] == PRE_AVAILABLE) {
            pointMIES.add(new PointMy(currentPosition.x + 2, currentPosition.y));
        }
        if (currentPosition.y + 1 < maze[0].length - 1 && maze[currentPosition.x][currentPosition.y + 2] == PRE_AVAILABLE) {
            pointMIES.add(new PointMy(currentPosition.x, currentPosition.y + 2));
        }
        return pointMIES;
    }

    public static List<PointMy> getNearestAvailablePoints2(PointType[][] maze, PointMy currentPosition) {
        List<PointMy> points = new ArrayList<>();
        if (currentPosition.x != 0 && maze[currentPosition.x - 1][currentPosition.y] == AVAILABLE) {
            points.add(new PointMy(currentPosition.x - 1, currentPosition.y));
        }
        if (currentPosition.y  != 0 && maze[currentPosition.x][currentPosition.y - 1] == AVAILABLE) {
            points.add(new PointMy(currentPosition.x, currentPosition.y - 1));
        }
        if (currentPosition.x < maze.length - 1 && maze[currentPosition.x + 1][currentPosition.y] == AVAILABLE) {
            points.add(new PointMy(currentPosition.x + 1, currentPosition.y));
        }
        if (currentPosition.y  < maze[0].length - 1 && maze[currentPosition.x][currentPosition.y + 1] == AVAILABLE) {
            points.add(new PointMy(currentPosition.x, currentPosition.y + 1));
        }
        return points;
    }

    /**
     * Generate the path while there are fields to which you can go
     *
     * @param maze generated maze
     */
    public static void generatePath(PointType[][] maze, PointMy currentPosition) {
        List<PointMy> pointMIES;
        do {
            pointMIES = getNearestAvailablePoints(maze, currentPosition);
            if (pointMIES.size() > 0) {
                currentPosition = goToNextRandomPoint(pointMIES, maze, new PointMy(currentPosition.x, currentPosition.y));
            }
        } while (pointMIES.size() != 0);
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
                    List<PointMy> pointMIES = getNearestAvailablePoints(maze, new PointMy(i, j));
                    if (pointMIES.size() > 0) {
                        generatePath(maze, new PointMy(i, j));
                        return;
                    }
                }
            }
        }
    }
}
