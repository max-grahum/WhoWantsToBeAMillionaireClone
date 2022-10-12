package WhoWantsToBeAMillionaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MoneyScreen extends JPanel {

    //class references
    private final ScreenController screenGUIContext;
    private final SaveManager saveManager;

    //The money panel gui looks
    private final DrawPanel drawPanel;
    private final CustomMoneyPanel[] moneyPanels;
    private final int[] values;
    private final Color[] colours;

    //the current question number
    private int questionNumber;

    public MoneyScreen(ScreenController screenGUIContext) {
        super(new BorderLayout());

        //class references
        this.screenGUIContext = screenGUIContext;
        this.saveManager = SaveManager.getInstance();

        //get the question number from the database
        this.questionNumber = this.saveManager.getQuestionNumber();

        this.drawPanel = new DrawPanel();

        //values of each money panel
        this.values = new int[]{
            100, 200, 300, 500, 1000, 2000, 4000, 8000, 16000,
            32000, 64000, 125000, 250000, 500000, 1000000
        };

        //the 3 possible colours of panel
        this.colours = new Color[]{Color.cyan, Color.YELLOW, Color.RED};

        //create the money panels and asign colours
        this.moneyPanels = new CustomMoneyPanel[this.values.length];
        for (int i = 0; i < this.moneyPanels.length; i++) {
            Color currentColour;
            if (i < questionNumber) {
                currentColour = this.colours[0];
            } else if (i == questionNumber) {
                currentColour = this.colours[1];
            } else {
                currentColour = this.colours[2];
            }

            this.moneyPanels[i] = new CustomMoneyPanel(this.values[i], currentColour);
        }

        //add the draw panel to the moneyScreen panel
        super.add(this.drawPanel);
    }

    //called when ever you can to this scene to update the money panel colours
    public void updateQuestionNumber() {
        this.questionNumber = this.saveManager.getQuestionNumber();
        for (int i = 0; i < this.moneyPanels.length; i++) {
            Color currentColour;
            if (i < questionNumber) {
                currentColour = this.colours[0];
            } else if (i == questionNumber) {
                currentColour = this.colours[1];
            } else {
                currentColour = this.colours[2];
            }

            this.moneyPanels[i].setColour(currentColour);
        }
    }

    //inner class to handle drawing
    public class DrawPanel extends JPanel {

        //home button
        private JButton homeBtn;

        //setup draw panel
        public DrawPanel() {
            super(new FlowLayout(FlowLayout.LEADING, 0, 0));
            super.setPreferredSize(new Dimension(ScreenController.WIDTH, ScreenController.HEIGHT));
            super.setBackground(Color.BLUE);
            super.setFont(new Font("Ariel", Font.BOLD, 32));

            //home button setup
            this.homeBtn = new JButton("Home");
            this.homeBtn.setFont(new Font("Ariel", Font.BOLD, 16));
            this.homeBtn.setPreferredSize(new Dimension(100, 75));
            this.homeBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    screenGUIContext.cancelTimer();
                    screenGUIContext.gotoHomeScreen();
                }
            });
            super.add(this.homeBtn);
        }

        //custom painting goes here
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            //loop throught the money panels and draw them
            for (int i = 0; i < moneyPanels.length; i++) {
                moneyPanels[moneyPanels.length - 1 - i].drawCustomMoneyPanel(g, 250, 100 + (i * 45));
            }

        }

    }

}
