package WhoWantsToBeAMillionaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FinaleScreen extends JPanel implements ActionListener {

    private final DrawPanel drawPanel;
    
    private ScreenController screenGUIContext;

    private Timer timer;

    public FinaleScreen(ScreenController screenGUIContext) {
        super(new BorderLayout());
        this.drawPanel = new DrawPanel();
        
        this.screenGUIContext = screenGUIContext;

        timer = new Timer(25, this);
        timer.start();

        super.add(this.drawPanel, BorderLayout.NORTH);
    }

    public class DrawPanel extends JPanel {

        //setup draw panel
        public DrawPanel() {
            super.setPreferredSize(new Dimension(ScreenController.WIDTH, ScreenController.HEIGHT));
            super.setBackground(Color.BLUE);
        }

        //custom painting goes here
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            
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
