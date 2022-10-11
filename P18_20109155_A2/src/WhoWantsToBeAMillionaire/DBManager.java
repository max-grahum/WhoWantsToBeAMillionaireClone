package WhoWantsToBeAMillionaire;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//central database manager class for the program
public class DBManager {

    //contants for embedded database
    private static final String USER_NAME = "username"; //your DB username
    private static final String PASSWORD = "password"; //your DB password
    private static final String URL = "jdbc:derby:MillionaireDB_Ebd; create=true";  //url of the DB host

    Connection conn;

    //uses singleton pattern
    private static DBManager instance;

    private DBManager() {
        establishConnection();
    }

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.conn;
    }

    //Establish connection
    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println(URL + " Get Connected Successfully ....");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
