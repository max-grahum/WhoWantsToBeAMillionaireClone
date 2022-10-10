package WhoWantsToBeAMillionaire;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JPanel;

public class QAScreen extends JPanel {

    private ScreenController screenGUIContext;

    private AudiencePanel audiencePanel;

    private Help5050 help5050;
    private HelpAudience helpAudience;

    private JPanel decorPanel, homeBtnPanel;
    private JButton homeBtn;

    private Dimension panelSize;

    private QuestionPanel questionPanel;
    private Question currentQuestion;
    private QuestionPool questionPool;

    public QAScreen(ScreenController screenGUIContext) {
        super.setPreferredSize(new Dimension(ScreenController.WIDTH, ScreenController.HEIGHT));

        this.audiencePanel = new AudiencePanel();
        this.audiencePanel.setVisible(false);

        this.panelSize = new Dimension(ScreenController.WIDTH, ScreenController.HEIGHT / 2);

        this.screenGUIContext = screenGUIContext;

        this.decorPanel = new JPanel(new BorderLayout());
        this.decorPanel.setPreferredSize(this.panelSize);
        this.homeBtn = new JButton("Home");
        this.homeBtn.setFont(new Font("Ariel", Font.BOLD, 16));
        this.homeBtn.setPreferredSize(new Dimension(100, 75));
        this.homeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenGUIContext.gotoHomeScreen();
            }
        });
        this.decorPanel.add(this.audiencePanel, BorderLayout.EAST);
        this.homeBtnPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        this.homeBtnPanel.add(this.homeBtn);
        this.decorPanel.add(this.homeBtnPanel);
        

        this.questionPanel = new QuestionPanel(this, help5050);

        this.questionPool = new QuestionPool();

        this.questionPanel.setPreferredSize(this.panelSize);
        super.add(this.decorPanel, BorderLayout.NORTH);
        super.add(this.questionPanel, BorderLayout.SOUTH);
    }

    public void getNewQuestion() {
        this.currentQuestion = questionPool.getRandomQuestion();
        this.help5050 = new Help5050(false, this.currentQuestion.getCorrectAnswer());
        this.helpAudience = new HelpAudience(false, this.currentQuestion.getCorrectAnswer());
        this.questionPanel.updateQuestion(this.currentQuestion, help5050);

        this.audiencePanel.setVisible(false);
    }

    public void HandleAnswer(boolean result) {
        if (result) {
            System.out.println("Correct!");
            this.screenGUIContext.gotoMoneyScreen();
        } else {
            System.out.println("Wrong!");
            SaveManager.getInstance().clearData();
            this.screenGUIContext.gotoHomeScreen();
        }
    }

    public void useAudience() {
        this.audiencePanel.setVisible(true);
    }

    public class AudiencePanel extends JPanel {

        //setup draw panel
        public AudiencePanel() {
            super.setPreferredSize(new Dimension(ScreenController.WIDTH / 3, ScreenController.HEIGHT / 3));
            super.setBackground(Color.BLACK);
            super.setFont(new Font("Ariel", Font.BOLD, 26));
            super.setForeground(Color.WHITE);
        }

        //custom painting goes here
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (char character = 'a'; character <= 'd'; character++) {
                String string = String.valueOf(character);
                int index = character;
                index -= 97;
                int percent = helpAudience.percents.get(string);

                g.drawString(string + " (" + percent + "%)", 15, (90 * index) + 55);
                g.fillRect(110, (90 * index) + 30, percent * 2, 30);
            }
        }
    }
}
