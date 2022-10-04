package WhoWantsToBeAMillionaire;

import java.util.HashMap;
import java.util.Random;

public class HelpAudience extends Help{

    public HelpAudience(boolean used) {
        super("Ask the audience", used);
    }

    /*
    The idea i went behind this help line is to make "biased random" 
    it will work by first generating the percent for the correct answer from all 100%.
    Then it will generate the other options as percents from what is left after the first generation.
    This should produce a higher number for the correct answer most of the time however you can get unlucky.
    
    edit: I think it works very well for simulating audience.
    */
    @Override
    public void use(String correctAnswer, int questionNum) {
         
        //return if already used
        if(this.used == true){
            return;
        }
        //set used flag
        this.used = true;
        
        
        int total = 0;
        int index = (int) (correctAnswer.charAt(0) - 'a');
        Random rand = new Random();
        int randomNumber;
        
        //stores percentages to their String key
        HashMap<String, Integer> percents = new HashMap<>();
        
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
            System.out.println(ConsoleColour.BLUE + string + ": " + percents.get(string) + "%");
        }
        System.out.println(ConsoleColour.RESET);
    }
    
}
