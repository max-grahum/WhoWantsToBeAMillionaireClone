package WhoWantsToBeAMillionaire;

import java.util.HashMap;
import java.util.Random;

public class Help5050 extends Help {

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
        while ((randomNumber = rand.nextInt(3)) == index) {
        }

        int other = (randomNumber + 'a');

        for (char character = 'a'; character <= 'd'; character++) {
            Boolean anOption = (character == other || character == index);
            System.out.println(anOption);
            this.answers.put(Character.toString(character), anOption);
        }
    }

}
