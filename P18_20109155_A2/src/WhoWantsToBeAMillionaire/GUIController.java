package WhoWantsToBeAMillionaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JPanel;

public class GUIController extends JPanel {

    private JPanel currentPanel;
    
    public GUIController(JPanel startPanel){
        super(new BorderLayout());

        //UI setup
        this.changeScreen(startPanel);
    }
    
    //changes panel to whatever you pass through
    public void changeScreen(JPanel newPanel){
        super.removeAll();
        this.currentPanel = newPanel;
        super.add(this.currentPanel);
    }
    
    
    
    
}
