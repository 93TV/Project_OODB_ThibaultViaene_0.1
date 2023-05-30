package data;

import logica.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static logica.Helper.aanntalBanenNaarEnum;
import static logica.Helper.lengteNaarEnum;

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

    public void insertAdres(Adres adres) throws SQLException {
        PreparedStatement stmt = null;
        try {
            if (checkAdres(adres) == -1) {
                stmt = this.con.prepareStatement("INSERT INTO Adressen (straat, huisnummer, gemeente, postcode) VALUES (?, ?, ?, ?)");
                stmt.setString(1, adres.getStraat());
                stmt.setString(2, adres.getHuisnummer());
                stmt.setString(3, adres.getGemeente());
                stmt.setInt(4, adres.getPostcode());
                stmt.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataLaag.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public int checkAdres(Adres adres) throws SQLException {
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select * FROM adressen");
        while (rs.next()) {
            int id = rs.getInt("id");
            String straat = rs.getString("straat");
            String huisNummer = rs.getString("huisnummer");
            String gemeente = rs.getString("gemeente");
            int postcode = rs.getInt("postcode");
            Adres a = new Adres(id, straat, huisNummer, gemeente, postcode);
            if (adres.equals(a)) {
                return a.getId();
            }
        }
        return -1;
    }

    public void insertZwembad(Zwembad zwembad, int adresId) throws SQLException {
        PreparedStatement stmt = null;
        try {
            if (checkzwembad(zwembad)) {
                stmt = this.con.prepareStatement("INSERT INTO Zwembaden (adres_id, lengte, aantal_banen, naam) VALUES (?, ?, ?, ?)");
                stmt.setInt(1, adresId);
                stmt.setString(2, zwembad.getLengte().toString().replace("_", ""));
                stmt.setString(3, zwembad.getAantalBanen().toString().replace("_", ""));
                stmt.setString(4, zwembad.getNaam());
                stmt.executeUpdate();
            } else {
                throw new IllegalArgumentException("Zwembad bestaat al!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataLaag.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    private boolean checkzwembad(Zwembad zwembad) throws SQLException {
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select * FROM Zwembaden");
        while (rs.next()) {
            String naam = rs.getString("naam");
            Zwembad zwb = new Zwembad(naam);
            if (zwembad.equals(zwb)) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Zwembad> geefZwembadenNaamEnId() throws SQLException {
        ArrayList<Zwembad> zwembadenNaamEnId = new ArrayList<>();
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select id, naam FROM Zwembaden");
        while (rs.next()) {
            String naam = rs.getString("naam");
            int id = rs.getInt("id");
            zwembadenNaamEnId.add(new Zwembad(id, naam));
        }
        return zwembadenNaamEnId;
    }

    public void insertWedstrijd(Wedstrijd wedstrijd) throws SQLException {
        PreparedStatement stmt = null;
        try {
            if (checkWedstrijd(wedstrijd)) {
                stmt = this.con.prepareStatement("INSERT INTO wedstrijden (naam, datum, tijdsregistratie, dagdeel) VALUES (?, ?, ?, ?)");
                stmt.setString(1, wedstrijd.getNaam());
                stmt.setDate(2, wedstrijd.getDatum());
                stmt.setString(3, wedstrijd.getTijdRregistratie().toString().toUpperCase());
                stmt.setString(4, wedstrijd.getDagDeel().toString().toUpperCase());
                stmt.executeUpdate();
            } else {
                throw new IllegalArgumentException("Wedstrijd met exact deze gegevens bestaat al!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataLaag.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    private boolean checkWedstrijd(Wedstrijd wedstrijd) throws SQLException {
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select * FROM Wedstrijden");
        while (rs.next()) {
            String naam = rs.getString("naam");
            Date datum = rs.getDate("datum");
            String tijdsRegistratie = rs.getString("tijdsregistratie");
            String dagDeel = rs.getString("dagdeel");
            Wedstrijd wdstr = new Wedstrijd(naam, datum, TijdsRegistratie.valueOf(tijdsRegistratie), DagDeel.valueOf(dagDeel));
            if (wedstrijd.equals(wdstr)) {
                return false;
            }
        }
        return true;
    }


}