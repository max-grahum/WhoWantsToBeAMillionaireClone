package WhoWantsToBeAMillionaire;

import java.awt.Graphics;

//extension of the default explodion firework
public class Exploding8PointFirework extends ExplodingFirework {

    public Exploding8PointFirework(Firework firework) {
        super(firework);
    }

    //overridden draw method to draw a different shape
    @Override
    public void draw(Graphics g) {
        this.firework.draw(g);
        if (this.firework.dead) {
            this.exploded = true;
            int x = (int) this.firework.xPos + 5;
            int y = (int) (ScreenController.HEIGHT - this.firework.yPos) + 5;
            g.drawLine(x, y, x + super.SIZE, y);
            g.drawLine(x, y, (int) (x + super.SIZE * Math.sin(45.0)), (int) (y + super.SIZE * Math.sin(45.0)));
            g.drawLine(x, y, x, y + super.SIZE);
            g.drawLine(x, y, (int) (x - super.SIZE * Math.sin(45.0)), (int) (y + super.SIZE * Math.sin(45.0)));
            g.drawLine(x, y, x - super.SIZE, y);
            g.drawLine(x, y, (int) (x - super.SIZE * Math.sin(45.0)), (int) (y - super.SIZE * Math.sin(45.0)));
            g.drawLine(x, y, x, y - super.SIZE);
            g.drawLine(x, y, (int) (x + super.SIZE * Math.sin(45.0)), (int) (y - super.SIZE * Math.sin(45.0)));
        }
    }

}
