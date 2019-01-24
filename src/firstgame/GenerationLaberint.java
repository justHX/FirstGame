/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstgame;

import java.util.Random;

/**
 *
 * @author hulk-
 */
public class GenerationLaberint {
    
   private int rowCount, columnCount;
   public int laberint[][];
   private int nowRow = 1;
   private int nowCount = 1;
   private int r; //переменная с данным уникальным названием используется для рандома
   private boolean check;
   private Random rand = new Random();
   
   

    public GenerationLaberint(int a, int b) {
        this.rowCount = a;
        this.columnCount = b;
        laberint = new int[rowCount][columnCount];
        
        //<editor-fold defaultstate="collapsed" desc="Цикл с сеткой">
        
        for (int i = 0; i < rowCount; i++) {//////////////рандомим первоначальный массив с "тюремной решёткой"
            for (int j = 0; j < columnCount; j++) {

                int finalI = i;
                int finalJ = j;

                ////////////////////////////////////////////
                if (finalI % 2 == 0 || finalJ % 2 == 0) {
                    laberint[i][j] = 0;

                } else {
                    laberint[i][j] = 1;
                }
                ////////////////////////////////////////////

                if (i == 0 && j == 3) {
                    laberint[i][j] = 5;
                }

                if (i == rowCount - 1 && j == columnCount - 4) {
                    laberint[i][j] = 2;
                }


            }
        }
        laberint[nowRow][nowCount] = 2;
        //</editor-fold>
   
    onlyOne();
    
    
    }
    
    
   private void onlyOne(){
       
   
       if (nowRow - 1 == 0 && nowCount - 1 == 0 && (laberint[nowRow + 2][nowCount] == 1 || laberint[nowRow][nowCount + 2] == 1)) {
           letsGo();
       } else if (nowRow - 1 == 0 && nowCount + 1 == columnCount - 1 && (laberint[nowRow + 2][nowCount] == 1 || laberint[nowRow][nowCount - 2] == 1)) {
           letsGo();
       } else if (nowRow + 1 == rowCount - 1 && nowCount - 1 == 0 && (laberint[nowRow - 2][nowCount] == 1 || laberint[nowRow][nowCount + 2] == 1)) {
           letsGo();
       } else if (nowRow + 1 == rowCount - 1 && nowCount + 1 == columnCount - 1 && (laberint[nowRow - 2][nowCount] == 1 || laberint[nowRow][nowCount - 2] == 1)) {
           letsGo();
       } else if (nowRow - 1 == 0 && nowCount - 1 != 0 && nowCount + 1 != columnCount - 1 && (laberint[nowRow + 2][nowCount] == 1 || laberint[nowRow][nowCount + 2] == 1 || laberint[nowRow][nowCount - 2] == 1)) {
           letsGo();
       } else if (nowRow + 1 == rowCount - 1 && nowCount - 1 != 0 && nowCount + 1 != columnCount - 1 && (laberint[nowRow - 2][nowCount] == 1 || laberint[nowRow][nowCount - 2] == 1 || laberint[nowRow][nowCount + 2] == 1)) {
           letsGo();
       } else if (nowCount - 1 == 0 && nowRow - 1 != 0 && nowRow + 1 != rowCount - 1 && (laberint[nowRow][nowCount + 2] == 1 || laberint[nowRow - 2][nowCount] == 1 || laberint[nowRow + 2][nowCount] == 1)) {
           letsGo();
       } else if (nowCount + 1 == columnCount - 1 && nowRow - 1 != 0 && nowRow + 1 != rowCount - 1 && (laberint[nowRow][nowCount - 2] == 1 || laberint[nowRow + 2][nowCount] == 1 || laberint[nowRow - 2][nowCount] == 1)) {
           letsGo();
       } else if (nowRow - 1 != 0 && nowRow + 1 != rowCount - 1 && nowCount - 1 != 0 && nowCount + 1 != columnCount - 1 && (laberint[nowRow + 2][nowCount] == 1 || laberint[nowRow][nowCount + 2] == 1 || laberint[nowRow - 2][nowCount] == 1 || laberint[nowRow][nowCount - 2] == 1)) {
           letsGo();
       }else{
       transform();
       }
       
       

   }
     
