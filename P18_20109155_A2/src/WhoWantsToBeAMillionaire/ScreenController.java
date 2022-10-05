package WhoWantsToBeAMillionaire;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ScreenController extends JFrame implements ActionListener {

    private final String TITLE = "Who Wants To Be A Millionaire!";
    public static final int WIDTH = 1000, HEIGHT = 800;
    
    private JFrame frame;

    private JPanel panelHolder;
    
    private final HomeScreen homeScreen;
    private final QAScreen qaScreen;
    private final MoneyScreen moneyScreen;
    private final PauseScreen pauseScreen;
    private final FinaleScreen finaleScreen;
    
    private Timer moveOnTimer;

    public ScreenController() {
       
        panelHolder = new JPanel(new CardLayout());
        
        //home screen
        this.homeScreen = new HomeScreen(this);
        this.moneyScreen = new MoneyScreen(this);
        this.qaScreen = new QAScreen(this);
        this.pauseScreen = new PauseScreen(this);
        this.finaleScreen = new FinaleScreen(this);
 
        this.panelHolder.add(this.homeScreen, "home");
        this.panelHolder.add(this.moneyScreen, "money");
        this.panelHolder.add(this.qaScreen, "qa");
        this.panelHolder.add(this.pauseScreen, "pause");
        this.panelHolder.add(this.finaleScreen, "finale");
        
        
        //GUI Setup
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().add(this.panelHolder);
        frame.pack();
        
        moveOnTimer = new Timer(1000, this);
        
        this.gotoHomeScreen();
    }

    private void changeScreen(String key){
        System.out.println("Changing screens!");

        CardLayout cl = (CardLayout)(this.panelHolder.getLayout());
        cl.show(this.panelHolder, key);
    }
    
    //changes panel to home
    public void gotoHomeScreen() {
        this.changeScreen("home");
    }

    //changes panel to Q&A screen
    private void gotoQAScreen() {
        if(moveOnTimer.isRunning()){
            moveOnTimer.stop();
        }
        this.changeScreen("qa");
    }

    //changes panel to money table screen
    public void gotoMoneyScreen() {
        this.changeScreen("money");
        moveOnTimer.start();
    }

    //changes panel to pause screen
    public void gotoPauseScreen() {
        this.changeScreen("pause");
    }

    //changes panel to the finale screen
    public void gotoFinaleScreen() {
        this.changeScreen("finale");
    }
    
    public void close(){
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == moveOnTimer){
            this.qaScreen.getNewQuestion();
            this.gotoQAScreen();
        }
    }
}
