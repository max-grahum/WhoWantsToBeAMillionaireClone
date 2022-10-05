package WhoWantsToBeAMillionaire;

import java.util.HashMap;

public class Question implements QuestionInterface{
   
    private String questionPromt1, questionPromt2, correctAnswer;
    private final HashMap<String, String> answers;
    
    public Question(String questionPromt1, String questionPromt2, String[] answers, String correctAnswer){
        this.questionPromt1 = questionPromt1;
        this.questionPromt2 = questionPromt2;
        this.correctAnswer = correctAnswer;
        
        //put the answers in a HashMap with their corresponding character keys
        this.answers = new HashMap<>();
        this.answers.put("a", answers[0]);
        this.answers.put("b", answers[1]);
        this.answers.put("c", answers[2]);
        this.answers.put("d", answers[3]);
    }
    
    //returns true if the value passed through equals the correct answer
    @Override
    public boolean isCorrect(String abcd){
        boolean result = false;
        if(abcd.equals(this.correctAnswer)){
            result = true;
        }
        return result;
    } 
    
    //returns the correctAnswer
    @Override
    public String getCorrectAnswer(){
        return this.correctAnswer;
    }
    
    public String getAnswer(String key){
        return this.answers.get(key);
    }
    
    //returns the question formatted
    @Override
    public String toString(){
        String text = "<html>" + this.questionPromt1 + "<br>" + this.questionPromt2 + "<html>";
        return text;
    }
    
    
}
