package WhoWantsToBeAMillionaire;

import java.util.Random;

public class Help5050 extends Help{

    public Help5050(boolean used) {
        super("50 / 50", used);
    }

    //this help line randomly picks one other option to make a 50/50 with
    @Override
    public void use(String correctAnswer, int questionNum) {
        
        //return if already used
        if(this.used == true){
            return;
        }
        //toggle the used flag
        this.used = true;
         
        //gets the correct answer's index
        int index = (int) (correctAnswer.charAt(0) - 'a');
        
        //generates the random other option
        Random rand = new Random();
        int randomNumber;
        while((randomNumber = rand.nextInt(3)) == index){}
        
        //converts the other number to its char value
        char other = (char) (randomNumber + 'a');
        
        //prints the outcome
        System.out.print(Colour.BLUE);
        if(index < randomNumber){
            System.out.println("It's either " + String.valueOf(correctAnswer).toUpperCase() + " or " + String.valueOf(other).toUpperCase());
        }else{
            System.out.println("It's either " + String.valueOf(other).toUpperCase() + " or " + String.valueOf(correctAnswer).toUpperCase());
        }
        System.out.println(Colour.RESET);
        
    }
    
}
