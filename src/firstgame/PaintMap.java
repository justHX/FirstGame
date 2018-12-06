/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

/**
 *
 * @author hulk-
 */
public class PaintMap extends JPanel{
    private int cellSize;
    private int mapWidth, mapHeight;
    private JPanel cells[][];
    private  JLabel label;

    public PaintMap(int rowCount, int columnCount, int fieldWidth, int fieldHeight) {
        setLayout(new GridLayout(rowCount, columnCount));
         this.mapWidth = fieldWidth;
         this.mapHeight = fieldHeight;
         
         int cellSizeByWidth = fieldWidth/columnCount;
         int cellSizeByHeight = fieldHeight/rowCount;
         
         cellSize = cellSizeByWidth < cellSizeByHeight ? cellSizeByWidth : cellSizeByHeight;
        
        cells = new JPanel[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                
                int finalI = i;
                int finalJ = j;
                
                cells[i][j] = new JPanel(){{
                        try {
                            setPreferredSize(new Dimension(cellSize, cellSize));
                            label  = new JLabel(); 
                            label.setIcon(new ImageIcon(ImageIO.read(new File("E://A//square.png"))));
                            
                             GradientPaint primary = new GradientPaint(
                        0f, 0f, Color.WHITE, 200f, 0f, Color.ORANGE);
                             setBackground(Color.MAGENTA);
                             
                    setBorder(new MatteBorder(1, 1, finalI == rowCount - 1 ? 1 : 0, finalJ == columnCount - 1 ? 1 : 0, Color.DARK_GRAY));
                          // add(label);
                            
                        } catch (IOException ex) {
                            Logger.getLogger(PaintMap.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }};
                add(cells[i][j]);
            }
        }
    }
    
}
