import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class MeteorSky extends JPanel implements ActionListener {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int NUM_STARS = 200;
    private final int NUM_METEORS = 15;

    private Point[] stars;
    private Meteor[] meteors;
    private Timer timer;
    private Random rand;

    public MeteorSky() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        rand = new Random();

        // Generate stars
        stars = new Point[NUM_STARS];
        for (int i = 0; i < NUM_STARS; i++) {
            stars[i] = new Point(rand.nextInt(WIDTH), rand.nextInt(HEIGHT));
        }

        // Generate meteors
        meteors = new Meteor[NUM_METEORS];
        for (int i = 0; i < NUM_METEORS; i++) {
            meteors[i] = new Meteor();
        }

        timer = new Timer(30, this); // Control speed
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Anti-aliasing for smooth lines
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw stars
        g2.setColor(Color.WHITE);
        for (Point star : stars) {
            g2.fillOval(star.x, star.y, 2, 2);
        }

        // Draw meteors with trail
        for (Meteor m : meteors) {
            // Tail effect
            for (int i = 0; i < m.tailLength; i++) {
                float alpha = 1.0f - (float) i / m.tailLength;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                g2.setColor(m.color);
                int tailX = m.x - i * m.dx;
                int tailY = m.y - i * m.dy;
                g2.drawLine(tailX, tailY, tailX - 4, tailY - 4);
            }

            // Reset transparency
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Meteor m : meteors) {
            m.move();
        }
        repaint();
    }

    // Meteor Class with direction and tail
    private class Meteor {
        int x, y;
        int dx, dy;
        int tailLength;
        Color color;

        public Meteor() {
            reset();
        }

        public void reset() {
            x = rand.nextInt(WIDTH);
            y = rand.nextInt(HEIGHT / 2);
            dx = rand.nextInt(3) + 2;  // Speed x
            dy = dx;                   // Same speed in y to move diagonally
            tailLength = rand.nextInt(15) + 10;

            // Flame-like color
            color = new Color(255, rand.nextInt(200), 0); // Orange-Yellow
        }

        public void move() {
            x += dx;
            y += dy;

            if (x > WIDTH || y > HEIGHT) {
                reset();
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sky with Meteors and Stars");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MeteorSky());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
