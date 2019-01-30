package firstgame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("WeakerAccess")
public class FieldGame extends JFrame {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;


    public FieldGame(final GameMap gm) {
        this.setTitle("First game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - WIDTH / 2, screenSize.height / 2 - HEIGHT / 2, WIDTH, HEIGHT);

        JPanel panel = new JPanel() {{
            setSize(FieldGame.WIDTH - 40, FieldGame.HEIGHT - 40);
            setLayout(new FlowLayout());
            add(new PaintMap(gm, getSize()));
        }};

        this.add(panel);
    }

}

