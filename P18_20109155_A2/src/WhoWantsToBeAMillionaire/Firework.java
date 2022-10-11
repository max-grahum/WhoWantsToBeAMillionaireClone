package WhoWantsToBeAMillionaire;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Firework {

    public Color colour;
    public Random rand;

    //firework constants
    public final int POINT_SIZE = 10, MAX_HEIGHT = 45, MIN_HEIGHT = 20, HEIGHT;
    public final float DAMPENING = 0.1f;

    //position and speed numbers
    public float ySpeed = 12, xSpeed = 0,
            yPos = 10, xPos,
            xAcc;

    //stateflags
    public boolean launched = false, dead = false;

    //collection of all points
    private ArrayList<Point2D> pathPoints;

    public Firework(Color colour) {
        this.rand = new Random();

        this.colour = colour;

        //generate a random starting position
        this.xPos = this.rand.nextInt(ScreenController.WIDTH - 50) + 25;

        //generate a random horizontal acceleration 
        this.xAcc = (rand.nextFloat() * 2) - 1.0f;

        //generate amount of dots high it will go to
        this.HEIGHT = this.rand.nextInt(MAX_HEIGHT - MIN_HEIGHT) + MIN_HEIGHT;

        //setup path points holder
        this.pathPoints = new ArrayList<>();
        this.pathPoints.add(new Point2D((int) this.xPos, (int) (this.yPos)));
    }

    //launch the firework
    public void launch() {
        this.launched = true;
    }

    //update position of the fire work and add a new tracking dot
    public void update() {
        if (!dead) {
            this.yPos += this.ySpeed;
            this.xSpeed += this.xAcc;
            this.xSpeed *= (1.0f - DAMPENING);
            this.xPos += this.xSpeed;
            this.pathPoints.add(new Point2D((int) this.xPos, (int) this.yPos));
            if (this.pathPoints.size() > this.HEIGHT) {
                this.die();
            }
        }
    }

    //stop the firework
    public void die() {
        this.dead = true;
    }

    //draw the points
    public void draw(Graphics g) {

        //only draw if the firework has launched
        if (launched) {
            this.update();
            g.setColor(this.colour);
            for (int i = 0; i < this.pathPoints.size(); i++) {
                g.fillOval(this.pathPoints.get(i).x, (int) (ScreenController.HEIGHT - this.pathPoints.get(i).y), POINT_SIZE, POINT_SIZE);
            }
        }
    }
}
