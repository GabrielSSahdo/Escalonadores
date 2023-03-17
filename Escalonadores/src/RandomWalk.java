import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RandomWalk extends JPanel {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int WALK_LENGTH = 10000;
    private static final int STEP_SIZE = 5;

    private Point position;

    public RandomWalk() {
        position = new Point(WINDOW_WIDTH/2, WINDOW_HEIGHT/2);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Random rand = new Random();
        g.fillRect(position.x, position.y, 3, 3);

        for (int i = 0; i < WALK_LENGTH; i++) {
            int direction = rand.nextInt(4);
            switch (direction) {
                case 0:
                    position.x += STEP_SIZE;
                    break;
                case 1:
                    position.x -= STEP_SIZE;
                    break;
                case 2:
                    position.y += STEP_SIZE;
                    break;
                case 3:
                    position.y -= STEP_SIZE;
                    break;
            }
            g.fillRect(position.x, position.y, 3, 3);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.add(new RandomWalk());
        frame.setVisible(true);
    }
}
