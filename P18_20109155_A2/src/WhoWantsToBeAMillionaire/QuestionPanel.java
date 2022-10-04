package WhoWantsToBeAMillionaire;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuestionPanel extends JPanel implements ActionListener {

    private JButton[] ansBtns;
    private JLabel qLbl;
    private JPanel qPanel;
    private Dimension buttonSize;

    public QuestionPanel() {
        super(new FlowLayout(FlowLayout.CENTER, 15, 10));
        super.setBackground(Color.BLUE);

        this.qPanel = new JPanel();
        this.qPanel.setPreferredSize(new Dimension(815, 80));
        this.qPanel.setBackground(Color.LIGHT_GRAY);
        this.qPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        this.qLbl = new JLabel("Question: ");
        this.qLbl.setFont(new Font("Ariel", Font.BOLD, 24));
        
        this.qPanel.add(this.qLbl);
        
        super.add(this.qPanel);
        
        this.buttonSize = new Dimension(400, 80);

        this.ansBtns = new JButton[4];
        for (int i = 0; i < this.ansBtns.length; i++) {
            this.ansBtns[i] = new JButton();
            this.ansBtns[i].setPreferredSize(this.buttonSize);
            this.ansBtns[i].setFont(new Font("Ariel", Font.BOLD, 26));
            super.add(this.ansBtns[i]);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //get the events source component;
        Object source = e.getSource();

    }
}
