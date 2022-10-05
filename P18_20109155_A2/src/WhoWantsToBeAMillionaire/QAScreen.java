package WhoWantsToBeAMillionaire;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;
import javax.swing.JPanel;

public class QAScreen extends JPanel {

    private ScreenController screenGUIContext;

    private AudiencePanel audiencePanel;

    private Help5050 help5050;
    private HelpAudience helpAudience;

    private QuestionPanel questionPanel;
    private Question currentQuestion;
    private QuestionPool questionPool;

    public QAScreen(ScreenController screenGUIContext) {
        super(new BorderLayout());
        super.setPreferredSize(new Dimension(ScreenController.WIDTH, ScreenController.HEIGHT));

        this.audiencePanel = new AudiencePanel();
        this.audiencePanel.setVisible(false);
        super.add(this.audiencePanel, BorderLayout.WEST);

        this.screenGUIContext = screenGUIContext;

        this.questionPanel = new QuestionPanel(this, help5050);

        this.questionPool = new QuestionPool();

        this.questionPanel.setPreferredSize(new Dimension(ScreenController.WIDTH, ScreenController.HEIGHT / 2));
        super.add(this.questionPanel, BorderLayout.SOUTH);
    }

    public void getNewQuestion() {
        this.currentQuestion = questionPool.getRandomQuestion();
        this.questionPanel.updateQuestion(this.currentQuestion);
        this.help5050 = new Help5050(false, this.currentQuestion.getCorrectAnswer());
        this.helpAudience = new HelpAudience(false, this.currentQuestion.getCorrectAnswer());
    }

    public void HandleAnswer(boolean result) {
        if (result) {
            System.out.println("Correct!");
            this.screenGUIContext.gotoMoneyScreen();
        } else {
            System.out.println("Wrong!");
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
            super.setFont(new Font("Ariel", Font.BOLD, 32));
        }

        //custom painting goes here
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            helpAudience.drawGraph(g);
        }
    }
}
