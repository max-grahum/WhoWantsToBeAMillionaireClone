package WhoWantsToBeAMillionaire;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public abstract class ScreenADT extends JPanel implements ActionListener {
    
    //for buttons and general handling of events
    public abstract void actionPerformed(ActionEvent e);
}
