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

    public static void test(JPanel[][] cells) {
        PointMy p = new PointMy(0,3);

        setPointMy.add(p);
        p.path.add(p);
        pointMyQueue.add(p);

        findNext(cells);

    }

    public static void findNext(JPanel[][] cells){

        PointMy p = pointMyQueue.poll();


        if(p == null || p.equals(finalPointMy)){
            System.out.println("Hallowe");
            for(PointMy pp : p.path){
                System.out.println(p);
                cells[pp.x][pp.y].setBackground(Color.green);
            }
            return;
        }

        setPointMy.add(p);

        List<PointMy> pointMIES = new ArrayList<>();

        for(PointMy ppp : MazeHelper.getNearestAvailablePoints2(maze, p)){

            if(!setPointMy.contains(ppp)){
                pointMIES.add(ppp);
            }

        }

        for(PointMy ppp : pointMIES){
            ppp.path.addAll(p.path);
            ppp.path.add(ppp);
            pointMyQueue.add(ppp);
        }
        findNext(cells);
    }
}

class PointMy {
    int x,y;
    List<PointMy> path = new ArrayList<>();

    public PointMy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return ""+x+","+y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointMy pointMy = (PointMy) o;
        return x == pointMy.x &&
                y == pointMy.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }
}
