package WhoWantsToBeAMillionaire;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

public class QAScreen extends JPanel {

    private ScreenController screenGUIContext;
    
    private QuestionPanel questionPanel;
    private Question currentQuestion;
    private QuestionPool questionPool;

    public QAScreen(ScreenController screenGUIContext) {
        super(new BorderLayout());
        super.setPreferredSize(new Dimension(ScreenController.WIDTH, ScreenController.HEIGHT));

        this.screenGUIContext = screenGUIContext;
        
        this.questionPanel = new QuestionPanel(this);
        
        this.questionPool = new QuestionPool();
        
        this.questionPanel.setPreferredSize(new Dimension(ScreenController.WIDTH, ScreenController.HEIGHT / 2));
        super.add(this.questionPanel, BorderLayout.SOUTH);
    }
    
    public void getNewQuestion() {
        this.questionPanel.updateQuestion(questionPool.getRandomQuestion());
    }

    public void HandleAnswer(boolean result) {
        if(result){
            System.out.println("Correct!");
            this.screenGUIContext.gotoMoneyScreen();
        }else{
            System.out.println("Wrong!");
            this.screenGUIContext.gotoHomeScreen();
        }
    }

}
