package WhoWantsToBeAMillionaire;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveManager {

    private static SaveManager instance;

    private DBManager dbManager;
    private Connection conn;
    private Statement statement;

    private SaveManager() {
        dbManager = DBManager.getInstance();
        conn = dbManager.getConnection();
        try {
            statement = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getNextException());
            System.out.println(ex.getMessage());

        }
        try {
            if (!this.checkTableExists("DATA")) {
                this.statement.addBatch("CREATE  TABLE DATA  (ID INT, QUESTIONNUM  INT,  HELPFIFTY BOOLEAN,  HELPAUDIENCE BOOLEAN)");
                this.statement.addBatch("INSERT INTO DATA VALUES(1, 0, false, false)");
            }
            if (!this.checkTableExists("QUESTIONS")) {
                this.statement.addBatch("CREATE  TABLE QUESTIONS (ID INT, PROMT1 VARCHAR(70), PROMT2 VARCHAR(70), ANSWERA VARCHAR(30), ANSWERB VARCHAR(30), ANSWERC VARCHAR(30), ANSWERD VARCHAR(30), CORRECTANSWER VARCHAR(5))");
                this.setUpQuestions();
            }
            this.statement.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static SaveManager getInstance() {
        if (instance == null) {
            instance = new SaveManager();
        }
        return instance;
    }

    //returns true if table exists
    public boolean checkTableExists(String name) {
        boolean result = false;

        try {
            DatabaseMetaData dbmd = this.conn.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = dbmd.getTables(null, null, null, types);

            while (rs.next()) {
                String table_name = rs.getString("TABLE_NAME");
                if (table_name.equalsIgnoreCase(name)) {
                    result = true;
                    break;
                }
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public void setUpQuestions() {
        try {
            this.statement.addBatch("INSERT INTO QUESTIONS VALUES(1,"
                    + "'In which of these games do players',"
                    + "'use a paddle to hit the ball?',"
                    + "'Water Polo', 'Ping-Pong', 'Shuffleboard', 'Foosball',"
                    + "'b')");
            this.statement.addBatch("INSERT INTO QUESTIONS VALUES(2,"
                    + "'Which of these names is most commonly used for ',"
                    + "'a portable, notebook-sized computer?',"
                    + "'Laptop', 'Turnkey', 'Dashboard', 'Heavy',"
                    + "'a')");
            this.statement.addBatch("INSERT INTO QUESTIONS VALUES(3,"
                    + "'What kind of TV show is a fictionalized',"
                    + "'account of a true story?',"
                    + "'Docudrama', 'Looney Tunes cartoon', 'Infomercial', 'Soap opera',"
                    + "'a')");
            this.statement.addBatch("INSERT INTO QUESTIONS VALUES(4,"
                    + "'Someone who is speaking frankly is said',"
                    + "'to be \"laying it on the\" what?',"
                    + "'Ironing board', 'Operating table', 'Bed', 'Line',"
                    + "'d')");
            this.statement.addBatch("INSERT INTO QUESTIONS VALUES(5,"
                    + "'A discouraging event or person is said to',"
                    + "'\"take the wind out of your\" what?',"
                    + "'Balloon', 'Weather report', 'Sails', 'Hair',"
                    + "'c')");
            this.statement.addBatch("INSERT INTO QUESTIONS VALUES(6,"
                    + "'Which of these signs does not',"
                    + "'appear in musical notation?',"
                    + "'Natural', 'Flat', 'Chomp', 'Sharp',"
                    + "'c')");
            this.statement.addBatch("INSERT INTO QUESTIONS VALUES(7,"
                    + "'According to a well-known proverb,',"
                    + "'\"an ounce of prevention\" is worth what?',"
                    + "'Ten pounds of Tylenol', 'A ton of insurance', 'A pound of cure', 'Two pounds of coughdrops',"
                    + "'c')");
            this.statement.addBatch("INSERT INTO QUESTIONS VALUES(8,"
                    + "'According to a common piece of advice,',"
                    + "'\"Dont take any wooden\" what?',"
                    + "'Nickels', 'Teeth', 'Spoons', 'Fruit',"
                    + "'a')");
            this.statement.addBatch("INSERT INTO QUESTIONS VALUES(9,"
                    + "'In what city are most episodes of',"
                    + "'\"The Late Show with David Letterman\" taped?',"
                    + "'New York', 'Los Angeles', 'Chicago', 'Indianopolis',"
                    + "'a')");
            this.statement.addBatch("INSERT INTO QUESTIONS VALUES(10,"
                    + "'In the 2000 movie \"Little Nicky,\" who is the ',"
                    + "'father of the character played by Adam Sandler?',"
                    + "'Dracula', 'Satan', 'Freddy Krueger', 'Dr. Frankenstein',"
                    + "'b')");
            this.statement.addBatch("INSERT INTO QUESTIONS VALUES(11,"
                    + "'Which of these words is often used ',"
                    + "'to describe a very successful movie?',"
                    + "'Smashmouth', 'Waterworld', 'Blockbuster', 'Flamethrower',"
                    + "'c')");
            this.statement.addBatch("INSERT INTO QUESTIONS VALUES(12,"
                    + "'According to a Mother Goose nursery rhyme,',"
                    + "'\"there was an old woman who lived in a\" what?',"
                    + "'Shoe', 'High-rise apartment', 'Cup', 'Lamp',"
                    + "'a')");
            this.statement.addBatch("INSERT INTO QUESTIONS VALUES(13,"
                    + "'Someone who is giving less than a 100% effort',"
                    + "'is often said to be \"pulling\" what?',"
                    + "'His hair out', 'Punches', 'Strings', 'Up stakes',"
                    + "'b')");
            this.statement.addBatch("INSERT INTO QUESTIONS VALUES(14,"
                    + "'Which of these U.S. cities is ',"
                    + "'nicknamed \"Tinseltown\"?',"
                    + "'Miami', 'New York City', 'Los angeles', 'Las Vegas',"
                    + "'c')");
            this.statement.addBatch("INSERT INTO QUESTIONS VALUES(15,"
                    + "'In blackjack, what does a player traditionally',"
                    + "'say when asking for another card?',"
                    + "'Hit me', 'Yo!', 'Check, please', 'Bring it on',"
                    + "'a')");
            this.statement.addBatch("INSERT INTO QUESTIONS VALUES(16,"
                    + "'According to a popular phrase, a noteworthy',"
                    + "'accomplishment is said to be \"nothing to\" what?',"
                    + "'Cough on', 'Sell on eBay', 'Push over', 'Sneeze at',"
                    + "'d')");
            this.statement.addBatch("INSERT INTO QUESTIONS VALUES(17,"
                    + "'According to the title of a 1984 Stevie Wonder',"
                    + "'song, \"I Just Called to Say\" what?',"
                    + "'Wasssupp', 'I Love You', 'Its Over', 'Hello',"
                    + "'b')");
            this.statement.addBatch("INSERT INTO QUESTIONS VALUES(18,"
                    + "'According to a popular phrase, something that',"
                    + "'is easy to accomplish is said to be a \"piece of\" what?',"
                    + "'Cake', 'Candy', 'Fudge', 'Pizza',"
                    + "'a')");
            this.statement.addBatch("INSERT INTO QUESTIONS VALUES(19,"
                    + "'People who live in Florida are',"
                    + "'commonly given what name?',"
                    + "'Floridites', 'Floriders', 'Floridians', 'Fluorides',"
                    + "'c')");
            this.statement.addBatch("INSERT INTO QUESTIONS VALUES(20,"
                    + "'If you are much better at something than another person, it is',"
                    + "'often said that they \"cant hold a\" what to you?',"
                    + "'Bonfire', 'Match', 'Candle', 'Blowtorch',"
                    + "'c')");

            this.statement.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void clearData() {
        try {
            if (this.checkTableExists("DATA")) {
                this.statement.addBatch("Drop table DATA");
            }
            if (this.checkTableExists("QUESTIONS")) {
                this.statement.addBatch("Drop table QUESTIONS");
            }

            this.statement.addBatch("CREATE  TABLE DATA (ID INT, QUESTIONNUM  INT,  HELPFIFTY BOOLEAN,  HELPAUDIENCE BOOLEAN)");
            this.statement.addBatch("INSERT INTO DATA VALUES(1, 0, FALSE, FALSE)");

            this.statement.addBatch("CREATE  TABLE QUESTIONS (ID INT, PROMT1 VARCHAR(70), PROMT2 VARCHAR(70), ANSWERA VARCHAR(30), ANSWERB VARCHAR(30), ANSWERC VARCHAR(30), ANSWERD VARCHAR(30), CORRECTANSWER VARCHAR(5))");
            this.setUpQuestions();

            this.statement.executeBatch();

        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveData(int questionNum, boolean help5050, boolean helpAudience) {
        System.out.println("(" + questionNum + ", " + help5050 + ", " + helpAudience + ")");
        try {
            this.statement.addBatch("UPDATE DATA "
                    + "SET ID = 1, QUESTIONNUM = " + questionNum + ", HELPFIFTY = " + help5050 + ", HELPAUDIENCE = " + helpAudience);
            this.statement.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Question getQuestion(int index) {
        Question question = null;
        try {
            ResultSet rs = this.statement.executeQuery("SELECT * FROM QUESTIONS "
                    + "WHERE ID = " + index);
            while (rs.next()) {
                question = new Question(rs.getString("PROMT1"), rs.getString("PROMT2"),
                        new String[]{rs.getString("ANSWERA"), rs.getString("ANSWERB"), rs.getString("ANSWERC"), rs.getString("ANSWERD")},
                        rs.getString("CORRECTANSWER")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return question;
    }

    private String getData(String columnName) {
        String data = "0";
        try {
            ResultSet rs = this.statement.executeQuery("SELECT * FROM DATA");
            rs.next();
            data = rs.getString(columnName);
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }

    public int getQuestionNumber() {
        return Integer.parseInt(this.getData("QUESTIONNUM"));
    }

    public boolean get5050() {
        return Boolean.parseBoolean(this.getData("HELPFIFTY"));
    }

    public boolean getAudience() {
        return Boolean.parseBoolean(this.getData("HELPAUDIENCE"));
    }

}
