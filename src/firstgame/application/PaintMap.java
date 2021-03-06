package firstgame.application;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;
import javax.swing.*;
import javax.swing.border.MatteBorder;



@SuppressWarnings("WeakerAccess")
public class PaintMap extends JPanel {
    private final JPanel[][] cells;

    private boolean firstPush = false;
    private int thisY = 0;
    private int thisX = 3;

    private Point oldPoint = new Point();


    public PaintMap(final GameMap gm, final Dimension panelSize, Consumer<Point> sendPointConsumer) {
        setFocusable(true);
        int rowCount = gm.map.length;
        int columnCount = gm.map[0].length;
        setLayout(new GridLayout(gm.map.length, gm.map[0].length));

        cells = new JPanel[rowCount][columnCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {

                int finalI = i;
                int finalJ = j;

                cells[i][j] = new JPanel() {{
                    setPreferredSize(MazeHelper.getCellPreferSize(gm, panelSize));

                    if (PointType.NOT_AVAILABLE.equals(gm.map[finalI][finalJ])) {
                        setBackground(MazeHelper.DEFAULT_NOT_AVAILABLE_COLOR);
                    } else if (PointType.AVAILABLE.equals(gm.map[finalI][finalJ])) {
                        setBackground(MazeHelper.DEFAULT_AVAILABLE_COLOR);
                    }

                    setBorder(new MatteBorder(1, 1, finalI == rowCount - 1 ? 1 : 0, finalJ == columnCount - 1 ? 1 : 0, Color.DARK_GRAY));
                }};

                add(cells[i][j]);
            }
        }
        cells[0][3].setBackground(Color.CYAN);


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

                  sendPointConsumer.accept(new Point(thisX, thisY));

                }

                if (thisY == rowCount - 1) {
                    JOptionPane.showMessageDialog(null, "����������, �� ������ ����", "���� � �������", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }

                if (e.getKeyCode() == KeyEvent.VK_P && !firstPush) {
                    SearchPath.maze = gm.map;
                    SearchPath.finalPointMy = new PointMy(gm.map.length - 1, gm.map[0].length - 4);
                    SearchPath.searchPath(cells);
                    firstPush = true;
                }
            }
        });

    }

    public void drawEnemyPosition(Point point) {
        if (oldPoint.x != 0 && oldPoint.y != 0) {
            cells[oldPoint.y][oldPoint.x].setBackground(MazeHelper.DEFAULT_AVAILABLE_COLOR);
        }
        cells[point.y][point.x].setBackground(Color.GRAY);
        oldPoint.setLocation(point);
    }

}
