package WhoWantsToBeAMillionaire;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveManager {
    
    private static SaveManager instance;
    
    private DBManager dbManager;
    private Connection conn;
    private Statement statement;
    
    private SaveManager(){
        dbManager = DBManager.getInstance();
        conn = dbManager.getConnection();
        try {
            statement = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            this.statement.addBatch("CREATE  TABLE DATA  (QUESTIONNUM  INT,  HELPFIFTY BOOLEAN,  HELPAUDIENCE BOOLEAN)");
            this.statement.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static SaveManager getInstance(){
        if(instance == null){
            instance = new SaveManager();
        }
        return instance;
    }
    
    public void saveData(int questionNum, boolean help5050, boolean helpAudience){
        System.out.println("(" + questionNum + ", " + help5050 + ", " + helpAudience + ")");
    }
    
}
