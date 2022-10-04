package WhoWantsToBeAMillionaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MoneyScreen extends JPanel implements ActionListener {

    private final DrawPanel drawPanel;

    private final CustomMoneyPanel[] moneyPanels;
    private final int[] values;
    private final Color[] colours;

    private final int currentIndex;

    private final Timer timer;

    public MoneyScreen() {
        super(new BorderLayout());
        this.drawPanel = new DrawPanel();

        currentIndex = 3;

        this.values = new int[]{
            100, 200, 300, 500, 1000, 2000, 4000, 8000, 16000,
            32000, 64000, 125000, 250000, 500000, 1000000
        };

        this.colours = new Color[]{Color.cyan, Color.YELLOW, Color.RED};

        this.moneyPanels = new CustomMoneyPanel[this.values.length];
        for (int i = 0; i < this.moneyPanels.length; i++) {
            Color currentColour;
            if (i < currentIndex) {
                currentColour = this.colours[0];
            } else if (i == currentIndex) {
                currentColour = this.colours[1];
            } else {
                currentColour = this.colours[2];
            }

            this.moneyPanels[i] = new CustomMoneyPanel(this.values[i], currentColour);
        }

        timer = new Timer(25, this);
        timer.start();

        super.add(this.drawPanel);
    }

    public class DrawPanel extends JPanel {

        //setup draw panel
        public DrawPanel() {
            super.setPreferredSize(new Dimension(ScreenGUI.WIDTH, ScreenGUI.HEIGHT));
            super.setBackground(Color.BLUE);
            super.setFont(new Font("Ariel", Font.BOLD, 32));
        }

        //custom painting goes here
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            for (int i = 0; i < moneyPanels.length; i++) {
                moneyPanels[moneyPanels.length-1 - i].drawCustomMoneyPanel(g, 250, 100 + (i * 45));
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //get the events source component;
        Object source = e.getSource();

        //if timer finished
        if (source == timer) {
            drawPanel.repaint();
        }

    }

}