   private void letsGo(){
       check = false;
   
        if (nowRow - 1 == 0 && nowCount - 1 == 0 && (laberint[nowRow + 2][nowCount] == 1 || laberint[nowRow][nowCount + 2] == 1)) {
                            
                            while (check != true) {
                                r = rand.nextInt(2);
                                if (r == 0) {
                                    if (laberint[nowRow + 2][nowCount] == 1) {
                                        laberint[nowRow + 1][nowCount] = 2;
                                        laberint[nowRow + 2][nowCount] = 2;
                                        nowRow+=2;
                                        check = true;
                                    }
                                } else {
                                    if (laberint[nowRow][nowCount + 2] == 1) {
                                        laberint[nowRow][nowCount + 1] = 2;
                                        laberint[nowRow][nowCount + 2] = 2;
                                        nowCount+=2;
                                        check = true;
                                    }
                                }
                            }
                            onlyOne();
                        } else if (nowRow - 1 == 0 && nowCount + 1 == columnCount - 1 && (laberint[nowRow + 2][nowCount] == 1 || laberint[nowRow][nowCount - 2] == 1)) {
                            
                            while (check != true) {
                                r = rand.nextInt(2);
                                if (r == 0) {
                                    if (laberint[nowRow + 2][nowCount] == 1) {
                                        laberint[nowRow + 1][nowCount] = 2;
                                        laberint[nowRow + 2][nowCount] = 2;
                                        nowRow+=2;
                                        check = true;
                                    }
                                } else {
                                    if (laberint[nowRow][nowCount - 2] == 1) {
                                        laberint[nowRow][nowCount - 1] = 2;
                                        laberint[nowRow][nowCount - 2] = 2;
                                        nowCount-=2;
                                        check = true;
                                    }
                                }
                            }
                            onlyOne();
                        } else if (nowRow + 1 == rowCount - 1 && nowCount - 1 == 0 && (laberint[nowRow - 2][nowCount] == 1 || laberint[nowRow][nowCount + 2] == 1)) {
                            
                            while (check != true) {
                                r = rand.nextInt(2);
                                if (r == 0) {
                                    if (laberint[nowRow - 2][nowCount] == 1) {
                                        laberint[nowRow - 1][nowCount] = 2;
                                        laberint[nowRow - 2][nowCount] = 2;
                                        nowRow-=2;
                                        check = true;
                                    }
                                } else {
                                    if (laberint[nowRow][nowCount + 2] == 1) {
                                        laberint[nowRow][nowCount + 1] = 2;
                                        laberint[nowRow][nowCount + 2] = 2;
                                        nowCount+=2;
                                        check = true;
                                    }
                                }
                            }
                            onlyOne();
                        } else if (nowRow + 1 == rowCount - 1 && nowCount + 1 == columnCount - 1 && (laberint[nowRow - 2][nowCount] == 1 || laberint[nowRow][nowCount - 2] == 1)) {
                            
                            while (check != true) {
                                r = rand.nextInt(2);
                                if (r == 0) {
                                    if (laberint[nowRow - 2][nowCount] == 1) {
                                        laberint[nowRow - 1][nowCount] = 2;
                                        laberint[nowRow - 2][nowCount] = 2;
                                        nowRow-=2;
                                        check = true;
                                    }
                                } else {
                                    if (laberint[nowRow][nowCount - 2] == 1) {
                                        laberint[nowRow][nowCount - 1] = 2;
                                        laberint[nowRow][nowCount - 2] = 2;
                                        nowCount-=2;
                                        check = true;
                                    }
                                }
                            }
                            onlyOne();
                        } else if (nowRow - 1 == 0 && nowCount - 1 != 0 && nowCount + 1 != columnCount - 1 && (laberint[nowRow + 2][nowCount] == 1 || laberint[nowRow][nowCount + 2] == 1 || laberint[nowRow][nowCount - 2] == 1)) {
                            
                            while (check != true) {
                                r = rand.nextInt(3);
                                if (r == 0) {
                                    if (laberint[nowRow + 2][nowCount] == 1) {
                                        laberint[nowRow + 1][nowCount] = 2;
                                        laberint[nowRow + 2][nowCount] = 2;
                                        nowRow+=2;
                                        check = true;
                                    }
                                } else if (r == 1) {
                                    if (laberint[nowRow][nowCount - 2] == 1) {
                                        laberint[nowRow][nowCount - 1] = 2;
                                        laberint[nowRow][nowCount - 2] = 2;
                                        nowCount-=2;
                                        check = true;
                                    }
                                } else {
                                    if (laberint[nowRow][nowCount + 2] == 1) {
                                        laberint[nowRow][nowCount + 1] = 2;
                                        laberint[nowRow][nowCount + 2] = 2;
                                        nowCount+=2;
                                        check = true;
                                    }
                                }
                            }
                            onlyOne();
                        } else if (nowRow + 1 == rowCount - 1 && nowCount - 1 != 0 && nowCount + 1 != columnCount - 1 && (laberint[nowRow - 2][nowCount] == 1 || laberint[nowRow][nowCount - 2] == 1 || laberint[nowRow][nowCount + 2] == 1)) {
                            
                            while (check != true) {
                                r = rand.nextInt(3);
                                if (r == 0) {
                                    if (laberint[nowRow - 2][nowCount] == 1) {
                                        laberint[nowRow - 1][nowCount] = 2;
                                        laberint[nowRow - 2][nowCount] = 2;
                                        nowRow-=2;
                                        check = true;
                                    }
                                } else if (r == 1) {
                                    if (laberint[nowRow][nowCount - 2] == 1) {
                                        laberint[nowRow][nowCount - 1] = 2;
                                        laberint[nowRow][nowCount - 2] = 2;
                                        nowCount-=2;
                                        check = true;
                                    }
                                } else {
                                    if (laberint[nowRow][nowCount + 2] == 1) {
                                        laberint[nowRow][nowCount + 1] = 2;
                                        laberint[nowRow][nowCount + 2] = 2;
                                        nowCount+=2;
                                        check = true;
                                    }
                                }
                            }
                            onlyOne();
                        } else if (nowCount - 1 == 0 && nowRow - 1 != 0 && nowRow + 1 != rowCount - 1 && (laberint[nowRow][nowCount + 2] == 1 || laberint[nowRow - 2][nowCount] == 1 || laberint[nowRow + 2][nowCount] == 1)) {
                            while (check != true) {
                                r = rand.nextInt(3);
                                if (r == 0) {
                                    if (laberint[nowRow + 2][nowCount] == 1) {
                                        laberint[nowRow + 1][nowCount] = 2;
                                        laberint[nowRow + 2][nowCount] = 2;
                                        nowRow+=2;
                                        check = true;
                                    }
                                } else if (r == 1) {
                                    if (laberint[nowRow - 2][nowCount] == 1) {
                                        laberint[nowRow - 1][nowCount] = 2;
                                        laberint[nowRow - 2][nowCount] = 2;
                                        nowRow-=2;
                                        check = true;
                                    }
                                } else {
                                    if (laberint[nowRow][nowCount + 2] == 1) {
                                        laberint[nowRow][nowCount + 1] = 2;
                                        laberint[nowRow][nowCount + 2] = 2;
                                        nowCount+=2;
                                        check = true;
                                    }
                                }
                            }
                            onlyOne();
                        } else if (nowCount + 1 == columnCount - 1 && nowRow - 1 != 0 && nowRow + 1 != rowCount - 1 && (laberint[nowRow][nowCount - 2] == 1 || laberint[nowRow + 2][nowCount] == 1 || laberint[nowRow - 2][nowCount] == 1)) {
                            
                            while (check != true) {
                                r = rand.nextInt(3);
                                if (r == 0) {
                                    if (laberint[nowRow + 2][nowCount] == 1) {
                                        laberint[nowRow + 1][nowCount] = 2;
                                        laberint[nowRow + 2][nowCount] = 2;
                                        nowRow+=2;
                                        check = true;
                                    }
                                } else if (r == 1) {
                                    if (laberint[nowRow - 2][nowCount] == 1) {
                                        laberint[nowRow - 1][nowCount] = 2;
                                        laberint[nowRow - 2][nowCount] = 2;
                                        nowRow-=2;
                                        check = true;
                                    }
                                } else {
                                    if (laberint[nowRow][nowCount - 2] == 1) {
                                        laberint[nowRow][nowCount - 1] = 2;
                                        laberint[nowRow][nowCount - 2] = 2;
                                        nowCount-=2;
                                        check = true;
                                    }
                                }
                            }
                            onlyOne();
                        } else if (nowRow - 1 != 0 && nowRow + 1 != rowCount - 1 && nowCount - 1 != 0 && nowCount + 1 != columnCount - 1 && (laberint[nowRow + 2][nowCount] == 1 || laberint[nowRow][nowCount + 2] == 1 || laberint[nowRow - 2][nowCount] == 1 || laberint[nowRow][nowCount - 2] == 1)) {
                            
                            while (check != true) {
                                r = rand.nextInt(4);
                                if (r == 0) {
                                    if (laberint[nowRow + 2][nowCount] == 1) {
                                        laberint[nowRow + 1][nowCount] = 2;
                                        laberint[nowRow + 2][nowCount] = 2;
                                        nowRow+=2;
                                        check = true;
                                    }
                                } else if (r == 1) {
                                    if (laberint[nowRow - 2][nowCount] == 1) {
                                        laberint[nowRow - 1][nowCount] = 2;
                                        laberint[nowRow - 2][nowCount] = 2;
                                        nowRow-=2;
                                        check = true;
                                    }
                                } else if (r == 2) {
                                    if (laberint[nowRow][nowCount - 2] == 1) {
                                        laberint[nowRow][nowCount - 1] = 2;
                                        laberint[nowRow][nowCount - 2] = 2;
                                        nowCount-=2;
                                        check = true;
                                    }
                                } else {
                                    if (laberint[nowRow][nowCount + 2] == 1) {
                                        laberint[nowRow][nowCount + 1] = 2;
                                        laberint[nowRow][nowCount + 2] = 2;
                                        nowCount+=2;
                                        check = true;
                                    }
                                }
                            }
                            
                            onlyOne();
                        }
                        
       
           
   } 
   
