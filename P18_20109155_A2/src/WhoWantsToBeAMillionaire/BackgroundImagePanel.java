package WhoWantsToBeAMillionaire;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

//for home and QA screen decorations
public class BackgroundImagePanel extends JPanel {

    private Image image;

    //setup draw panel
    public BackgroundImagePanel() {
        super(new FlowLayout(FlowLayout.LEADING, 0, 0));
        super.setPreferredSize(new Dimension(ScreenController.WIDTH, ScreenController.HEIGHT / 2));
        super.setBackground(Color.BLACK);
        this.image = new ImageIcon("./resources/millionaire_bg.PNG").getImage();
    }

    //custom painting goes here
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, -170, -200, null);

    }
}
