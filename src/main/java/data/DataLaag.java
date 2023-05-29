package data;

import logica.Adres;
import logica.Zwembad;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Project_OODB_ThibaultViaene_0.1 : DataLayer
 *
 * @author viaen
 * @version 28/05/2023
 */
public class DataLaag {

    private String dbName = "zwemwedstrijden";
    private final String login = "root";
    private final String pass = "279Kp9g5kf279LO!";
    private Connection con;

    public DataLaag() {
        makeConnection();
    }

    private void makeConnection() {
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                    + dbName + "?serverTimezone=UTC&allowMultiQueries=true", login, pass);

        } catch (SQLException ex) {
            Logger.getLogger(DataLaag.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int maakZwembadAan(Zwembad zwb, Adres adres) throws SQLException {
        maakAdresAan(adres);
        Statement stmt = null;
        try {
            if (checkZwembad(zwb) != -1) {return checkZwembad(zwb);}
            else {
                stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("Select * FROM klanten");
                rs.moveToInsertRow();
                rs.updateString("naam", zwb.getNaam());
                rs.updateEnum ("lengte", zwb.getLengte());
                rs.updateInt("postcode", zwb.getPostcode());
                rs.updateString("gemeente", zwb.getGemeente());
                rs.updateDate("klant_sinds", zwb.getKlantSinds());
                rs.updateString("tel", zwb.getTel());
                rs.insertRow();
                return checkZwembad(k);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataLaag.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return 0;
    }

    public void maakAdresAan(Adres adres) throws SQLException {
        Statement stmt = null;
        try {
            if (checkAdres(adres)) {
                throw new IllegalArgumentException("Adres bestaat al");
            }
            else {
                stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("Select * FROM adressen");
                rs.moveToInsertRow();
                rs.updateString("straat", adres.getStraat());
                rs.updateString ("huisnummer", adres.getHuisnummer());
                rs.updateInt("postcode", adres.getPostcode());
                rs.updateString("gemeente", adres.getGemeente());
                rs.insertRow();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataLaag.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    private boolean checkAdres(Adres a) throws SQLException {
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select * FROM zwembaden");
        while (rs.next()) {
            String straat = rs.getString("straat");
            String huisNummer = rs.getString("huisnummer");
            String gemeente = rs.getString("gemeente");
            int postcode = rs.getInt("postcode");
            Adres adres = new Adres(straat, huisNummer, gemeente, postcode);
            if (a.equals(adres)) {
                return true;
            }
        }
        return false;
    }

    private int checkZwembad(Zwembad zwb) throws SQLException {
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select * FROM zwembaden");
        while (rs.next()) {
            int id = rs.getInt("id");
            String naam = rs.getString("naam");
            String adres = rs.getString("adres");
            int postcode = rs.getInt("postcode");
            String gemeente = rs.getString("gemeente");
            java.sql.Date date = rs.getDate("klant_sinds");
            String tel = rs.getString("tel");
            Zwembad zwembad = new Zwembad(id, naam, adres, postcode, gemeente, date, tel);
            if (zwembad.equals(zwb)) {
                return zwembad.getId();
            }
        }
        return -1;
    }
}