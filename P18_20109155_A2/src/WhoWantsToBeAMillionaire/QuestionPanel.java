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

public class QuestionPanel extends JPanel implements ActionListener {

    private Question question;
    private QAScreen qaContext;

    private HashMap<String, JButton> ansBtns;

    private final JButton h5050Btn, hAudienceBtn;
    private Help5050 help5050;

    private SaveManager saveManager;

    private final JLabel qLbl;
    private final JPanel qPanel;
    private final Dimension buttonSize;

    private Font buttonFont;

    private boolean answered;

    public QuestionPanel(QAScreen qaContext, Help5050 help5050) {
        super(new FlowLayout(FlowLayout.CENTER, 15, 10));
        super.setBackground(Color.BLUE);

        this.qaContext = qaContext;

        this.saveManager = SaveManager.getInstance();

        this.answered = false;

        this.help5050 = help5050;

        this.buttonSize = new Dimension(400, 80);
        this.buttonFont = new Font("Ariel", Font.BOLD, 26);

        this.h5050Btn = new JButton("50 / 50");
        this.h5050Btn.setPreferredSize(this.buttonSize);
        this.h5050Btn.setFont(buttonFont);
        this.h5050Btn.addActionListener(this);
        this.hAudienceBtn = new JButton("Ask The Audience");
        this.hAudienceBtn.setPreferredSize(this.buttonSize);
        this.hAudienceBtn.setFont(buttonFont);
        this.hAudienceBtn.addActionListener(this);

        this.qPanel = new JPanel();
        this.qPanel.setPreferredSize(new Dimension(900, 80));
        this.qPanel.setBackground(Color.LIGHT_GRAY);
        this.qPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 5));

        this.qLbl = new JLabel();
        this.qLbl.setFont(new Font("Ariel", Font.BOLD, 24));

        super.add(this.h5050Btn);
        super.add(this.hAudienceBtn);
        this.qPanel.add(this.qLbl);

        super.add(this.qPanel);

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

    public void updateQuestion(Question question, Help5050 help5050) {
        this.answered = false;
        this.question = question;
        this.qLbl.setText(question.toString());

        this.help5050 = help5050;

        if (this.saveManager.get5050()) {
            this.h5050Btn.setEnabled(false);
        }
        if (this.saveManager.getAudience()) {
            this.hAudienceBtn.setEnabled(false);
        }
        this.ansBtns.get("a").setText("a:" + question.getAnswer("a"));
        this.ansBtns.get("b").setText("b:" + question.getAnswer("b"));
        this.ansBtns.get("c").setText("c:" + question.getAnswer("c"));
        this.ansBtns.get("d").setText("d:" + question.getAnswer("d"));

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

        if (!answered) {
            boolean result = false;

            if (source == this.ansBtns.get("a")) {
                this.answered = true;
                result = this.question.isCorrect("a");
                this.ansBtns.get("a").setBackground(Color.RED);
                this.ansBtns.get(this.question.getCorrectAnswer()).setBackground(Color.GREEN);
            }
            if (source == this.ansBtns.get("b")) {
                this.answered = true;
                result = this.question.isCorrect("b");
                this.ansBtns.get("b").setBackground(Color.RED);
                this.ansBtns.get(this.question.getCorrectAnswer()).setBackground(Color.GREEN);
            }
            if (source == this.ansBtns.get("c")) {
                this.answered = true;
                result = this.question.isCorrect("c");
                this.ansBtns.get("c").setBackground(Color.RED);
                this.ansBtns.get(this.question.getCorrectAnswer()).setBackground(Color.GREEN);
            }
            if (source == this.ansBtns.get("d")) {
                this.answered = true;
                result = this.question.isCorrect("d");
                this.ansBtns.get("d").setBackground(Color.RED);
                this.ansBtns.get(this.question.getCorrectAnswer()).setBackground(Color.GREEN);
            }

            if (answered) {
                for (int i = 0; i < this.ansBtns.size(); i++) {
                    JButton button = this.ansBtns.get(Character.toString((char) (97 + i)));
                    button.update(button.getGraphics());
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (result) {
                    this.saveManager.saveData(this.saveManager.getQuestionNumber() + 1, this.saveManager.get5050(), this.saveManager.getAudience());
                } else {
                    this.saveManager.clearData();
                }
                this.qaContext.HandleAnswer(result);

            }
        }

        if (source == this.h5050Btn) {
            if (!this.saveManager.get5050()) {
                this.h5050Btn.setEnabled(false);
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
        if (source == this.hAudienceBtn) {
            if (!this.saveManager.getAudience()) {
                this.hAudienceBtn.setEnabled(false);
                this.qaContext.useAudience();
                this.saveManager.saveData(this.saveManager.getQuestionNumber(), this.saveManager.get5050(), true);
            }
        }

    }
}
