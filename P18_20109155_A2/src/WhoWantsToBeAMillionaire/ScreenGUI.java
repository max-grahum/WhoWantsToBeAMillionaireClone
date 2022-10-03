package WhoWantsToBeAMillionaire;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ScreenGUI extends JFrame {

    private final String title = "Who Wants To Be A Millionaire!";

    private GUIController controller;
    private HomeScreen homeScreen;

    public ScreenGUI() {
       

        //home screen
        homeScreen = new HomeScreen();
 
        controller = new GUIController(homeScreen);
        
        //GUI Setup
        JFrame frame = new JFrame(title);
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
