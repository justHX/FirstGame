/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;


public class PaintMap extends JPanel {
    
   
    private int thisY = 0; //текущая координата Y персонажа
    private int thisX = 3; //текущая координата X персонажа
    private int cellSize;
    private int mapWidth, mapHeight;
    private JPanel cells[][];
   // private int laberint[][];
    private Random rand = new Random();
    private int r;
    private GenerationLaberint newLaberint;
    
    public PaintMap(int rowCount, int columnCount, int fieldWidth, int fieldHeight) {
        newLaberint = new GenerationLaberint(rowCount, columnCount);
        
        setLayout(new GridLayout(rowCount, columnCount));
        this.mapWidth = fieldWidth;
        this.mapHeight = fieldHeight;
        
        int cellSizeByWidth = fieldWidth / columnCount;
        int cellSizeByHeight = fieldHeight / rowCount;
        
        cellSize = cellSizeByWidth < cellSizeByHeight ? cellSizeByWidth : cellSizeByHeight;
        
        cells = new JPanel[rowCount][columnCount];
        //laberint = new int[rowCount][columnCount];
        
        
        
//<editor-fold defaultstate="collapsed" desc="БОЛЬ">
//        boolean go, check;
//        do {//////////////////////////чудо-цикл генерирующий лаберинт
//            go = true;
//            for (int i = 0; i < rowCount; i++) {/////////проверяет есть ли непосещённые клетки
//                for (int j = 0; j < columnCount; j++) {
//                    r = rand.nextInt(2);
//                    if (laberint[i][j] == 1) {
//                        go = false;
//                    }
//                    
//                    if (laberint[i][j] == 2 && r == 0 && i % 2 != 0 && j % 2 != 0) {///условия, проверяющие есть ли вокруг ПОСЕЩЁННЫХ клеток непосещённые
//
//                        
//                        
//                    }
//                }
//            }
//            
//            for (int i = 0; i < rowCount; i++) {/////////тут творится магия рандомного формирования лабринта
//                for (int j = 0; j < columnCount; j++) {
//                    check = false;
//                    if (laberint[i][j] == 3) {
//                        laberint[i][j] = 2;
//                       
//                        i = 0;
//                        j = 0;
//                    }
//                    
//                }
//            }
//            
//        } while (go != true);
//        

        
//        do {
//            go = true;
//
//            for (int i = 0; i < rowCount; i++) {
//                for (int j = 0; j < columnCount; j++) {
//                    if (laberint[i][j] == 1 || laberint[i][j] == 3) {
//                        go = false;
//                        
//                    }
//                }
//            }
//
//            for (int i = 0; i < rowCount; i++) {
//                for (int j = 0; j < columnCount; j++) {
//                    if (laberint[i][j] == 1 || laberint[i][j] == 3) {
//                        if (i - 1 == 0 && j - 1 == 0) {
//                            if ((laberint[i + 2][j] != 2|| laberint[i][j + 2] != 2) && !(laberint[i + 2][j] == 3 && laberint[i][j + 2] == 3)) {
//                                r = rand.nextInt(2);
//                                if (r == 0) {
//                                    laberint[i + 1][j] = 2;
//                                    laberint[i + 2][j] = 3;
//                                } else {
//                                    laberint[i][j + 1] = 2;
//                                    laberint[i][j + 2] = 3;
//                                }
//                            } else if(laberint[i + 2][j] == 3 && laberint[i][j + 2] == 3){
//                                laberint[i][j] = 2;
//                            }
//                          
//                        } else if (i - 1 == 0 && j + 1 == columnCount-1) {
//                            if ((laberint[i + 2][j] != 2 || laberint[i][j - 2] !=2) && !(laberint[i + 2][j] == 3 && laberint[i][j - 2] == 3)) {
//                                r = rand.nextInt(2);
//                                if (r == 0) {
//                                    laberint[i + 1][j] = 2;
//                                    laberint[i + 2][j] = 3;
//                                } else {
//                                    laberint[i][j - 1] = 2;
//                                    laberint[i][j - 2] = 3;
//                                }
//
//                            } else if(laberint[i + 2][j] == 3 && laberint[i][j - 2] == 3){
//                                laberint[i][j] = 2;
//                            }
//                         
//                        } else if (i + 1 == rowCount-1 && j - 1 == 0) {
//                            if ((laberint[i - 2][j] !=2 || laberint[i][j + 2] !=2) && !(laberint[i - 2][j] == 3 && laberint[i][j + 2] == 3)) {
//                                r = rand.nextInt(2);
//                                if (r == 0) {
//                                    laberint[i - 1][j] = 2;
//                                    laberint[i - 2][j] = 3;
//                                } else {
//                                    laberint[i][j + 1] = 2;
//                                    laberint[i][j + 2] = 3;
//                                }
//                            } else if(laberint[i - 2][j] == 3 && laberint[i][j + 2] == 3){
//                                laberint[i][j] = 2;
//                            }
//                           
//                        } else if (i + 1 == rowCount-1 && j + 1 == columnCount-1) {
//                            if ((laberint[i - 2][j] != 2 || laberint[i][j - 2] != 2) && !(laberint[i - 2][j] == 3 && laberint[i][j - 2] == 3)) {
//                                r = rand.nextInt(2);
//                                if (r == 0) {
//                                    laberint[i - 1][j] = 2;
//                                    laberint[i - 2][j] = 3;
//                                } else {
//                                    laberint[i][j - 1] = 2;
//                                    laberint[i][j - 2] = 3;
//                                }
//                            } else if(laberint[i - 2][j] == 3 && laberint[i][j - 2] == 3){
//                                laberint[i][j] = 2;
//                            }
//                          
//                        } else if (i - 1 == 0 && (j-1 != 0 || j + 1 != columnCount-1)){
//                            if((laberint[i+2][j] != 2 || laberint[i][j+2] != 2 || laberint[i][j-2] != 2) && !(laberint[i+2][j] == 3 && laberint[i][j+2] == 3 && laberint[i][j-2] == 3)){
//                                r = rand.nextInt(3);
//                                if (r == 0) {
//                                    laberint[i + 1][j] = 2;
//                                    laberint[i + 2][j] = 3;
//                                } else if (r == 1) {
//                                    laberint[i][j - 1] = 2;
//                                    laberint[i][j - 2] = 3;
//                                }else{
//                                laberint[i][j+1] = 2;
//                                laberint[i][j+2] = 3;
//                                }
//                            }else if(laberint[i+2][j] == 3 && laberint[i][j+2] == 3 && laberint[i][j-2] == 3){
//                            laberint[i][j]=2;
//                            }
//                       
//                        } else if (i + 1 == rowCount-1 && (j-1 != 0 || j + 1 != columnCount-1)){
//                         if((laberint[i-2][j] !=2 || laberint[i][j+2] !=2 || laberint[i][j-2] !=2) && !(laberint[i-2][j] == 3 && laberint[i][j+2] == 3 && laberint[i][j-2] == 3)){
//                                r = rand.nextInt(3);
//                                if (r == 0) {
//                                    laberint[i - 1][j] = 2;
//                                    laberint[i - 2][j] = 3;
//                                } else if (r == 1) {
//                                    laberint[i][j - 1] = 2;
//                                    laberint[i][j - 2] = 3;
//                                }else{
//                                laberint[i][j+1] = 2;
//                                laberint[i][j+2] = 3;
//                                }
//                            }else if(laberint[i-2][j] == 3 && laberint[i][j+2] == 3 && laberint[i][j-2] == 3){
//                            laberint[i][j]=2;
//                            }
//                 
//                        }else if (j-1 == 0 && (i+1 != rowCount-1 || i-1 != 0)){
//                        if((laberint[i+2][j] !=2 || laberint[i-2][j] !=2 || laberint[i][j+2] !=2) && !(laberint[i+2][j] == 3 && laberint[i-2][j] == 3 && laberint[i][j+2] == 3)){
//                         r = rand.nextInt(3);
//                                if (r == 0) {
//                                    laberint[i - 1][j] = 2;
//                                    laberint[i - 2][j] = 3;
//                                } else if (r == 1) {
//                                    laberint[i+1][j] = 2;
//                                    laberint[i+2][j] = 3;
//                                }else{
//                                laberint[i][j+1] = 2;
//                                laberint[i][j+2] = 3;
//                                }
//                        }else if (laberint[i+2][j] == 3 && laberint[i-2][j] == 3 && laberint[i][j+2] == 3){
//                        laberint[i][j] = 2;
//                        }
//                        
//                        }else if (j+1 == columnCount-1 && (i-1 !=0 || i+1 != rowCount-1)){
//                         if((laberint[i+2][j] !=2 || laberint[i-2][j] !=2 || laberint[i][j-2] !=2) && !(laberint[i+2][j] == 3 && laberint[i-2][j] == 3 && laberint[i][j-2] == 3)){
//                         r = rand.nextInt(3);
//                                if (r == 0) {
//                                    laberint[i - 1][j] = 2;
//                                    laberint[i - 2][j] = 3;
//                                } else if (r == 1) {
//                                    laberint[i+1][j] = 2;
//                                    laberint[i+2][j] = 3;
//                                }else{
//                                laberint[i][j-1] = 2;
//                                laberint[i][j-2] = 3;
//                                }
//                        }else if(laberint[i+2][j] == 3 && laberint[i-2][j] == 3 && laberint[i][j-2] == 3){
//                        laberint[i][j] = 2;
//                        }
//                         
//                        }else{
//                            if((laberint[i+2][j] !=2 || laberint[i-2][j] !=2 || laberint[i][j+2] !=2 || laberint[i][j-2] !=2) && !(laberint[i+2][j] == 3 && laberint[i-2][j] == 3 && laberint[i][j+2] == 3 && laberint[i][j-2] ==3)){
//                            r = rand.nextInt(4);
//                            if(r == 0){
//                            laberint[i+1][j] = 2;
//                            laberint[i+2][j] = 3;
//                            }else if (r == 1){
//                            laberint[i-1][j] = 2;
//                            laberint[i-2][j] = 3;
//                            }else if (r == 2){
//                            laberint[i][j+1] = 2;
//                            laberint[i][j+2] = 3;
//                            }else if (r == 3){
//                            laberint[i][j-1] = 2;
//                            laberint[i][j-2] = 3;
//                            }
//                            }else if(laberint[i+2][j] == 3 && laberint[i-2][j] == 3 && laberint[i][j+2] == 3 && laberint[i][j-2] ==3){
//                            laberint[i][j] = 2;
//                            }
//                            
//                        }
//                    }
//                }
//            }
//
//        }while(go != true);
//        
//
//
//        do {
//            go = true;
//
//            for (int i = 0; i < rowCount; i++) {
//                for (int j = 0; j < columnCount; j++) {
//                    if (laberint[i][j] == 1) {
//                        go = false;
//                        break;
//                    }
//                }
//            }
//
//            for (int i = 0; i < rowCount; i++) {
//                for (int j = 0; j < columnCount; j++) {
//                    if (laberint[i][j] == 1) {
//                            if ((i - 1 == 0) || (i + 1 == rowCount - 1) || (j - 1 == 0) || (j + 1 == columnCount - 1)) {
//                                if ((i - 1 == 0) && (j - 1 == 0)) {
//                                    r = rand.nextInt(2);
//                                    if (r == 0) {
//                                        laberint[i + 1][j] = 2;
//                                        laberint[i + 2][j] = 2;
//                                    } else {
//                                        laberint[i][j + 1] = 2;
//                                        laberint[i][j + 2] = 2;
//                                    }
//                                } else if ((i - 1 == 0) && (j + 1 == columnCount - 1)) {
//                                    r = rand.nextInt(2);
//                                    if (r == 0) {
//                                        laberint[i + 1][j] = 2;
//                                        laberint[i + 2][j] = 2;
//                                    } else {
//                                        laberint[i][j - 1] = 2;
//                                        laberint[i][j - 2] = 2;
//                                    }
//                                } else if ((i + 1 == rowCount) && (j - 1 == 0)) {
//                                    r = rand.nextInt(2);
//                                    if (r == 0) {
//                                        laberint[i - 1][j] = 2;
//                                        laberint[i - 2][j] = 2;
//                                    } else {
//                                        laberint[i][j + 1] = 2;
//                                        laberint[i][j + 2] = 2;
//                                    }
//                                } else if ((i + 1 == rowCount) && (j + 1 == columnCount - 1)) {
//                                    r = rand.nextInt(2);
//                                    if (r == 0) {
//                                        laberint[i - 1][j] = 2;
//                                        laberint[i - 2][j] = 2;
//                                    } else {
//                                        laberint[i][j - 1] = 2;
//                                        laberint[i][j - 2] = 2;
//                                    }
//                                } else if ((i - 1 == 0) && (j - 1 != 0) && (j + 1 != columnCount - 1)) {
//                                    r = rand.nextInt(3);
//                                    if (r == 0) {
//                                        laberint[i + 1][j] = 2;
//                                        laberint[i + 2][j] = 2;
//                                    } else if (r == 1) {
//                                        laberint[i][j - 1] = 2;
//                                        laberint[i][j - 2] = 2;
//                                    } else {
//                                        laberint[i][j + 1] = 2;
//                                        laberint[i][j + 2] = 2;
//                                    }
//                                } else if ((i + 1 == rowCount) && (j - 1 != 0) && (j + 1 != columnCount - 1)) {
//                                    r = rand.nextInt(3);
//                                    if (r == 0) {
//                                        laberint[i - 1][j] = 2;
//                                        laberint[i - 2][j] = 2;
//                                    } else if (r == 1) {
//                                        laberint[i][j + 1] = 2;
//                                        laberint[i][j + 2] = 2;
//                                    } else {
//                                        laberint[i][j - 1] = 2;
//                                        laberint[i][j - 2] = 2;
//                                    }
//                                } else if ((j - 1 == 0) && (i - 1 != 0) && (i + 1 != rowCount - 1)) {
//                                    r = rand.nextInt(3);
//                                    if (r == 0) {
//                                        laberint[i + 1][j] = 2;
//                                        laberint[i + 2][j] = 2;
//                                    } else if (r == 1) {
//                                        laberint[i - 1][j] = 2;
//                                        laberint[i - 2][j] = 2;
//                                    } else {
//                                        laberint[i][j + 1] = 2;
//                                        laberint[i][j + 2] = 2;
//                                    }
//                                } else if (j + 1 == columnCount - 1 && (i - 1 != 0) && (i + 1 != rowCount - 1)) {
//                                    r = rand.nextInt(3);
//                                    if (r == 0) {
//                                        laberint[i + 1][j] = 2;
//                                        laberint[i + 2][j] = 2;
//                                    } else if (r == 1) {
//                                        laberint[i - 1][j] = 2;
//                                        laberint[i - 2][j] = 2;
//                                    } else {
//                                        laberint[i][j - 1] = 2;
//                                        laberint[i][j - 2] = 2;
//                                    }
//                                }
//
//                            } else {
//                                r = rand.nextInt(4);
//                                if (r == 0) {
//                                    laberint[i + 1][j] = 2;
//                                    laberint[i + 2][j] = 2;
//                                } else if (r == 1) {
//                                    laberint[i - 1][j] = 2;
//                                    laberint[i - 2][j] = 2;
//                                } else if (r == 2) {
//                                    laberint[i][j + 1] = 2;
//                                    laberint[i][j + 2] = 2;
//                                } else {
//                                    laberint[i][j - 1] = 2;
//                                    laberint[i][j - 2] = 2;
//
//                                }
//
//                            }
//                    }
//                }
//            }
//
//
//        } while (go == true);
//        int n = 0;
//
//        int x;
//        int y;
//        int r;
//        boolean go;
//        do {
//            go = true;
//            
//            for (int i = 0; i < rowCount; i++) {
//                for (int j = 0; j < columnCount; j++) {
//                    if (laberint[i][j] == 1) {
//                        go = false;
//                    }
//                }
//            }
//
//            do {
//                x = rand.nextInt(rowCount);
//                y = rand.nextInt(columnCount);
//            } while ((x % 2 == 0 && y % 2 == 0)&&(x != 0 && y != 0));
//
//            //laberint[x][y] = 2;
//
//            if (x - 1 == 0) {
//                if (y - 1 == 0) {
//                    r = rand.nextInt(2);
//                    if (r == 0) {
//                        laberint[x][y + 1] = 2;
//                    } else {
//                        laberint[x + 1][y] = 2;
//                    }
//                } else if (y + 1 == columnCount) {
//                    r = rand.nextInt(2);
//                    if (r == 0) {
//                        laberint[x][y - 1] = 2;
//                    } else {
//                        laberint[x + 1][y] = 2;
//                    }
//                } else {
//                    r = rand.nextInt(3);
//                    if (r == 0) {
//                        laberint[x + 1][y] = 2;
//                    } else if (r == 1) {
//                        laberint[x][y + 1] = 2;
//                    } else {
//                        laberint[x][y - 1] = 2;
//                    }
//                }
//
//            } else if (x + 1 == rowCount) {
//                if (y - 1 == 0) {
//                    r = rand.nextInt(2);
//                    if (r == 0) {
//                        laberint[x - 1][y] = 2;
//                    } else {
//                        laberint[x][y + 1] = 2;
//                    }
//                } else if (y + 1 == columnCount) {
//                    r = rand.nextInt(2);
//                    if (r == 0) {
//                        laberint[x - 1][y] = 2;
//                    } else {
//                        laberint[x][y - 1] = 2;
//                    }
//                } else {
//                    r = rand.nextInt(3);
//                    if (r == 0) {
//                        laberint[x - 1][y] = 2;
//                    } else if (r == 1) {
//                        laberint[x][y + 1] = 2;
//                    } else {
//                        laberint[x][y - 1] = 2;
//                    }
//                }
//            } else {
//                r = rand.nextInt(4);
//                if (r == 0) {
//                    laberint[x + 1][y] = 2;
//                } else if (r == 1) {
//                    laberint[x - 1][y] = 2;
//                } else if (r == 2) {
//                    laberint[x][y + 1] = 2;
//                } else {
//                    laberint[x][y - 1] = 2;
//                }
//            }
//
//            if (y - 1 == 0) {
//                if (x - 1 == 0) {
//                    r = rand.nextInt(2);
//                    if (r == 0) {
//                        laberint[x + 1][y] = 2;
//                    } else {
//                        laberint[x][y + 1] = 2;
//                    }
//                } else if (x + 1 == rowCount) {
//                    r = rand.nextInt(2);
//                    if (r == 0) {
//                        laberint[x - 1][y] = 2;
//                    } else {
//                        laberint[x][y + 1] = 2;
//                    }
//                } else {
//                    r = rand.nextInt(3);
//                    if (r == 0) {
//                        laberint[x - 1][y] = 2;
//                    } else if (r == 1) {
//                        laberint[x + 1][y] = 2;
//                    } else {
//                        laberint[x][y + 1] = 2;
//                    }
//                }
//            } else if (y + 1 == columnCount) {
//                if (x - 1 == 0) {
//                    r = rand.nextInt(2);
//                    if (r == 0) {
//                        laberint[x + 1][y] = 2;
//                    } else {
//                        laberint[x][y - 1] = 2;
//                    }
//                } else if (x + 1 == rowCount) {
//                    r = rand.nextInt(2);
//                    if (r == 0) {
//                        laberint[x - 1][y] = 2;
//                    } else {
//                        laberint[x][y - 1] = 2;
//                    }
//                } else {
//                    r = rand.nextInt(3);
//                    if (r == 0) {
//                        laberint[x - 1][y] = 2;
//                    } else if (r == 1) {
//                        laberint[x + 1][y] = 2;
//                    } else {
//                        laberint[x][y - 1] = 2;
//                    }
//                }
//            } else {
//                r = rand.nextInt(4);
//                if (r == 0) {
//                    laberint[x + 1][y] = 2;
//                } else if (r == 1) {
//                    laberint[x - 1][y] = 2;
//                } else if (r == 2) {
//                    laberint[x][y + 1] = 2;
//                } else {
//                    laberint[x][y - 1] = 2;
//                }
//            }
//
//        } while (go == true);
//        do {
//            for (int i = 0; i < rowCount; i++) {
//                for (int j = 0; j < columnCount; j++) {
//                    if(laberint[i][j] == 1){
//                        go = false;
//                        break;
//                    }
//                }
//            }
//            
//            x = rand.nextInt(rowCount);
//            y = rand.nextInt(columnCount);
//            if (x % 2 != 0) {
//                x++;
//            }
//            if (y % 2 != 0) {
//                y++;
//            }
//
//            laberint[0][1] = 2;
//
//            r = rand.nextInt(4);
//            laberint[x][y] = 2;
//            if (r == 0) {
//                laberint[x + 1][y] = 2;
//            } else if (r == 1) {
//                laberint[x][y + 1] = 2;
//            } else if (r == 2) {
//                laberint[x - 1][y] = 2;
//            } else if (r == 3) {
//                laberint[x][y - 1] = 2;
//            }
//
//        } while (go != true);
        //</editor-fold>
        
        
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                
                int finalI = i;
                int finalJ = j;
                
                cells[i][j] = new JPanel() {
                    {
                        setPreferredSize(new Dimension(cellSize, cellSize));
                        
                        if (newLaberint.laberint[finalI][finalJ] == 0) {
                            setBackground(Color.BLACK);
                        } else if (newLaberint.laberint[finalI][finalJ] == 2) {
                            setBackground(Color.MAGENTA);
                        } else if (newLaberint.laberint[finalI][finalJ] == 5) {
                            setBackground(Color.CYAN);
                        }
                        
                        setBorder(new MatteBorder(1, 1, finalI == rowCount - 1 ? 1 : 0, finalJ == columnCount - 1 ? 1 : 0, Color.DARK_GRAY));
                    }
                };
                
                add(cells[i][j]);
            }
        }
        
        setFocusable(true);
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                
                r = rand.nextInt(5);/////немного бутофории, рандом для цвета. Страшный сон эпилептика
                
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (!cells[thisY][thisX - 1].getBackground().equals(Color.BLACK)) {
                        cells[thisY][thisX].setBackground(Color.MAGENTA);
                        if (r == 0) {
                            cells[thisY][thisX - 1].setBackground(Color.CYAN);
                        } else if (r == 1) {
                            cells[thisY][thisX - 1].setBackground(Color.YELLOW);
                        } else if (r == 2) {
                            cells[thisY][thisX - 1].setBackground(Color.ORANGE);
                        } else if (r == 3) {
                            cells[thisY][thisX - 1].setBackground(Color.BLUE);
                        } else {
                            cells[thisY][thisX - 1].setBackground(Color.WHITE);
                        }
                        thisX--;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (!cells[thisY][thisX + 1].getBackground().equals(Color.BLACK)) {
                        cells[thisY][thisX].setBackground(Color.MAGENTA);
                        if (r == 0) {
                            cells[thisY][thisX + 1].setBackground(Color.CYAN);
                        } else if (r == 1) {
                            cells[thisY][thisX + 1].setBackground(Color.YELLOW);
                        } else if (r == 2) {
                            cells[thisY][thisX + 1].setBackground(Color.ORANGE);
                        } else if (r == 3) {
                            cells[thisY][thisX + 1].setBackground(Color.BLUE);
                        } else {
                            cells[thisY][thisX + 1].setBackground(Color.WHITE);
                        }
                        thisX++;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP && thisY != 0) {
                    if (!cells[thisY - 1][thisX].getBackground().equals(Color.BLACK)) {
                        cells[thisY][thisX].setBackground(Color.MAGENTA);
                        if (r == 0) {
                            cells[thisY - 1][thisX].setBackground(Color.CYAN);
                        } else if (r == 1) {
                            cells[thisY - 1][thisX].setBackground(Color.YELLOW);
                        } else if (r == 2) {
                            cells[thisY - 1][thisX].setBackground(Color.ORANGE);
                        } else if (r == 3) {
                            cells[thisY - 1][thisX].setBackground(Color.BLUE);
                        } else {
                            cells[thisY - 1][thisX].setBackground(Color.WHITE);
                        }
                        thisY--;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && thisY != rowCount) {
                    if (!cells[thisY + 1][thisX].getBackground().equals(Color.BLACK)) {
                        cells[thisY][thisX].setBackground(Color.MAGENTA);
                        if (thisY + 1 == rowCount - 1) {
                            JOptionPane.showMessageDialog(null, "Поздравляю, Вы прошли игру", "Скромное окно о победе", JOptionPane.INFORMATION_MESSAGE);
                            System.exit(0);
                        }
                        if (r == 0) {
                            cells[thisY + 1][thisX].setBackground(Color.CYAN);
                        } else if (r == 1) {
                            cells[thisY + 1][thisX].setBackground(Color.YELLOW);
                        } else if (r == 2) {
                            cells[thisY + 1][thisX].setBackground(Color.ORANGE);
                        } else if (r == 3) {
                            cells[thisY + 1][thisX].setBackground(Color.BLUE);
                        } else {
                            cells[thisY + 1][thisX].setBackground(Color.WHITE);
                        }
                        thisY++;
                    }
                } 
            }
        });
        
    }
    
}
