package WhoWantsToBeAMillionaire;

import java.awt.Graphics;

//expansion on the default exploding firework to give it a different explosion
public class ExplodingSquareFirework extends ExplodingFirework {

    public ExplodingSquareFirework(Firework firework) {
        super(firework);
    }

    //overriden draw method
    @Override
    public void draw(Graphics g) {
        this.firework.draw(g);
        if (this.firework.dead) {
            this.exploded = true;
            g.drawRect((int) this.firework.xPos - (this.SIZE / 2), (int) (ScreenController.HEIGHT - this.firework.yPos) - (this.SIZE / 2), SIZE, SIZE);
        }
    }

}
