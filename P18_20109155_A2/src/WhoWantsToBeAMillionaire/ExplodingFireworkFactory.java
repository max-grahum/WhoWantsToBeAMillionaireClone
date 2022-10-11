package WhoWantsToBeAMillionaire;

import java.awt.Color;
import java.util.Random;

//uses the factory pattern to create random exploding fireworks
public class ExplodingFireworkFactory {

    private Random rand;
    private Color[] colours;

    public ExplodingFireworkFactory() {
        this.rand = new Random();
        this.colours = new Color[]{Color.RED, Color.GREEN, Color.YELLOW, Color.GRAY, Color.WHITE, Color.WHITE, Color.PINK, Color.MAGENTA, Color.CYAN, Color.ORANGE};
    }

    //creates a random exploding firework
    public ExplodingFirework createRandomExplodingFirework() {
        int randomNumber = this.rand.nextInt(3);
        ExplodingFirework explodingFirework = null;
        switch (randomNumber) {
            case 0:
                explodingFirework = new ExplodingFirework(new Firework(colours[this.rand.nextInt(this.colours.length)]));
                break;
            case 1:
                explodingFirework = new ExplodingSquareFirework(new Firework(colours[this.rand.nextInt(this.colours.length)]));
                break;
            case 2:
                explodingFirework = new Exploding8PointFirework(new Firework(colours[this.rand.nextInt(this.colours.length)]));
                break;
            default:
                System.out.println("ERROR:Illegal argument!");
        }
        return explodingFirework;
    }
}
