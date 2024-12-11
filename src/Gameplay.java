import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private int[] snakexlength = new int[750];
    private int[] snakeylength = new int[750];

    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon snakeimage;

    private int[] enemyxpos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475,
            500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};

    private int[] enemyypos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500,
            525, 550, 575, 600, 625};

    private ImageIcon enemyimage;

    private Random random = new Random();

    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);

    private Timer timer;
    private int delay = 300;

    private int lengthofsnake = 3;
    private int moves = 0;
    private int scores = 0;

    private ImageIcon titleImage;

    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {

        if (moves == 0) {
            snakexlength[0] = 100;
            snakexlength[1] = 75;
            snakexlength[2] = 50;

            snakeylength[0] = 100;
            snakeylength[1] = 100;
            snakeylength[2] = 100;
        }

        // Border of title image
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);

        // Load title image
        titleImage = new ImageIcon(getClass().getResource("/resources/snaketitle.jpg"));
        titleImage.paintIcon(this, g, 25, 11);

        // Border of gameplay
        g.setColor(Color.white);
        g.drawRect(24, 74, 851, 577);
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);

        // Draw the scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Scores : " + scores, 780, 30);
        g.drawString("Length : " + lengthofsnake, 780, 50);

        // Snake's head
        if (right) {
            rightmouth = new ImageIcon(getClass().getResource("/resources/rightmouth.png"));
            rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        } else if (left) {
            leftmouth = new ImageIcon(getClass().getResource("/resources/leftmouth.png"));
            leftmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        } else if (up) {
            upmouth = new ImageIcon(getClass().getResource("/resources/upmouth.png"));
            upmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        } else if (down) {
            downmouth = new ImageIcon(getClass().getResource("/resources/downmouth.png"));
            downmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        }

        // Snake's body
        for (int a = 1; a < lengthofsnake; a++) {
            snakeimage = new ImageIcon(getClass().getResource("/resources/snakeimage.png"));
            snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
        }

        // Enemy
        enemyimage = new ImageIcon(getClass().getResource("/resources/enemy.png"));
        if (enemyxpos[xpos] == snakexlength[0] && enemyypos[ypos] == snakeylength[0]) {
            lengthofsnake++;
            scores++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }
        enemyimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);

        // Game Over
        for (int b = 1; b < lengthofsnake; b++) {
            if (snakexlength[b] == snakexlength[0] && snakeylength[b] == snakeylength[0]) {
                right = false;
                left = false;
                up = false;
                down = false;

                g.setColor(Color.WHITE);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("Game Over!", 300, 300);

                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString("Space to RESTART", 350, 340);
            }
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (right) {
            for (int i = lengthofsnake - 1; i >= 0; i--) {
                snakeylength[i + 1] = snakeylength[i];
            }
            for (int i = lengthofsnake; i >= 0; i--) {
                if (i == 0) {
                    snakexlength[i] += 25;
                } else {
                    snakexlength[i] = snakexlength[i - 1];
                }
                if (snakexlength[i] > 850) {
                    snakexlength[i] = 25;
                }
            }
        } else if (left) {
            for (int i = lengthofsnake - 1; i >= 0; i--) {
                snakeylength[i + 1] = snakeylength[i];
            }
            for (int i = lengthofsnake; i >= 0; i--) {
                if (i == 0) {
                    snakexlength[i] -= 25;
                } else {
                    snakexlength[i] = snakexlength[i - 1];
                }
                if (snakexlength[i] < 25) {
                    snakexlength[i] = 850;
                }
            }
        } else if (up) {
            for (int i = lengthofsnake - 1; i >= 0; i--) {
                snakexlength[i + 1] = snakexlength[i];
            }
            for (int i = lengthofsnake; i >= 0; i--) {
                if (i == 0) {
                    snakeylength[i] -= 25;
                } else {
                    snakeylength[i] = snakeylength[i - 1];
                }
                if (snakeylength[i] < 75) {
                    snakeylength[i] = 625;
                }
            }
        } else if (down) {
            for (int i = lengthofsnake - 1; i >= 0; i--) {
                snakexlength[i + 1] = snakexlength[i];
            }
            for (int i = lengthofsnake; i >= 0; i--) {
                if (i == 0) {
                    snakeylength[i] += 25;
                } else {
                    snakeylength[i] = snakeylength[i - 1];
                }
                if (snakeylength[i] > 625) {
                    snakeylength[i] = 75;
                }
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && !left) {
            moves++;
            right = true;
            left = false;
            up = false;
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && !right) {
            moves++;
            left = true;
            right = false;
            up = false;
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && !down) {
            moves++;
            up = true;
            down = false;
            left = false;
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && !up) {
            moves++;
            down = true;
            up = false;
            left = false;
            right = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            moves = 0;
            scores = 0;
            lengthofsnake = 3;
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
