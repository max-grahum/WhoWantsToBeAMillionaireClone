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

    private HashMap<String, Help> helpLines;
    private final JButton h5050Btn, hAudienceBtn;
    
    private final JLabel qLbl;
    private final JPanel qPanel;
    private final Dimension buttonSize;
    
    private boolean answered;

    public QuestionPanel(QAScreen qaContext) {
        super(new FlowLayout(FlowLayout.CENTER, 15, 10));
        super.setBackground(Color.BLUE);

        this.qaContext = qaContext;

        this.answered = false;
        
        this.buttonSize = new Dimension(400, 80);
        
        this.helpLines = new HashMap<>();
        this.helpLines.put("5050", new Help5050(false));
        this.helpLines.put("audience", new HelpAudience(false));

        this.h5050Btn = new JButton("50 / 50");
        this.h5050Btn.setPreferredSize(this.buttonSize);
        this.hAudienceBtn = new JButton("Ask The Audience");
        this.hAudienceBtn.setPreferredSize(this.buttonSize);
        
        this.qPanel = new JPanel();
        this.qPanel.setPreferredSize(new Dimension(900, 80));
        this.qPanel.setBackground(Color.LIGHT_GRAY);
        this.qPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 5));

        this.qLbl = new JLabel();
        this.qLbl.setFont(new Font("Ariel", Font.BOLD, 24));

        this.qPanel.add(this.qLbl);

        super.add(this.qPanel);

        this.ansBtns = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            JButton ansBtn = new JButton();
            ansBtn.setPreferredSize(this.buttonSize);
            ansBtn.setBackground(Color.LIGHT_GRAY);
            ansBtn.setFont(new Font("Ariel", Font.BOLD, 26));
            ansBtn.addActionListener(this);
            super.add(ansBtn);

            this.ansBtns.put(Character.toString((char) (97 + i)), ansBtn);
        }

    }

    public void updateQuestion(Question question) {
        this.answered = false;
        this.question = question;
        this.qLbl.setText(question.toString());

        this.ansBtns.get("a").setText(question.getAnswer("a"));
        this.ansBtns.get("b").setText(question.getAnswer("b"));
        this.ansBtns.get("c").setText(question.getAnswer("c"));
        this.ansBtns.get("d").setText(question.getAnswer("d"));

        for (int i = 0; i < this.ansBtns.size(); i++) {
            JButton button = this.ansBtns.get(Character.toString((char) (97 + i)));
            button.setBackground(Color.LIGHT_GRAY);
            button.update(button.getGraphics());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //get the events source component;
        Object source = e.getSource();
        if (!answered) {
            this.answered = true;
            boolean result = false;

            if (source == this.ansBtns.get("a")) {
                result = this.question.isCorrect("a");
                this.ansBtns.get("a").setBackground(Color.RED);
            }
            if (source == this.ansBtns.get("b")) {
                result = this.question.isCorrect("b");
                this.ansBtns.get("b").setBackground(Color.RED);
            }
            if (source == this.ansBtns.get("c")) {
                result = this.question.isCorrect("c");
                this.ansBtns.get("c").setBackground(Color.RED);
            }
            if (source == this.ansBtns.get("d")) {
                result = this.question.isCorrect("d");
                this.ansBtns.get("d").setBackground(Color.RED);
            }

            this.ansBtns.get(this.question.getCorrectAnswer()).setBackground(Color.GREEN);
            for (int i = 0; i < this.ansBtns.size(); i++) {
                JButton button = this.ansBtns.get(Character.toString((char) (97 + i)));
                button.update(button.getGraphics());
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(QuestionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.qaContext.HandleAnswer(result);
        }

    }
}
