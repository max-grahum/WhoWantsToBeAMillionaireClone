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

    private final DrawPanel drawPanel;

    private ScreenController screenGUIContext;

    private SaveManager saveManager;

    private final CustomMoneyPanel[] moneyPanels;
    private final int[] values;
    private final Color[] colours;

    private int currentIndex;

    public MoneyScreen(ScreenController screenGUIContext) {
        super(new BorderLayout());
        this.drawPanel = new DrawPanel();

        this.saveManager = SaveManager.getInstance();
        this.currentIndex = this.saveManager.getQuestionNumber();

        this.screenGUIContext = screenGUIContext;

        this.values = new int[]{
            100, 200, 300, 500, 1000, 2000, 4000, 8000, 16000,
            32000, 64000, 125000, 250000, 500000, 1000000
        };

        this.colours = new Color[]{Color.cyan, Color.YELLOW, Color.RED};

        this.moneyPanels = new CustomMoneyPanel[this.values.length];
        for (int i = 0; i < this.moneyPanels.length; i++) {
            Color currentColour;
            if (i < currentIndex) {
                currentColour = this.colours[0];
            } else if (i == currentIndex) {
                currentColour = this.colours[1];
            } else {
                currentColour = this.colours[2];
            }

            this.moneyPanels[i] = new CustomMoneyPanel(this.values[i], currentColour);
        }

        super.add(this.drawPanel);
    }

    public void updateQuestionNumber() {
        this.currentIndex = this.saveManager.getQuestionNumber();
        for (int i = 0; i < this.moneyPanels.length; i++) {
            Color currentColour;
            if (i < currentIndex) {
                currentColour = this.colours[0];
            } else if (i == currentIndex) {
                currentColour = this.colours[1];
            } else {
                currentColour = this.colours[2];
            }

            this.moneyPanels[i].setColour(currentColour);
        }
    }

    public class DrawPanel extends JPanel {

        private JButton homeBtn;

        //setup draw panel
        public DrawPanel() {
            super(new FlowLayout(FlowLayout.LEADING));
            super.setPreferredSize(new Dimension(ScreenController.WIDTH, ScreenController.HEIGHT));
            super.setBackground(Color.BLUE);
            super.setFont(new Font("Ariel", Font.BOLD, 32));

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

            for (int i = 0; i < moneyPanels.length; i++) {
                moneyPanels[moneyPanels.length - 1 - i].drawCustomMoneyPanel(g, 250, 100 + (i * 45));
            }

        }

    }

}
