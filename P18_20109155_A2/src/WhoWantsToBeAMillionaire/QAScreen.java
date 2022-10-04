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

public class QAScreen extends JPanel implements ActionListener {

    private final JPanel questionPanel;
    
    private Timer timer;
    
    public QAScreen(){
        super(new BorderLayout());
        super.setPreferredSize(new Dimension(ScreenGUI.WIDTH, ScreenGUI.HEIGHT));
        
        this.questionPanel = new QuestionPanel();
        this.questionPanel.setPreferredSize(new Dimension(ScreenGUI.WIDTH, ScreenGUI.HEIGHT/2));
        
        timer = new Timer(25, this);
        timer.start();
        
        super.add(this.questionPanel, BorderLayout.SOUTH);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //get the events source component;
        Object source = e.getSource();
        
       
    }
    
}

