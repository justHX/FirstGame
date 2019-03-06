package firstgame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.MatteBorder;



@SuppressWarnings("WeakerAccess")
public class PaintMap extends JPanel {
    private int firstPush = 0;
    private int thisY = 0;
    private int thisX = 3;
    private JPanel[][] cells;
    private Point secondPoint = new Point();
    private Point oldPoint = new Point();


    public PaintMap(GameMap gm, final Dimension panelSize) throws IOException, ClassNotFoundException {
        setFocusable(true);
        int rowCount = gm.map.length;
        int columnCount = gm.map[0].length;
        setLayout(new GridLayout(gm.map.length, gm.map[0].length));
        InternetConnect internet = new InternetConnect();


//        MazeHelper.generateLattice(gm.map);
//        gm.map[1][1] = PointType.AVAILABLE;
//        PointMy currentPosition = MazeHelper.generateStartAndEndPosition(gm.map);
//        MazeHelper.generatePath(gm.map, currentPosition);

        gm = (GameMap) internet.objectInputStream.readObject();

        cells = new JPanel[rowCount][columnCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {

                int finalI = i;
                int finalJ = j;

                GameMap finalGm = gm;
                cells[i][j] = new JPanel() {{
                    setPreferredSize(MazeHelper.getCellPreferSize(finalGm, panelSize));

                    if (PointType.NOT_AVAILABLE.equals(finalGm.map[finalI][finalJ])) {
                        setBackground(MazeHelper.DEFAULT_NOT_AVAILABLE_COLOR);
                    } else if (PointType.AVAILABLE.equals(finalGm.map[finalI][finalJ])) {
                        setBackground(MazeHelper.DEFAULT_AVAILABLE_COLOR);
                    }

                    setBorder(new MatteBorder(1, 1, finalI == rowCount - 1 ? 1 : 0, finalJ == columnCount - 1 ? 1 : 0, Color.DARK_GRAY));
                }};

                add(cells[i][j]);
            }
        }
        cells[0][3].setBackground(Color.CYAN);


        GameMap finalGm1 = gm;


        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        secondPoint = (Point) internet.objectInputStream.readObject();
                        if(oldPoint.x!=0 && oldPoint.y!=0)
                        cells[oldPoint.y][oldPoint.x].setBackground(MazeHelper.DEFAULT_AVAILABLE_COLOR);
                        cells[secondPoint.y][secondPoint.x].setBackground(Color.GRAY);
                        oldPoint.setLocation(secondPoint);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        }).start();


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                int xOffset = 0;
                int yOffset = 0;
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    xOffset = -1;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    xOffset = 1;
                } else if (e.getKeyCode() == KeyEvent.VK_UP && thisY != 0) {
                    yOffset = -1;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && thisY != rowCount) {
                    yOffset = 1;
                }

                if (!cells[thisY + yOffset][thisX + xOffset].getBackground().equals(MazeHelper.DEFAULT_NOT_AVAILABLE_COLOR)) {
                    cells[thisY][thisX].setBackground(MazeHelper.DEFAULT_AVAILABLE_COLOR);
                    cells[thisY + yOffset][thisX + xOffset].setBackground(MazeHelper.getRandomColor());
                    thisY += yOffset;
                    thisX += xOffset;

                  internet.sendPoint(new Point(thisX, thisY));

                }

                if (thisY == rowCount - 1) {
                    JOptionPane.showMessageDialog(null, "Поздравляю, вы прошли игру", "Окно с победой", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }

                if (e.getKeyCode() == KeyEvent.VK_P && firstPush == 0) {
                    SearchPath.maze = finalGm1.map;
                    SearchPath.finalPointMy = new PointMy(finalGm1.map.length - 1, finalGm1.map[0].length - 4);
                    SearchPath.searchPath(cells);
                    firstPush++;
                }
            }
        });

    }

}