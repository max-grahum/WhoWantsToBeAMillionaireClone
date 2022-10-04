package WhoWantsToBeAMillionaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PauseScreen extends JPanel implements ActionListener {

    private final JPanel buttonPanel;
    
    private final JButton continueBtn, homeBtn;
    private final Dimension buttonSize, panelSize;
    
    
    public PauseScreen(){
        super(new BorderLayout());
        super.setPreferredSize(new Dimension(ScreenGUI.WIDTH, ScreenGUI.HEIGHT));
        
        this.panelSize = new Dimension(ScreenGUI.WIDTH, ScreenGUI.HEIGHT/2);
        
        this.buttonPanel = new JPanel();
        this.buttonPanel.setPreferredSize(panelSize);
        this.buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 10));
        this.buttonPanel.setBackground(Color.BLUE);
        
        this.buttonSize = new Dimension(500, 80);
   
        this.continueBtn = new JButton();
        this.continueBtn.setText("Continue");
        this.continueBtn.addActionListener(this);
        this.continueBtn.setPreferredSize(buttonSize);
        this.continueBtn.setFont(new Font("Ariel", Font.BOLD, 32));
        
        this.homeBtn = new JButton();
        this.homeBtn.setText("Home");
        this.homeBtn.addActionListener(this);
        this.homeBtn.setPreferredSize(buttonSize);
        this.homeBtn.setFont(new Font("Ariel", Font.BOLD, 32));
        
        this.buttonPanel.add(continueBtn);
        this.buttonPanel.add(homeBtn);
        
        
        super.add(this.buttonPanel, BorderLayout.SOUTH);
    }
    
  
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //get the events source component;
        Object source = e.getSource();
      
        if(source == continueBtn){
            System.out.println("Continue!");
        }
        if(source == homeBtn){
            System.out.println("Quit!");
        }
    }
    
}
