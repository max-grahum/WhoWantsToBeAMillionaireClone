package WhoWantsToBeAMillionaire;

//quesiton class interface
public interface QuestionInterface {

    public abstract boolean isCorrect(String abcd);

    public abstract String getCorrectAnswer();

    public abstract String getAnswer(String key);

}
