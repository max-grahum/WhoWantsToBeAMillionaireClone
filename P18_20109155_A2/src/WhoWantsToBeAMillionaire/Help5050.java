package WhoWantsToBeAMillionaire;

import java.util.HashMap;
import java.util.Random;

//helpline that disables 2 of the 4 options
public class Help5050 extends Help {

    //contains true or false for each answer if it is disabled or not
    public HashMap<String, Boolean> answers;

    public Help5050(boolean used, String correctAnswer) {
        super("50 / 50", used);

        //return if already used
        if (this.used == true) {
            return;
        }

        //toggle the used flag
        this.used = true;

        this.answers = new HashMap<>();

        //gets the correct answer's index
        int index = (int) (correctAnswer.charAt(0));

        //generates the random other option
        Random rand = new Random();
        int randomNumber = index;
        while ((randomNumber = ('a' + rand.nextInt(3))) == index) {
        }


        for (char character = 'a'; character <= 'd'; character++) {
            Boolean anOption = (character == randomNumber || character == index);
            this.answers.put(Character.toString(character), anOption);
        }
    }

}
