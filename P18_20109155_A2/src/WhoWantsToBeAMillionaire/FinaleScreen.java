package WhoWantsToBeAMillionaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FinaleScreen extends JPanel implements ActionListener {
    
    private final DrawPanel drawPanel;
    
    private ScreenController screenGUIContext;
    
    private ExplodingFireworkFactory explodingFireworkFactory;
    
    private final int FIREWORK_AMOUNT = 15;
    private ArrayList<ExplodingFirework> fireworks;
    
    private Timer drawTimer, launchTimer;
    private int currentFirework = 0;
    
    public FinaleScreen(ScreenController screenGUIContext) {
        super(new BorderLayout());
        this.drawPanel = new DrawPanel();
        
        this.screenGUIContext = screenGUIContext;
        
        this.explodingFireworkFactory = new ExplodingFireworkFactory();
        this.fireworks = new ArrayList<>();
        for (int i = 0; i < FIREWORK_AMOUNT; i++) {
            this.fireworks.add(this.explodingFireworkFactory.createRandomExplodingFirework());
        }
        
        drawTimer = new Timer(40, this);
        drawTimer.start();
        
        launchTimer = new Timer(500, this);
        
        super.add(this.drawPanel, BorderLayout.NORTH);
    }
    
    public void startFinale() {
        this.launchTimer.start();
    }
    
    public class DrawPanel extends JPanel {
        
        private JButton homeBtn;

        //setup draw panel
        public DrawPanel() {
            super(new FlowLayout(FlowLayout.CENTER, 0, 400));
            super.setPreferredSize(new Dimension(ScreenController.WIDTH, ScreenController.HEIGHT));
            super.setBackground(Color.BLUE);
            
            this.homeBtn = new JButton("Home");
            this.homeBtn.setFont(new Font("Ariel", Font.BOLD, 48));
            this.homeBtn.setPreferredSize(new Dimension(300, 100));
            this.homeBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SaveManager.getInstance().clearData();
                    screenGUIContext.gotoHomeScreen();
                }
            });
            super.add(this.homeBtn);
        }

        //custom painting goes here
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            for (ExplodingFirework firework : fireworks) {
                firework.draw(g);
            }
            
            g.setColor(Color.BLACK);
            g.fillRect((ScreenController.WIDTH / 2) - 400, (ScreenController.HEIGHT / 2) - 200, 800, 175);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Ariel", Font.BOLD, 64));
            g.drawString("CONGRATULATIONS!!", (ScreenController.WIDTH / 2) - 350, (ScreenController.HEIGHT / 2) - 100);
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        //get the events source component;
        Object source = e.getSource();

        //if drawTimer finished
        if (source == drawTimer) {
            drawPanel.repaint();
        }
        if (source == launchTimer) {
            this.fireworks.get(currentFirework).launch();
            currentFirework++;
            if (currentFirework >= this.fireworks.size()) {
                launchTimer.stop();
            }
        }
    }
}
