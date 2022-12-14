package WhoWantsToBeAMillionaire;

//decorator class
import java.awt.Graphics;

//firework decorator class
public class ExplodingFirework {

    //instance of firework to be deccorated
    protected Firework firework;
    protected boolean exploded;

    protected final int SIZE;

    public ExplodingFirework(Firework firework) {
        this.firework = firework;
        this.exploded = false;
        //this.SIZE = this.firework.rand.nextInt(50) + 50;
        this.SIZE = 100;
    }

    public void launch() {
        this.firework.launch();
    }

    public void draw(Graphics g) {
        this.firework.draw(g);
        if (this.firework.dead) {
            this.exploded = true;
            g.drawOval((int) this.firework.xPos - (this.SIZE / 2), (int) (ScreenController.HEIGHT - this.firework.yPos) - (this.SIZE / 2), SIZE, SIZE);
        }
    }

}
