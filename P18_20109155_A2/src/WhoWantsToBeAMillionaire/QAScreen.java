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

public class QAScreen extends JPanel {

    //screen controller reference
    private final ScreenController screenGUIContext;

    //help lines
    private Help5050 help5050;
    private HelpAudience helpAudience;

    //size of panels
    private final Dimension panelSize;

    //audience help pop-up panel
    private final AudiencePanel audiencePanel;

    //home button gui components
    private final JPanel homeBtnPanel;
    private final JButton homeBtn;

    //decoration image panel
    private final BackgroundImagePanel backgroundPanel;

    //question and answer objects
    private final QuestionPanel questionPanel;
    private QuestionPool questionPool;
    private Question currentQuestion;

    public QAScreen(ScreenController screenGUIContext) {
        super(new BorderLayout());
        super.setPreferredSize(new Dimension(ScreenController.WIDTH, ScreenController.HEIGHT));

        //assigning controller class reference
        this.screenGUIContext = screenGUIContext;

        //setup audience panel popup
        this.audiencePanel = new AudiencePanel();
        this.audiencePanel.setVisible(false);

        //setup panel size
        this.panelSize = new Dimension(ScreenController.WIDTH, ScreenController.HEIGHT / 2);

        //setup decoration image panel
        this.backgroundPanel = new BackgroundImagePanel();
        this.backgroundPanel.setPreferredSize(this.panelSize);

        //setup home button 
        this.homeBtn = new JButton("Home");
        this.homeBtn.setFont(new Font("Ariel", Font.BOLD, 16));
        this.homeBtn.setPreferredSize(new Dimension(100, 75));
        this.homeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenGUIContext.gotoHomeScreen();
            }
        });
        this.homeBtnPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        this.homeBtnPanel.setPreferredSize(new Dimension(ScreenController.WIDTH / 5, ScreenController.HEIGHT / 2));
        this.homeBtnPanel.setOpaque(false);
        this.homeBtnPanel.add(this.homeBtn);

        //add home button and audience popup to the background
        this.backgroundPanel.add(this.audiencePanel, BorderLayout.EAST);
        this.backgroundPanel.add(this.homeBtnPanel);

        //setup up question panel
        this.questionPanel = new QuestionPanel(this, help5050);
        this.questionPanel.setPreferredSize(this.panelSize);

        //setup the question pool
        this.questionPool = new QuestionPool();

        //add panels to the QAScreen panel
        super.add(this.backgroundPanel, BorderLayout.CENTER);
        super.add(this.questionPanel, BorderLayout.SOUTH);
    }

    //updates the question panel with a new question
    public void updateWithNewQuestion() {
        this.currentQuestion = questionPool.getRandomQuestion();
        this.help5050 = new Help5050(false, this.currentQuestion.getCorrectAnswer());
        this.helpAudience = new HelpAudience(false, this.currentQuestion.getCorrectAnswer());
        this.questionPanel.updateQuestion(this.currentQuestion, help5050);

        this.audiencePanel.setVisible(false);
    }

    //handles answer from the QuestionPanel
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

    //use the audience help line
    public void useAudience() {
        this.audiencePanel.setVisible(true);
    }

    //inner audience panel classs
    public class AudiencePanel extends JPanel {

        //setup draw panel
        public AudiencePanel() {
            super.setPreferredSize(new Dimension(ScreenController.WIDTH / 3, ScreenController.HEIGHT / 2));
            super.setBackground(Color.BLACK);
            super.setFont(new Font("Ariel", Font.BOLD, 26));
            super.setForeground(Color.WHITE);
        }

        //custom painting goes here
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            //loop through all options
            for (char character = 'a'; character <= 'd'; character++) {

                //get the string of the option "a"/"b"/"c"/"d"
                String string = String.valueOf(character);

                //convert it to an index it to an integer
                int index = character;
                index -= 97;

                //get the percent for the option
                int percent = helpAudience.percents.get(string);

                //draw the text and the bar on the bar graph
                g.drawString(string + " (" + percent + "%)", 15, (90 * index) + 55);
                g.fillRect(110, (90 * index) + 30, percent * 2, 30);
            }
        }
    }
}
