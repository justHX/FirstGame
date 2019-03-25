/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstgame.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author hulk-
 */
public class PointMy {
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
