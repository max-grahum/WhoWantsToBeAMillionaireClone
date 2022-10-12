package WhoWantsToBeAMillionaire;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//gui component for the panel with the questions and answers
public class QuestionPanel extends JPanel implements ActionListener {

    //class references
    private final SaveManager saveManager;
    private final QAScreen qaContext;

    //button sizes for consistency
    private final Dimension buttonSize;

    //question information object
    private Question question;

    //answer buttons
    private HashMap<String, JButton> ansBtns;

    //help lines
    private final JButton h5050Btn, hAudienceBtn;
    private Help5050 help5050;

    //question gui
    private final JLabel qLbl;
    private final JPanel qPanel;

    //panel to hold buttons
    private final Font buttonFont;

    //answered flag
    private boolean answered;

    public QuestionPanel(QAScreen qaContext, Help5050 help5050) {
        super(new FlowLayout(FlowLayout.CENTER, 15, 10));
        super.setBackground(Color.BLUE);

        //Class reference context
        this.qaContext = qaContext;
        this.saveManager = SaveManager.getInstance();

        //initialise the answered flag
        this.answered = false;

        this.help5050 = help5050;

        //set button gui attributes
        this.buttonSize = new Dimension(400, 80);
        this.buttonFont = new Font("Ariel", Font.BOLD, 26);

        //setup help line buttons
        this.h5050Btn = new JButton("50 / 50");
        this.h5050Btn.setPreferredSize(this.buttonSize);
        this.h5050Btn.setFont(buttonFont);
        this.h5050Btn.addActionListener(this);
        this.hAudienceBtn = new JButton("Ask The Audience");
        this.hAudienceBtn.setPreferredSize(this.buttonSize);
        this.hAudienceBtn.setFont(buttonFont);
        this.hAudienceBtn.addActionListener(this);

        //setup the question lable holding panel
        this.qPanel = new JPanel();
        this.qPanel.setPreferredSize(new Dimension(900, 80));
        this.qPanel.setBackground(Color.LIGHT_GRAY);
        this.qPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 5));

        //setup the qPanel
        this.qLbl = new JLabel();
        this.qLbl.setFont(new Font("Ariel", Font.BOLD, 24));

        //add the question label to the question panel
        this.qPanel.add(this.qLbl);

        //add help lines and question panel to screen
        super.add(this.h5050Btn);
        super.add(this.hAudienceBtn);
        super.add(this.qPanel);

        //setup answer buttons and add them to the screen
        this.ansBtns = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            JButton ansBtn = new JButton();
            ansBtn.setPreferredSize(this.buttonSize);
            ansBtn.setBackground(Color.LIGHT_GRAY);
            ansBtn.setFont(buttonFont);
            ansBtn.addActionListener(this);
            super.add(ansBtn);

            this.ansBtns.put(Character.toString((char) (97 + i)), ansBtn);
        }

    }

    //called to update the current question held in the QuestionPanel
    public void updateQuestion(Question question, Help5050 help5050) {

        //reset answered flag
        this.answered = false;

        //assign new help5050 line
        this.help5050 = help5050;

        //assign new question
        this.question = question;
        this.qLbl.setText(question.toString());

        //enable the help line buttons
        this.h5050Btn.setEnabled(!this.saveManager.get5050());
        this.hAudienceBtn.setEnabled(!this.saveManager.getAudience());

        //set the answer button texts
        this.ansBtns.get("a").setText("a: " + question.getAnswer("a"));
        this.ansBtns.get("b").setText("b: " + question.getAnswer("b"));
        this.ansBtns.get("c").setText("c: " + question.getAnswer("c"));
        this.ansBtns.get("d").setText("d: " + question.getAnswer("d"));

        //setup buttons
        for (int i = 0; i < this.ansBtns.size(); i++) {
            JButton button = this.ansBtns.get(Character.toString((char) (97 + i)));
            button.setBackground(Color.LIGHT_GRAY);
            button.setEnabled(true);
            button.update(button.getGraphics());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //get the events source component;
        Object source = e.getSource();

        // only if NOT answered
        if (!answered) {

            //result (correct/not correct)
            boolean correct = false;

            //get the button pressed
            if (source == this.ansBtns.get("a")) {
                this.answered = true;
                correct = this.question.isCorrect("a");
                this.ansBtns.get("a").setBackground(Color.RED);
                this.ansBtns.get(this.question.getCorrectAnswer()).setBackground(Color.GREEN);
            }
            if (source == this.ansBtns.get("b")) {
                this.answered = true;
                correct = this.question.isCorrect("b");
                this.ansBtns.get("b").setBackground(Color.RED);
                this.ansBtns.get(this.question.getCorrectAnswer()).setBackground(Color.GREEN);
            }
            if (source == this.ansBtns.get("c")) {
                this.answered = true;
                correct = this.question.isCorrect("c");
                this.ansBtns.get("c").setBackground(Color.RED);
                this.ansBtns.get(this.question.getCorrectAnswer()).setBackground(Color.GREEN);
            }
            if (source == this.ansBtns.get("d")) {
                this.answered = true;
                correct = this.question.isCorrect("d");
                this.ansBtns.get("d").setBackground(Color.RED);
                this.ansBtns.get(this.question.getCorrectAnswer()).setBackground(Color.GREEN);
            }

            //if it has been answered
            if (answered) {

                //update the buttons
                for (int i = 0; i < this.ansBtns.size(); i++) {
                    JButton button = this.ansBtns.get(Character.toString((char) (97 + i)));
                    button.update(button.getGraphics());
                }

                //wait a second to show colours
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
                }

                //handle answer
                if (correct) {
                    this.saveManager.saveData(this.saveManager.getQuestionNumber() + 1, this.saveManager.get5050(), this.saveManager.getAudience());
                } else {
                    this.saveManager.clearData();
                }
                this.qaContext.HandleAnswer(correct);

            }
        }

        //if 5050 helpline was pressed
        if (source == this.h5050Btn) {

            //if the 5050 help has not been used
            if (!this.saveManager.get5050()) {
                
                //disable the button
                this.h5050Btn.setEnabled(false);
                
                //use the helpline
                for (char character = 'a'; character <= 'd'; character++) {
                    String string = Character.toString(character);
                    JButton button = this.ansBtns.get(string);
                    boolean enable = this.help5050.answers.get(string);
                    button.setEnabled(enable);
                    button.update(button.getGraphics());
                }
                this.saveManager.saveData(this.saveManager.getQuestionNumber(), true, this.saveManager.getAudience());
            }

        }

        //if audience helpline was pressed 
        if (source == this.hAudienceBtn) {
            
            //if the audience help has not been used
            if (!this.saveManager.getAudience()) {
                
                //disable the button
                this.hAudienceBtn.setEnabled(false);
                
                //use the helpline
                this.qaContext.useAudience();
                this.saveManager.saveData(this.saveManager.getQuestionNumber(), this.saveManager.get5050(), true);
            }
        }

    }
}
