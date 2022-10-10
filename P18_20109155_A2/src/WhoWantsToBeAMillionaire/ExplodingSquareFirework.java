package WhoWantsToBeAMillionaire;

import java.awt.Graphics;

public class ExplodingSquareFirework extends ExplodingFirework {

    public ExplodingSquareFirework(Firework firework) {
        super(firework);
    }

    @Override
    public void draw(Graphics g) {
        this.firework.draw(g);
        if (this.firework.dead) {
            this.exploded = true;
            g.drawRect((int) this.firework.xPos - (this.SIZE / 2), (int) (ScreenController.HEIGHT - this.firework.yPos) - (this.SIZE / 2), SIZE, SIZE);
        }
    }

}
