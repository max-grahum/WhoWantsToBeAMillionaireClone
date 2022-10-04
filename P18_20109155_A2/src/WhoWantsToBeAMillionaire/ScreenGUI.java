package WhoWantsToBeAMillionaire;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ScreenGUI extends JFrame {

    private final String TITLE = "Who Wants To Be A Millionaire!";
    public static final int WIDTH = 1000, HEIGHT = 800;

    private final GUIController controller;
    private final HomeScreen homeScreen;
    private final QAScreen qaScreen;
    private final MoneyScreen moneyScreen;
    private final PauseScreen pauseScreen;
    private final FinaleScreen finaleScreen;

    public ScreenGUI() {
       

        //home screen
        homeScreen = new HomeScreen();
        moneyScreen = new MoneyScreen();
        qaScreen = new QAScreen();
        pauseScreen = new PauseScreen();
        finaleScreen = new FinaleScreen();
 
        controller = new GUIController(homeScreen);
//        controller = new GUIController(moneyScreen);
//        controller = new GUIController(qaScreen);
//        controller = new GUIController(pauseScreen);
//        controller = new GUIController(finaleScreen);
        
        //GUI Setup
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().add(controller);
        frame.pack();

    }

    //changes panel to home
    public void gotoHomeScreen() {
        this.controller.changeScreen(this.homeScreen);
    }

    //changes panel to Q&A screen
    public void gotoQAScreen() {
        this.controller.changeScreen(this.homeScreen);
    }

    //changes panel to money table screen
    public void gotoMoneyScreen() {
        this.controller.changeScreen(this.homeScreen);
    }

    //changes panel to pause screen
    public void gotoPauseScreen() {
        this.controller.changeScreen(this.homeScreen);
    }

    //changes panel to the finale screen
    public void gotoFinaleScreen() {
        this.controller.changeScreen(this.homeScreen);
    }
}
