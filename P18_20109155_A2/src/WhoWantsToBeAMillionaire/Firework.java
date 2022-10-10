package WhoWantsToBeAMillionaire;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class Firework {

    public Color colour;
    public Random rand;
    
    public boolean launched = false, dead = false;

    public final int POINT_SIZE = 10, MAX_HEIGHT = 45, MIN_HEIGHT = 20, HEIGHT;
    public final float DAMPENING = 0.1f;
    
    public float ySpeed = 12, xSpeed = 0,
            yPos = 10, xPos,
            xAcc;

    private ArrayList<Point2D> pathPoints;

    public Timer timer;

    public Firework(Color colour) {
        this.rand = new Random();

        this.colour = colour;
        this.xPos = this.rand.nextInt(ScreenController.WIDTH - 50) + 25;
        this.xAcc = (rand.nextFloat() * 2) - 1.0f;
        
        this.HEIGHT = this.rand.nextInt(MAX_HEIGHT-MIN_HEIGHT) + MIN_HEIGHT;
        
        this.pathPoints = new ArrayList<>();
        this.pathPoints.add(new Point2D((int)this.xPos, (int)(this.yPos)));
    }

    public void launch() {
        this.launched = true;
    }

    public void update() {
        if(!dead){
            this.yPos += this.ySpeed;
            this.xSpeed += this.xAcc;
            this.xSpeed *= (1.0f - DAMPENING);
            this.xPos += this.xSpeed;
            this.pathPoints.add(new Point2D((int)this.xPos, (int)this.yPos));
            if(this.pathPoints.size() > this.HEIGHT){
                this.die();
            }
        }
    }

    public void die() {
        this.dead = true;
    }

    public void draw(Graphics g) {
        this.update();
        g.setColor(this.colour);
        for (int i = 0; i < this.pathPoints.size(); i++) {
            g.fillOval(this.pathPoints.get(i).x, (int) (ScreenController.HEIGHT - this.pathPoints.get(i).y), POINT_SIZE, POINT_SIZE);
        }
    }
}
