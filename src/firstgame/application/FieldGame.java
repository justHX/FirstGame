package firstgame.application;

import java.awt.*;
import java.util.function.Consumer;
import javax.swing.*;


public class FieldGame extends JFrame {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;


    private PaintMap paintMap;

    public FieldGame(final GameMap gm, final Consumer<Point> sendPointConsumer) {
        this.setTitle("First game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - WIDTH / 2, screenSize.height / 2 - HEIGHT / 2, WIDTH, HEIGHT);

        JPanel panel = new JPanel() {{
            setSize(FieldGame.WIDTH - 40, FieldGame.HEIGHT - 40);
            setLayout(new FlowLayout());
        }};

        paintMap = new PaintMap(gm, getSize(), sendPointConsumer);
        panel.add(paintMap);

        this.add(panel);

    }

    public PaintMap getPaintMap() {
        return paintMap;
    }
}

