package WhoWantsToBeAMillionaire;

import java.awt.BorderLayout;
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
