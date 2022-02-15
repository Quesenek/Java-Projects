/* Project 12 23.11 BouncingBallPanel.java
 * Name: Marcus Taylor
 * Date: 03-31-20
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class BouncingBallPanel extends JPanel implements MouseListener {
    
    private Ball[] balls;
    private int numBalls;
    private String ballIndicator;

    public BouncingBallPanel() 
    {
        // Set the background, add the mouse listenter to this instance and create the ball array object
        setBackground(Color.BLACK);
        addMouseListener(this);
        balls = new Ball[20];
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D graf = (Graphics2D)g;
        
        // Draw each of the balls in the array.
        for (int i = 0; i < numBalls; i++) {
            graf.setColor(balls[i].getCol());
            // I don't know if it was a typo in the included graphic in the module, but fillOval needs an int and not a double according to the oracle documentation so I cast them as int
            graf.fillOval((int)balls[i].getX(), (int)balls[i].getY(), (int)balls[i].getBallDiameter(), (int)balls[i].getBallDiameter());
        }
        // Added flair to the simulation.  
        // This block of code draws the current number of balls left to use and displays none when it runs out
        if ((20-numBalls) > 0) {
            ballIndicator = "BALLS LEFT: " + (20 - numBalls);
        }
        else
        {
            ballIndicator = "BALLS LEFT: NONE";
        }
        // This changes the color of the text based on how many balls are left to use.
        if ((20-numBalls) >= 10) {
            graf.setColor(Color.GREEN);
        }
        else if ((20-numBalls) >= 6 && (20-numBalls) < 10) {
            graf.setColor(Color.YELLOW);
        }
        else if ((20-numBalls) <= 5) {
            graf.setColor(Color.RED);
        }
        // Draw the string to the panel
        graf.drawString(ballIndicator, 10, 20);
    }

    // I used mouse pressed instead of mouse clicked because instead of a full click and release
    // Mouse pressed just needs the initial click to add the ball to the screen
    @Override
    public void mousePressed(MouseEvent arg0) {
        // Create and add the ball object to the array with the current mouse coordinates along with the current class instance.
        if (numBalls < 20) {
            balls[numBalls] = new Ball(this, arg0.getX(), arg0.getY());
            // Start the new thread for the ball to work in
            (new Thread(balls[numBalls])).start();
            numBalls++;
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent arg0) {}
    @Override
    public void mouseReleased(MouseEvent arg0) {}
    @Override
    public void mouseEntered(MouseEvent arg0) {}
    @Override
    public void mouseExited(MouseEvent arg0) {}
}
