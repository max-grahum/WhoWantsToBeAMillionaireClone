package WhoWantsToBeAMillionaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionPool {
    
    //array of all possible questions
    private final Question[] questions;
    
    //a list of indexs that have already been pulled to stop repeats
    private List<Integer> pulledNumbers;
    
    public QuestionPool(){
        pulledNumbers = new ArrayList<>();
        
        //create all the custom questions
        questions = new Question[]{
            new Question("In which of these games do players", "use a paddle to hit the ball?",
                    new String[]{"Water Polo", "Ping-Pong", "Shuffleboard", "Foosball"}, 
                    "b"
            ), 
            new Question("Which of these names is most commonly used for ", "a portable, notebook-sized computer?",
                    new String[]{"Laptop", "Turnkey", "Dashboard", "Heavy"}, 
                    "a"
            ),
            new Question("What kind of TV show is a fictionalized", "account of a true story?",
                    new String[]{"Docudrama", "Looney Tunes cartoon", "Infomercial", "Soap opera"},
                    "a"
            ),
            new Question("Someone who is speaking frankly is said ", "to be \"laying it on the\" what?",
                    new String[]{"Ironing board", "Operating table", "Bed", "Line"},
                    "d"
            ),
            new Question("A discouraging event or person is said to ", "\"take the wind out of your\" what?",
                    new String[]{"Balloon", "Weather report", "Sails", "Hair"},
                    "c"
            ),
            new Question("Which of these signs does not ", "appear in musical notation?",
                    new String[]{"Natural", "Flat", "Chomp", "Sharp"},
                    "c"
            ),
            new Question("According to a well-known proverb,", "\"an ounce of prevention\" is worth what?",
                    new String[]{"Ten pounds of Tylenol", "A ton of insurance", "A pound of cure", "Two pounds of coughdrops"},
                    "c"
            ),
            new Question("According to a common piece of advice, ", "\"Don't take any wooden\" what?",
                    new String[]{"Nickels", "Teeth", "Spoons", "Fruit"},
                    "a"
            ),
            new Question("In what city are most episodes of ", "\"The Late Show with David Letterman\" taped?",
                    new String[]{"New York", "Los Angeles", "Chicago", "Indianopolis"},
                    "a"
            ),
            new Question("In the 2000 movie \"Little Nicky,\" who is the ", "father of the character played by Adam Sandler?",
                    new String[]{"Dracula", "Satan", "Freddy Krueger", "Dr. Frankenstein"},
                    "b"
            ),
            new Question("Which of these words is often used ", "to describe a very successful movie?",
                    new String[]{"Smashmouth", "Waterworld", "Blockbuster", "Flamethrower"},
                    "c"
            ),
            new Question("According to a Mother Goose nursery rhyme, ", "\"there was an old woman who lived in a\" what?",
                    new String[]{"Shoe", "High-rise apartment", "Cup", "Lamp"},
                    "a"
            ),
            new Question("Someone who is giving less than a 100% effort ", "is often said to be \"pulling\" what?",
                    new String[]{"His hair out", "Punches", "Strings", "Up stakes"},
                    "b"
            ),
            new Question("Which of these U.S. cities is ", "nicknamed \"Tinseltown\"?",
                    new String[]{"Miami", "New York City", "Los angeles", "Las Vegas"},
                    "c"
            ),
            new Question("In blackjack, what does a player traditionally ", "say when asking for another card?",
                    new String[]{"Hit me", "Yo!", "Check, please", "Bring it on"},
                    "a"
            ),
            new Question("According to a popular phrase, a noteworthy ", "accomplishment is said to be \"nothing to\" what?",
                    new String[]{"Cough on", "Sell on eBay", "Push over", "Sneeze at"},
                    "d"
            ),
            new Question("According to the title of a 1984 Stevie Wonder ", "song, \"I Just Called to Say\" what?",
                    new String[]{"Wasssupp", "I Love You", "It's Over", "Hello"},
                    "b"
            ),
            new Question("According to a popular phrase, something that ", "is easy to accomplish is said to be a \"piece of\" what?",
                    new String[]{"Cake", "Candy", "Fudge", "Pizza"},
                    "a"
            ),
            new Question("People who live in Florida are ", "commonly given what name?",
                    new String[]{"Floridites", "Floriders", "Floridians", "Fluorides"},
                    "c"
            ),
            new Question("If you are much better at something than another person, it is", "often said that they \"can't hold a\" what to you?",
                    new String[]{"Bonfire", "Match", "Candle", "Blowtorch"},
                    "c"
            )
        };
    }

    //returns a randomly pulled question
    public Question getRandomQuestion() {
        Random rand = new Random();
        int randomNumber = -1;
        while(pulledNumbers.contains(randomNumber = rand.nextInt(questions.length))){}
        pulledNumbers.add(randomNumber);
        return questions[randomNumber];
    }
    
}
