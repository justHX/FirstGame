package firstgame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;


@SuppressWarnings("WeakerAccess")
public class PaintMap extends JPanel {
    
    private int thisY = 0;
    private int thisX = 3;
    private JPanel[][] cells;


    public PaintMap(final GameMap gm, final Dimension panelSize) {
        setFocusable(true);
        int rowCount = gm.map.length;
        int columnCount = gm.map[0].length;
        setLayout(new GridLayout(gm.map.length, gm.map[0].length));


        MazeHelper.generateLattice(gm.map);
        gm.map[1][1] = PointType.AVAILABLE;
        Point currentPosition = MazeHelper.generateStartAndEndPosition(gm.map);
        MazeHelper.generatePath(gm.map, currentPosition);



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
                }

                if (thisY == rowCount - 1) {
                    JOptionPane.showMessageDialog(null, "Поздравляю, Вы прошли игру", "Скромное окно о победе", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            }
        });
        
    }
    
}
