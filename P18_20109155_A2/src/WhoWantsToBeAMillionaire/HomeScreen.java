/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WhoWantsToBeAMillionaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author max_g
 */
public class HomeScreen extends JPanel implements ActionListener {

    private final DrawPanel drawPanel;
    private final JPanel buttonPanel;
    
    private final JButton newGameBtn, continueBtn, quitBtn;
    private final Dimension buttonSize;
    
    private Timer timer;
    
    
    
    public HomeScreen(){
        super(new BorderLayout());
        this.drawPanel = new DrawPanel();
        
        this.buttonPanel = new JPanel();
        this.buttonPanel.setPreferredSize(new Dimension(1000, 400));
        this.buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 10));
        this.buttonPanel.setBackground(Color.BLUE);
        
        this.buttonSize = new Dimension(500, 80);
        
        this.newGameBtn = new JButton();
        this.newGameBtn.setText("New Game");
        this.newGameBtn.addActionListener(this);
        this.newGameBtn.setPreferredSize(buttonSize);
        
        this.continueBtn = new JButton();
        this.continueBtn.setText("Continue");
        this.continueBtn.addActionListener(this);
        this.continueBtn.setPreferredSize(buttonSize);
        
        this.quitBtn = new JButton();
        this.quitBtn.setText("Quit");
        this.quitBtn.addActionListener(this);
        this.quitBtn.setPreferredSize(buttonSize);
        
        this.buttonPanel.add(newGameBtn);
        this.buttonPanel.add(continueBtn);
        this.buttonPanel.add(quitBtn);
        
        
        timer = new Timer(25, this);
        timer.start();
        
        super.add(this.drawPanel, BorderLayout.NORTH);
        super.add(this.buttonPanel, BorderLayout.SOUTH);
    }
    
    public class DrawPanel extends JPanel {

        //setup draw panel
        public DrawPanel(){
            super.setPreferredSize(new Dimension(1200, 400));
            super.setBackground(Color.BLUE);
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
        if (source == newGameBtn){
            System.out.println("NewGame!");
        }
        if(source == continueBtn){
            System.out.println("Continue!");
        }
        if(source == quitBtn){
            System.out.println("Quit!");
        }
    }
    
}
