package WhoWantsToBeAMillionaire;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Random;

public class HelpAudience extends Help{
   
    public HashMap<String, Integer> percents;
    
     /*
    The idea i went behind this help line is to make "biased random" 
    it will work by first generating the percent for the correct answer from all 100%.
    Then it will generate the other options as percents from what is left after the first generation.
    This should produce a higher number for the correct answer most of the time however you can get unlucky.
    
    edit: I think it works very well for simulating audience.
    */
    public HelpAudience(boolean used, String correctAnswer) {
        super("Ask the audience", used);
        int total = 0;
        int index = (int) (correctAnswer.charAt(0) - 'a');
        Random rand = new Random();
        int randomNumber;
        
        //stores percentages to their String key
        this.percents = new HashMap<>();
        
        //starts by generating a random number for the correct answer
        randomNumber = rand.nextInt(100);
        total += randomNumber;
        percents.put(correctAnswer, randomNumber);
        
        //generates the other 3 option's percentages and prints the outcome
        for(char character = 'a'; character <= 'd'; character++){
            String string = String.valueOf(character);
            if(!percents.containsKey(string)){
                randomNumber = rand.nextInt(100-total);
                total += randomNumber;
                percents.put(string, randomNumber);
            }
        }
    }
   
}
