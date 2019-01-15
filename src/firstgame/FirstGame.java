/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstgame;


public class FirstGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameMap gm = new GameMap(51,51);
        FieldGame fg = new FieldGame(gm);
        fg.setVisible(true);
    }
    
}
