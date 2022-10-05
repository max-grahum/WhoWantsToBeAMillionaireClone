package WhoWantsToBeAMillionaire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Game {

    
    //variable to break the running loop via request
    private boolean running;

    //file data manipulation object
    private SavedGame save;

    //pool of questions to grab from
    private QuestionPool questionPool;

    //the current question number
    private static int questionNumber;

    //A list of each question's value 
    private List<Integer> questionsWorth;

    //the current question  
    private Question currentQuestion;

    private Scanner sc;

    //hashmap of all the helplines
    public static HashMap<String, Help> helpLines;

    public Game() {
        
        ScreenController gui = new ScreenController();
        
        //init();
    }

    //initialise game
    public void init() {
        this.questionPool = new QuestionPool();

        //gets saved data from possible previous game
        save = new SavedGame();
        Help5050 help5050 = new Help5050(save.loadUsed5050());
        HelpAudience helpAudience = new HelpAudience(save.loadUsedAsk());
        this.questionNumber = save.loadQuestionNumber();

        //setup helpline hashmap
        this.helpLines = new HashMap<>();
        this.helpLines.put("50/50", help5050);
        this.helpLines.put("ask the audience", helpAudience);

        this.sc = new Scanner(System.in);

        //manually add the questions worth
        this.questionsWorth = new ArrayList<>();
        this.questionsWorth.add(100);
        this.questionsWorth.add(200);
        this.questionsWorth.add(300);
        this.questionsWorth.add(500);
        this.questionsWorth.add(1000);
        this.questionsWorth.add(2000);
        this.questionsWorth.add(4000);
        this.questionsWorth.add(8000);
        this.questionsWorth.add(16000);
        this.questionsWorth.add(32000);
        this.questionsWorth.add(64000);
        this.questionsWorth.add(125000);
        this.questionsWorth.add(250000);
        this.questionsWorth.add(500000);
        this.questionsWorth.add(1000000);
    }

    //play the game
    public void start() {
        this.running = true;

        //check if the user has not restarted and if so plays the intro
        if (questionNumber <= -2) {
            playIntro();
            ++questionNumber;
            save.save(questionNumber, this.helpLines.get("50/50").isUsed(), this.helpLines.get("ask the audience").isUsed());
        }

        //main game loop
        while (running) {
            questionNumber++;
            save.save(questionNumber, this.helpLines.get("50/50").isUsed(), this.helpLines.get("ask the audience").isUsed());

            //if we have completed the million dollar question play the win sequence
            if (questionNumber >= this.questionsWorth.size()) {
                playWin();
                this.requestStop();
                break;
            }

            //grab a new unique question from the pool
            this.currentQuestion = this.questionPool.getRandomQuestion();

            //print the game graphics
            printGame();

            //get and handle the users input
            String input = getUserInput();
            handleUserInput(input);
        }
    }

    //intro sequence
    public void playIntro() {
        try {
            System.out.println(ConsoleColour.BLUE + "Hello! and Welcome to WHO WANTS TO BE A MILLIONAIRE!" + ConsoleColour.RESET);
            Thread.sleep(3000);
            System.out.println("\nThe aim of the game is simple...");
            Thread.sleep(2000);
            System.out.println("Answer the questions as they come to win " + ConsoleColour.BLUE + "A MILLION DOLLARS!" + ConsoleColour.RESET);
            Thread.sleep(3000);
            System.out.println("\nYou have two life-lines you can use on any question:");
            Thread.sleep(3500);
            System.out.println("\nThe 1st life-line is the " + ConsoleColour.BLUE + "\"50/50\":" + ConsoleColour.RESET);
            Thread.sleep(2500);
            System.out.println("This will give you 2 possible answers, giving you a 50% chance.");
            Thread.sleep(3500);
            System.out.println("\nThe 2nd life-line is the " + ConsoleColour.BLUE + "\"ask the audience\":" + ConsoleColour.RESET);
            Thread.sleep(3500);
            System.out.println("This will poll the audience, and give you what percent of the audience choose each of the 4 answers.");
            Thread.sleep(4500);
            System.out.println("\nIf you would like to take a break you can close the game by answering " + ConsoleColour.BLUE + "\"x\"" + ConsoleColour.RESET + " to any question.");
            Thread.sleep(4500);
            System.out.println("Don't worry, your progress is saved you can return at any time.\n");
            Thread.sleep(4500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //win sequence
    public void playWin() {
        try {
            System.out.println("\n" + ConsoleColour.BLUE + "CONGRATULATIONS YOU JUST WON A MILLION " + ConsoleColour.RESET + "\"not real\"" + ConsoleColour.BLUE + " DOLLARS!!!\n" + ConsoleColour.RESET);
            Thread.sleep(2500);

            questionNumber = -1;
            helpLines.get("50/50").setUsed(false);
            helpLines.get("ask the audience").setUsed(false);
            save.save(questionNumber, this.helpLines.get("50/50").isUsed(), this.helpLines.get("ask the audience").isUsed());

            //checks if the player would like to play again
            boolean result = this.getRetryInput();
            if (result) {
                this.init();
                this.start();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //prints the games graphics
    public void printGame() {

        //print money board
        System.out.println(" ▄▄▄▄▄▄▄▄▄▄");
        for (int i = this.questionsWorth.size() - 1; i >= 0; i--) {
            System.out.print("▐ ");
            if (i < this.questionNumber) {
                System.out.print(ConsoleColour.BLUE);
            } else if (i == this.questionNumber) {
                System.out.print(ConsoleColour.YELLOW);
            } else {
                System.out.print(ConsoleColour.RED);
            }
            System.out.printf("%20s", i + 1 + " - $" + this.questionsWorth.get(i) + ConsoleColour.RESET + " ▌\n");
        }
        System.out.println(" ▀▀▀▀▀▀▀▀▀▀");

        //print Q&As
        System.out.println(this.currentQuestion);

    }

    //gets a valid user input
    public String getUserInput() {
        String responce = null;
        boolean isValid = false;

        //loops until the user enters a valid input
        while (!isValid) {

            //promt the user
            String promt = "(" + ConsoleColour.BLUE + "x" + ConsoleColour.RESET + ", " + ConsoleColour.BLUE + "a" + ConsoleColour.RESET + ", " + ConsoleColour.BLUE + "b" + ConsoleColour.RESET + ", " + ConsoleColour.BLUE + "c" + ConsoleColour.RESET + ", " + ConsoleColour.BLUE + "d" + ConsoleColour.RESET + ", ";
            if (!this.helpLines.get("50/50").isUsed()) {
                promt += ConsoleColour.BLUE + "50/50, " + ConsoleColour.RESET;
            }
            if (!this.helpLines.get("ask the audience").isUsed()) {
                promt += ConsoleColour.BLUE + "ask the audience" + ConsoleColour.RESET;
            }
            System.out.print(promt + ") >> ");

            //get a new responce
            responce = sc.nextLine().toLowerCase().trim();

            //check if valid
            if (responce.equals("a") || responce.equals("b") || responce.equals("c") || responce.equals("d")
                    || responce.equals("x")) {
                isValid = true;
            }

            //respond to unique help line calls
            if (responce.equals("50/50") || responce.equals("50")) {
                System.out.println();
                this.helpLines.get("50/50").use(this.currentQuestion.getCorrectAnswer(), this.questionNumber);
                save.save(questionNumber, this.helpLines.get("50/50").isUsed(), this.helpLines.get("ask the audience").isUsed());
            }
            if (responce.equals("ask the audience") || responce.equals("ask")) {
                System.out.println();
                this.helpLines.get("ask the audience").use(this.currentQuestion.getCorrectAnswer(), this.questionNumber);
                save.save(questionNumber, this.helpLines.get("50/50").isUsed(), this.helpLines.get("ask the audience").isUsed());
            }
        }
        return responce;
    }

    //handle a valid answer
    public void handleUserInput(String input) {
        switch (input) {

            //exit the game
            case "x":
                this.requestStop();
                break;

            //an answer
            default:

                //checks if correct
                if (this.currentQuestion.isCorrect(input)) {
                    System.out.println(ConsoleColour.GREEN + "CORRECT!" + ConsoleColour.RESET);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println(ConsoleColour.RED + "INCORRECT...\n\n" + ConsoleColour.RESET);

                    //clears progress
                    this.questionPool = new QuestionPool();
                    this.questionNumber = -1;
                    this.helpLines.get("50/50").setUsed(false);
                    this.helpLines.get("ask the audience").setUsed(false);

                    save.save(questionNumber, false, false);

                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //ask if the player would like to play again
                    if (!getRetryInput()) {
                        this.requestStop();
                    }

                }
                break;
        }
    }

    //asks if the player would like to retry the game
    public boolean getRetryInput() {

        String responce = null;
        boolean isValid = false;
        while (!isValid) {
            System.out.print("Game Over! \nRetry? (y/n) >> ");
            responce = sc.nextLine().toLowerCase().trim();
            if (responce.equals("y") || responce.equals("n")) {
                isValid = true;
            }
        }
        boolean result = true;
        if (!responce.equals("y")) {
            result = false;
        }

        return result;
    }

    //request the program to stop
    public void requestStop() {
        this.sc.close();
        this.running = false;
    }

    //Entry method
    public static void main(String[] args) {
        Game game = new Game();
        
    }
}
