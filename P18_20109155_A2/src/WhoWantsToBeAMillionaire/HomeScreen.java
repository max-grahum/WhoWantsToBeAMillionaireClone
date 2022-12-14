package WhoWantsToBeAMillionaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class HomeScreen extends JPanel implements ActionListener {

    //other class references 
    private final ScreenController screenGUIContext;
    private final SaveManager saveManager;

    //gui dimensions
    private final Dimension buttonSize, panelSize;

    //gui components
    private final JPanel buttonPanel;
    private final BackgroundImagePanel backgroundPanel;
    private final JButton newGameBtn, continueBtn, quitBtn;

    public HomeScreen(ScreenController screenGUIContext) {
        super(new BorderLayout());
        super.setPreferredSize(new Dimension(ScreenController.WIDTH, ScreenController.HEIGHT));

        //get other class references
        this.screenGUIContext = screenGUIContext;
        this.saveManager = SaveManager.getInstance();

        //instantiate size variables
        this.panelSize = new Dimension(ScreenController.WIDTH, ScreenController.HEIGHT / 2);
        this.buttonSize = new Dimension(500, 80);

        //button panel setup
        this.buttonPanel = new JPanel();
        this.buttonPanel.setPreferredSize(panelSize);
        this.buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 10));
        this.buttonPanel.setBackground(Color.BLUE);

        //new game button setup
        this.newGameBtn = new JButton();
        this.newGameBtn.setText("New Game");
        this.newGameBtn.addActionListener(this);
        this.newGameBtn.setPreferredSize(buttonSize);
        this.newGameBtn.setFont(new Font("Ariel", Font.BOLD, 32));

        //continue button setup
        this.continueBtn = new JButton();
        this.continueBtn.setText("Continue");
        this.continueBtn.addActionListener(this);
        this.continueBtn.setPreferredSize(buttonSize);
        this.continueBtn.setFont(new Font("Ariel", Font.BOLD, 32));
        if (this.saveManager.getQuestionNumber() <= 0) {
            this.continueBtn.setEnabled(false);
        }

        //quit button setup
        this.quitBtn = new JButton();
        this.quitBtn.setText("Quit");
        this.quitBtn.addActionListener(this);
        this.quitBtn.setPreferredSize(buttonSize);
        this.quitBtn.setFont(new Font("Ariel", Font.BOLD, 32));

        //adding buttons to button panel
        this.buttonPanel.add(newGameBtn);
        this.buttonPanel.add(continueBtn);
        this.buttonPanel.add(quitBtn);

        //decoration panel for image
        this.backgroundPanel = new BackgroundImagePanel();

        //add panels to homeScreen panel
        super.add(this.backgroundPanel, BorderLayout.NORTH);
        super.add(this.buttonPanel, BorderLayout.SOUTH);
    }

    //set the continue button as enabled/disabled if the save exists or not
    public void updateContinue() {
        if (this.saveManager.getQuestionNumber() <= 0) {
            this.continueBtn.setEnabled(false);
        } else {
            this.continueBtn.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //get the events source component;
        Object source = e.getSource();

        //handles the different button press events
        if (source == newGameBtn) {
            System.out.println("New Game!");
            this.saveManager.clearData();
            this.screenGUIContext.gotoMoneyScreen();
        }
        if (source == continueBtn) {
            System.out.println("Continue!");
            this.screenGUIContext.gotoMoneyScreen();
        }
        if (source == quitBtn) {
            System.out.println("Quit!");
            this.screenGUIContext.close();
        }
    }

}
