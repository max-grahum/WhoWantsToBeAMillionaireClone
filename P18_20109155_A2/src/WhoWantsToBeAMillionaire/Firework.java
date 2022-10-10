package WhoWantsToBeAMillionaire;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

public class Firework {
    
    private int baseX;
    private Random rand;
    
    private Graphics g;
    
    private Timer timer;
    
    public Firework(Graphics g){
        this.baseX = rand.nextInt(ScreenController.WIDTH-100)+50;
        this.g = g;
    }
    
    public void launch(){
        this.timer = new Timer(30, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                draw();
            }
        });
    }
    
    public void update(){
        
    }
    
    public void draw(){
        this.g.drawRect(this.baseX, ScreenController.HEIGHT - 100, 100, 100);
    }
}
