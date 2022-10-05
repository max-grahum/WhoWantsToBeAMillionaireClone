package WhoWantsToBeAMillionaire;

//abstract class to build more specific help-lines from

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;

public abstract class Help implements HelpInterface{
    protected boolean used;
    protected String name;
    
    public Help(String name, boolean used){
        this.name = name;
        this.used = used;
    }
   
    @Override
    public void setUsed(boolean used){
        this.used = used;
    }
     
    @Override
    public boolean isUsed(){
        return used;
    }
}
