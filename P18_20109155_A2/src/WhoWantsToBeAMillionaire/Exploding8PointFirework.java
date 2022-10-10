package WhoWantsToBeAMillionaire;

import java.awt.Graphics;

public class Exploding8PointFirework extends ExplodingFirework {

    public Exploding8PointFirework(Firework firework) {
        super(firework);
    }

    @Override
    public void draw(Graphics g) {
        this.firework.draw(g);
        if (this.firework.dead) {
            this.exploded = true;
            int x = (int) this.firework.xPos;
            int y = (int) (ScreenController.HEIGHT - this.firework.yPos);
            g.drawLine(x, y, x + super.SIZE, y);
            g.drawLine(x, y, x + super.SIZE, y + super.SIZE);
            g.drawLine(x, y, x, y + super.SIZE);
            g.drawLine(x, y, x - super.SIZE, y + super.SIZE);
            g.drawLine(x, y, x - super.SIZE, y);
            g.drawLine(x, y, x - super.SIZE, y - super.SIZE);
            g.drawLine(x, y, x, y - super.SIZE);
            g.drawLine(x, y, x + super.SIZE, y - super.SIZE);
        }
    }

}
