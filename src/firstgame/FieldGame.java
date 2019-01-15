/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstgame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class FieldGame extends JFrame{

     private static final int WIDTH = 1800;
    private static final int HEIGHT = 1000;

    private GameMap gm;
    private PaintMap map;
    
    public FieldGame(GameMap gm) {
        this.gm = gm;
        this.setTitle("First game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        int x = screenSize.width/2 - WIDTH/2;
        int y = screenSize.height/2 - HEIGHT/2;
        this.setBounds(x, y, WIDTH, HEIGHT);
        
        map = new PaintMap(gm.mapWidth, gm.mapHeight, 960, 960);
        
         JPanel centerPanel = new JPanel() {{
            setLayout(new FlowLayout(FlowLayout.CENTER));
        }};
         
         JPanel panel = new JPanel() {{
            setLayout(new FlowLayout());
        }};
         
         panel.add(map);
         panel.add(centerPanel);
         
         this.add(panel);
    }
    
        
}

