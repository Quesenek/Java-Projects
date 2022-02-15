/* Project 12 23.11 BouncingBallApp.java
 * Name: Marcus Taylor
 * Date: 03-31-20
 */
import javax.swing.JFrame;

public class BouncingBallApp extends JFrame{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        // Create the frame and set it's parameters
        JFrame frame = new JFrame();
        frame.setTitle("Bouncing Ball");
        // Add a new instance of the bouncingBallPanel class to the frame
        frame.add(new BouncingBallPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }
}
