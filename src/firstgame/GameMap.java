/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstgame;


public class GameMap {
    
    public int[][] map;
    public final int mapWidth, mapHeight;

    public GameMap(final int mapWidth, final int mapHeight) {
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        map = new int[mapWidth][mapHeight];
    }
}
