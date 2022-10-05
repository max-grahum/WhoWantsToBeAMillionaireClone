package WhoWantsToBeAMillionaire;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuestionPanel extends JPanel implements ActionListener {

    private Question question;
    private QAScreen qaContext;

    private JButton[] ansBtns;
    private String[] keys;

    private final JLabel qLbl;
    private final JPanel qPanel;
    private final Dimension buttonSize;

    private boolean answered;

    public QuestionPanel(QAScreen qaContext) {
        super(new FlowLayout(FlowLayout.CENTER, 15, 10));
        super.setBackground(Color.BLUE);

        this.qaContext = qaContext;

        this.answered = false;

        this.keys = new String[]{"a", "b", "c", "d"};

        this.qPanel = new JPanel();
        this.qPanel.setPreferredSize(new Dimension(815, 80));
        this.qPanel.setBackground(Color.LIGHT_GRAY);
        this.qPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 5));

        this.qLbl = new JLabel();
        this.qLbl.setFont(new Font("Ariel", Font.BOLD, 24));

        this.qPanel.add(this.qLbl);

        super.add(this.qPanel);

        this.buttonSize = new Dimension(400, 80);

        this.ansBtns = new JButton[4];
        for (int i = 0; i < this.ansBtns.length; i++) {
            this.ansBtns[i] = new JButton();
            this.ansBtns[i].setPreferredSize(this.buttonSize);
            this.ansBtns[i].setBackground(Color.LIGHT_GRAY);
            this.ansBtns[i].setFont(new Font("Ariel", Font.BOLD, 26));
            this.ansBtns[i].addActionListener(this);
            super.add(this.ansBtns[i]);
        }

    }

    public void updateQuestion(Question question) {
        this.answered = false;
        this.question = question;
        this.qLbl.setText(question.toString());
        for (int i = 0; i < this.ansBtns.length; i++) {
            this.ansBtns[i].setText(this.keys[i] + ": " + question.getAnswer(this.keys[i]));
            this.ansBtns[i].setBackground(Color.LIGHT_GRAY);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //get the events source component;
        Object source = e.getSource();
        if (!answered) {
            this.answered = true;
            boolean result = false;
            

            if (source == this.ansBtns[0]) {
                result = this.question.isCorrect(this.keys[0]);
                if (result) {
                    this.ansBtns[0].setBackground(Color.GREEN);
                } else {
                    this.ansBtns[0].setBackground(Color.RED);
                }

            }
            if (source == this.ansBtns[1]) {
                result = this.question.isCorrect(this.keys[1]);
                if (this.question.isCorrect(this.keys[1])) {
                    this.ansBtns[1].setBackground(Color.GREEN);
                } else {
                    this.ansBtns[1].setBackground(Color.RED);
                }
            }
            if (source == this.ansBtns[2]) {
                result = this.question.isCorrect(this.keys[2]);
                if (this.question.isCorrect(this.keys[2])) {
                    this.ansBtns[2].setBackground(Color.GREEN);
                } else {
                    this.ansBtns[2].setBackground(Color.RED);
                }
            }
            if (source == this.ansBtns[3]) {
                result = this.question.isCorrect(this.keys[3]);
                if (this.question.isCorrect(this.keys[3])) {
                    this.ansBtns[3].setBackground(Color.GREEN);
                } else {
                    this.ansBtns[3].setBackground(Color.RED);
                }
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
