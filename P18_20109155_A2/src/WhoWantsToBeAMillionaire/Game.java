package WhoWantsToBeAMillionaire;

//driver class
public class Game {

    public Game() {

        //create a new save manager and a gui controller
        SaveManager saveManager = SaveManager.getInstance();
        ScreenController gui = new ScreenController();
    }

    //Entry method
    public static void main(String[] args) {
        Game game = new Game();
    }
}
