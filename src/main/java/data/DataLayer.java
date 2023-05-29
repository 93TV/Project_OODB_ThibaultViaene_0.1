package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Project_OODB_ThibaultViaene_0.1 : DataLayer
 *
 * @author viaen
 * @version 28/05/2023
 */
public class DataLayer {

    private String dbName = "zwemwedstrijden";
    private final String login = "root";
    private final String pass = "279Kp9g5kf279LO!";
    private Connection con;

    public DataLayer() {
        makeConnection();
    }

    private void makeConnection() {
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                    + dbName + "?serverTimezone=UTC&allowMultiQueries=true", login, pass);

        } catch (SQLException ex) {
            Logger.getLogger(DataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
