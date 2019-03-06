package firstgame;


import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class SearchPath {
    public static PointType[][] maze;
    public static PointMy finalPointMy;
    public static HashSet<PointMy> setPointMy = new HashSet<>();
    public static Queue<PointMy> pointMyQueue = new ArrayDeque<>();

    public static void searchPath(JPanel[][] cells) {
        PointMy forstPoint = new PointMy(0,3);

        setPointMy.add(forstPoint);
        forstPoint.path.add(forstPoint);
        pointMyQueue.add(forstPoint);

        findNext(cells);
    }

    public static void findNext(JPanel[][] cells){

        PointMy pointFromQueue = pointMyQueue.poll();
        System.out.println(pointFromQueue);

        if(pointFromQueue == null || pointFromQueue.equals(finalPointMy)){
            for(PointMy point : pointFromQueue.path){
                cells[point.x][point.y].setBackground(Color.WHITE);
            }
            return;
        }

        setPointMy.add(pointFromQueue);

        List<PointMy> pointMIES = new ArrayList<>();

        for(PointMy point : MazeHelper.getNearestAvailablePoints2(maze, pointFromQueue)){

            if(!setPointMy.contains(point)){
                pointMIES.add(point);
            }

        }

        for(PointMy point : pointMIES){
            point.path.addAll(pointFromQueue.path);
            point.path.add(point);
            pointMyQueue.add(point);
        }
        findNext(cells);
    }
}


