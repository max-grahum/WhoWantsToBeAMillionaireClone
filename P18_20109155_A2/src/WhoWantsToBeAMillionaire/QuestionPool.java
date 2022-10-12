package WhoWantsToBeAMillionaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//question pool to handle retrieving new questions
public class QuestionPool {

    private final int AMOUNT_OF_QUESTIONS = 20;
    
    //a list of indexs that have already been pulled to stop repeats
    private List<Integer> pulledNumbers;

    public QuestionPool() {
        pulledNumbers = new ArrayList<>();
    }

    //returns a randomly pulled question
    public Question getRandomQuestion() {
        Random rand = new Random();
        int randomNumber = -1;
        
        //redraw numbers if you run out of them
        if(pulledNumbers.size() >= AMOUNT_OF_QUESTIONS-1){
            pulledNumbers.clear();
        }
        
        //loop until you find a number not used yet
        while (pulledNumbers.contains(randomNumber = rand.nextInt(AMOUNT_OF_QUESTIONS-1)+1)) {}
        
        //add the number to the already pulled numbers list
        pulledNumbers.add(randomNumber);
        
        //return the question
        return SaveManager.getInstance().getQuestion(randomNumber);
    }

}
