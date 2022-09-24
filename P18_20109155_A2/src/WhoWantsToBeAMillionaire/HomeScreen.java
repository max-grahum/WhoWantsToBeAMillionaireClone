/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WhoWantsToBeAMillionaire;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author max_g
 */
public class HomeScreen extends ScreenADT{

    private DrawPanel drawPanel;
    private Timer timer;
    
    public HomeScreen(){
        this.drawPanel = new DrawPanel();
        
        timer = new Timer(25, this);
        timer.start();
        
        this.add(this.drawPanel);
    }
    
    public class DrawPanel extends JPanel {

        //setup draw panel
        public DrawPanel(){
            super.setPreferredSize(new Dimension(1200, 800));
            super.setBackground(Color.WHITE);
        }
        
        //custom painting goes here
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
