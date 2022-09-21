package WhoWantsToBeAMillionaire;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SavedGame {

    //constant file location
    private final String FILE_LOCATION = "./GameData.txt";
    
    
    //empties the file's contents
    public void clear(){
        try {
            BufferedWriter update = new BufferedWriter(new FileWriter(this.FILE_LOCATION));
            update.append("");
            update.close();
        }catch(IOException e){
            System.out.println("FAILED CLEARING DATA: " + e);
        }
    }
     
    //overwrites the old data to the file
    public void save(int questionNumber, boolean used5050, boolean usedAsk) {

        try {
            BufferedWriter update = new BufferedWriter(new FileWriter(this.FILE_LOCATION));

            update.append(--questionNumber+"\n");
            update.append(used5050+"\n");
            update.append(usedAsk+"\n");

            update.close();

        } catch (IOException e) {
            System.out.println("FAILED SAVING THE GAME: " + e);
        }
    }
    
    //load the first value (the question number)
    public int loadQuestionNumber(){
        int result = -2;
        try {
            BufferedReader update = new BufferedReader(new FileReader(this.FILE_LOCATION));
            result = Integer.parseInt(update.readLine());
        } catch (Exception e) {}
        return result;
    }
    
    //load the second value (the "50/50" used boolean)
    public boolean loadUsed5050(){
        boolean result = false;
        try {
            BufferedReader update = new BufferedReader(new FileReader(this.FILE_LOCATION));
            update.readLine();
            result = Boolean.parseBoolean(update.readLine());
        } catch (Exception e) {}
        return result;
    }
    
    //load the third value (the "ask the audience" used boolean)
    public boolean loadUsedAsk(){
        boolean result = false;
        try {
            BufferedReader update = new BufferedReader(new FileReader(this.FILE_LOCATION));
            update.readLine();
            update.readLine();
            result = Boolean.parseBoolean(update.readLine());
        } catch (Exception e) {}
        return result;
    }
    
    
}
