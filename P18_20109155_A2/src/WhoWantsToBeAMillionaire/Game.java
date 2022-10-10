package WhoWantsToBeAMillionaire;

public class Game {
    
    public static int questionNumber;

    public Game() {
        SaveManager saveManager = SaveManager.getInstance();
        ScreenController gui = new ScreenController();
    }
    
    //Entry method
    public static void main(String[] args) {
        Game game = new Game();
    }
}
