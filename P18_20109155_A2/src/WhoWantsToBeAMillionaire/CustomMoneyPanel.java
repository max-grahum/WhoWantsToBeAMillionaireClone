package WhoWantsToBeAMillionaire;

import java.awt.Color;
import java.awt.Graphics;

//represents the bars with money on them in the money screen
public class CustomMoneyPanel {

    //price
    private int value;

    private Color colour;
    private final int WIDTH, HEIGHT;

    public CustomMoneyPanel(int value, Color colour) {
        this.value = value;
        this.colour = colour;
        this.WIDTH = 500;
        this.HEIGHT = 40;
    }

    //sets the colour of the panel
    public void setColour(Color colour) {
        this.colour = colour;
    }

    //get the colour of the panel
    public Color getColour() {
        return this.colour;
    }

    //draw method
    public void drawCustomMoneyPanel(Graphics g, int x, int y) {
        g.setColor(this.colour);
        g.fillRect(x, y, this.WIDTH, this.HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, this.WIDTH, this.HEIGHT);
        g.drawString("$" + Integer.toString(this.value), x + this.WIDTH / 2 - 25, y + this.HEIGHT / 2 + 10);
    }
}
