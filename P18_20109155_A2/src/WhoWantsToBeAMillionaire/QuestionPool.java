package WhoWantsToBeAMillionaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        while (pulledNumbers.contains(randomNumber = rand.nextInt(AMOUNT_OF_QUESTIONS-1)+1)) {
        }
        System.out.println(randomNumber);
        pulledNumbers.add(randomNumber);
        return SaveManager.getInstance().getQuestion(randomNumber);
    }

}
