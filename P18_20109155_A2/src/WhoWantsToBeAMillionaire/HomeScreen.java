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
import java.awt.Font;
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

    private ScreenController screenGUIContext;
    
    private final JPanel buttonPanel;
    
    private final SaveManager saveManager;
    
    private final JButton newGameBtn, continueBtn, quitBtn;
    private final Dimension buttonSize, panelSize;
    
    private Timer timer;
    
    
    
    public HomeScreen(ScreenController screenGUIContext){
        super(new BorderLayout());
        super.setPreferredSize(new Dimension(ScreenController.WIDTH, ScreenController.HEIGHT));
        
        this.screenGUIContext = screenGUIContext;
        
        this.saveManager = SaveManager.getInstance();
        
        this.panelSize = new Dimension(ScreenController.WIDTH, ScreenController.HEIGHT/2);
        
        this.buttonPanel = new JPanel();
        this.buttonPanel.setPreferredSize(panelSize);
        this.buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 10));
        this.buttonPanel.setBackground(Color.BLUE);
        
        this.buttonSize = new Dimension(500, 80);
        
        this.newGameBtn = new JButton();
        this.newGameBtn.setText("New Game");
        this.newGameBtn.addActionListener(this);
        this.newGameBtn.setPreferredSize(buttonSize);
        this.newGameBtn.setFont(new Font("Ariel", Font.BOLD, 32));
        
        this.continueBtn = new JButton();
        this.continueBtn.setText("Continue");
        this.continueBtn.addActionListener(this);
        this.continueBtn.setPreferredSize(buttonSize);
        this.continueBtn.setFont(new Font("Ariel", Font.BOLD, 32));
        
        if(this.saveManager.getQuestionNumber() <= 0){
            this.continueBtn.setEnabled(false);
        }
        
        this.quitBtn = new JButton();
        this.quitBtn.setText("Quit");
        this.quitBtn.addActionListener(this);
        this.quitBtn.setPreferredSize(buttonSize);
        this.quitBtn.setFont(new Font("Ariel", Font.BOLD, 32));
        
        this.buttonPanel.add(newGameBtn);
        this.buttonPanel.add(continueBtn);
        this.buttonPanel.add(quitBtn);
        
        
        timer = new Timer(25, this);
        timer.start();
        
        super.add(this.buttonPanel, BorderLayout.SOUTH);
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //get the events source component;
        Object source = e.getSource();
        
        if (source == newGameBtn){
            System.out.println("New Game!");
            this.saveManager.clearData();
            this.screenGUIContext.gotoMoneyScreen();
        }
        if(source == continueBtn){
            System.out.println("Continue!");
            this.screenGUIContext.gotoMoneyScreen();
        }
        if(source == quitBtn){
            System.out.println("Quit!");
            this.screenGUIContext.close();
        }
    }
    
}
