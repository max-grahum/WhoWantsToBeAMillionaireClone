package WhoWantsToBeAMillionaire;

//help class interface
public interface HelpInterface {
     public abstract void use(String correctAnswer, int questionNum);
     public void setUsed(boolean used);
     public boolean isUsed();
}
 