/* Project 12 23.11 Ball.java
 * Name: Marcus Taylor
 * Date: 03-31-20
 */
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public class Ball implements Runnable 
{
    private final int panelWidth = 600;
    private final int panelHeight = 600;
    private double x;
    private double y;
    private Color col;
    private BouncingBallPanel bbp;
    private int ballSpeed;
    private double ballDiameter;
    private int deltaX;
    private int deltaY;
    

    public Ball(BouncingBallPanel bbp,double x, double y) 
    {
        this.x = x;
        this.y = y;
        // Generate a random number between 0 and 3 for the delta's
        this.deltaX = RandomIntGenerator("delta");
        this.deltaY = RandomIntGenerator("delta");
        // If both of them are 0 the ball stays in place, so this checks to make sure both can't be 0 and corrects it if they are.
        if (deltaX == 0 && deltaY == 0) {
            deltaY++;
        }
        // On creation this generates a color for the ball
        this.col = colGenerator();
        this.bbp = bbp;
        // This generates a random "speed" for the ball
        this.ballSpeed = RandomIntGenerator("Sleep");
        // Call to the diameter generator
        diameter();
    }

    public double getX() {return x;}
    public void setX(double x) {this.x = x;}
    public double getY() {return y;}
    public void setY(double y) {this.y = y;}
    public Color getCol() {return col;}
    public void setCol(Color col) {this.col = col;}
    public double getBallDiameter() {return ballDiameter;}
    
    // Color generator class that keeps an array of the available colors to choose from
    private Color colGenerator()
    {
        Color[] colList = {Color.BLUE, Color.CYAN,
                           Color.DARK_GRAY, Color.GRAY, Color.GREEN,
                           Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
                           Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};
        
        return colList[RandomIntGenerator("Color")];       
    }
    
    // Set class for the diameter, it calls to the randomintgenerator method to get a number
    private void diameter()
    {
        ballDiameter = RandomDoubleGenerator();
    }
    
    // Random int generation method, I used this as a way to consolodate code into one place and the different calls send what number range they need through a string
    private int RandomIntGenerator(String callingMethod)
    {
        int randIntnum = 0;
        
        switch(callingMethod)
        {
            case "Sleep": 
                randIntnum = ThreadLocalRandom.current().nextInt(5, 15);
                break;
            case "Color": 
                randIntnum = ThreadLocalRandom.current().nextInt(0, 12);
                break;
            case "delta": 
                randIntnum = ThreadLocalRandom.current().nextInt(0, 3);
                break;
        }
        
        return randIntnum;
    }
    // Added flair Random double generator, this sends back a double in range of 35-60 
    private double RandomDoubleGenerator()
    {
        return ThreadLocalRandom.current().nextDouble(35.0, 60.0);
    }

    @Override
    public void run() 
    {
        move();
    }
    
    private void move()
    {
        while(true)
        {
            x = x + deltaX;
            y = y + deltaY;
            
            // This sets the bounds for the balls, once they reach an edge this changes direction of the ball.
            // Added flair I made it so that once it made contact the ball changed color.
            if (x+(ballDiameter)*1.5 > panelWidth || x <= 0) 
            {
                deltaX = -deltaX;
                setCol(colGenerator());
            }
            if (y+(ballDiameter)*2 > panelHeight || y <= 0) 
            {
                deltaY = -deltaY;
                setCol(colGenerator());
            }
            
            bbp.repaint();
            try 
            {
                Thread.sleep(ballSpeed);
            } 
            catch (Exception e) 
            {
                System.out.println("Sleep ran into an issue: " + e);
            }
        }
    }
}
