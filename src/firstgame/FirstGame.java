package firstgame;


public class FirstGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameMap gm = new GameMap(15,15);
        FieldGame fg = new FieldGame(gm);
        fg.setVisible(true);
    }
    
}