   private void transform(){
   
       for (int i = 0; i < rowCount; i++) {
           for (int j = 0; j < columnCount; j++) {
               if (laberint[i][j] == 2  && i % 2 != 0 && j % 2 != 0) {///условия, проверяющие есть ли вокруг ПОСЕЩЁННЫХ клеток непосещённые
                   if (i - 1 == 0 && j - 1 == 0 && (laberint[i + 2][j] == 1 || laberint[i][j + 2] == 1)) {
                       nowRow = i;
                       nowCount = j;
                       onlyOne();
                       
                   } else if (i - 1 == 0 && j + 1 == columnCount - 1 && (laberint[i + 2][j] == 1 || laberint[i][j - 2] == 1)) {
                       nowRow = i;
                       nowCount = j;
                       onlyOne();
                   } else if (i + 1 == rowCount - 1 && j - 1 == 0 && (laberint[i - 2][j] == 1 || laberint[i][j + 2] == 1)) {
                      nowRow = i;
                       nowCount = j;
                       onlyOne();
                   } else if (i + 1 == rowCount - 1 && j + 1 == columnCount - 1 && (laberint[i - 2][j] == 1 || laberint[i][j - 2] == 1)) {
                       nowRow = i;
                       nowCount = j;
                       onlyOne();
                   } else if (i - 1 == 0 && j - 1 != 0 && j + 1 != columnCount - 1 && (laberint[i + 2][j] == 1 || laberint[i][j + 2] == 1 || laberint[i][j - 2] == 1)) {
                       nowRow = i;
                       nowCount = j;
                       onlyOne();
                   } else if (i + 1 == rowCount - 1 && j - 1 != 0 && j + 1 != columnCount - 1 && (laberint[i - 2][j] == 1 || laberint[i][j - 2] == 1 || laberint[i][j + 2] == 1)) {
                       nowRow = i;
                       nowCount = j;
                       onlyOne();
                   } else if (j - 1 == 0 && i - 1 != 0 && i + 1 != rowCount - 1 && (laberint[i][j + 2] == 1 || laberint[i - 2][j] == 1 || laberint[i + 2][j] == 1)) {
                       nowRow = i;
                       nowCount = j;
                       onlyOne();
                   } else if (j + 1 == columnCount - 1 && i - 1 != 0 && i + 1 != rowCount - 1 && (laberint[i][j - 2] == 1 || laberint[i + 2][j] == 1 || laberint[i - 2][j] == 1)) {
                       nowRow = i;
                       nowCount = j;
                       onlyOne();
                   } else if (i - 1 != 0 && i + 1 != rowCount - 1 && j - 1 != 0 && j + 1 != columnCount - 1 && (laberint[i + 2][j] == 1 || laberint[i][j + 2] == 1 || laberint[i - 2][j] == 1 || laberint[i][j - 2] == 1)) {
                       nowRow = i;
                       nowCount = j;
                       onlyOne();
                   }

                        
                    }
           }
       }
   }
}
