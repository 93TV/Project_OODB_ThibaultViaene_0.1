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

    private int aantalBanen(int zwbID) throws SQLException {
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select aantal_banen FROM Zwembaden WHERE naam = '" + zwbID + "'");
        while (rs.next()) {
            return rs.getInt("aantal_banen");
        }
        return 0;
    }

    public ArrayList<Zwembad> geefZwembadenNaamEnId() throws SQLException {
        ArrayList<Zwembad> zwembadenNaamEnId = new ArrayList<>();
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select id, naam FROM zwembaden");
        while (rs.next()) {
            String naam = rs.getString("naam");
            int id = rs.getInt("id");
            zwembadenNaamEnId.add(new Zwembad(id, naam));
        }
        return zwembadenNaamEnId;
    }

    public ArrayList<Wedstrijd> geefWedstrijdEnID() throws SQLException {
        ArrayList<Wedstrijd> wedstrijdEnId = new ArrayList<>();
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT id, naam FROM wedstrijden");
        while (rs.next()) {
            String naam = rs.getString("naam");
            int id = rs.getInt("id");
            wedstrijdEnId.add(new Wedstrijd(id, naam));
        }
        return wedstrijdEnId;
    }

    private int wedstrijdIdNaarZwembadId(int wedId) throws SQLException {
        ArrayList<Wedstrijd> wedstrijdEnId = new ArrayList<>();
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT zwembad_id FROM wedstrijden WHERE id = '" + wedId + "' ");
        if (rs.next()) return  rs.getInt("zwembad_id");
        return -1;
          }


    public ArrayList<Wedstrijd> geefWedstrijdNaamEnId() throws SQLException {
        ArrayList<Wedstrijd> wedstrijdNaamEnId = new ArrayList<>();
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select id, naam FROM wedstrijden");
        while (rs.next()) {
            String naam = rs.getString("naam");
            int id = rs.getInt("id");
            wedstrijdNaamEnId.add(new Wedstrijd(id, naam));
        }
        return wedstrijdNaamEnId;
    }

    public void insertWedstrijd(Wedstrijd wedstrijd) throws SQLException {
        PreparedStatement stmt = null;
        try {
            if (checkWedstrijd(wedstrijd)) {
                stmt = this.con.prepareStatement("INSERT INTO wedstrijden (zwembad_id, naam, datum, tijdsregistratie, dagdeel) VALUES (?, ?, ?, ?, ?)");
                stmt.setInt(1, wedstrijd.getZwembadID());
                stmt.setString(2, wedstrijd.getNaam());
                stmt.setDate(3, wedstrijd.getDatum());
                stmt.setString(4, wedstrijd.getTijdRregistratie().toString().toUpperCase());
                stmt.setString(5, wedstrijd.getDagDeel().toString().toUpperCase());
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

    public ArrayList<Official> geefJuryPerWedstrijd(int wedstrijdId) throws SQLException {
        ArrayList<Official> juryPerWedstrijd = new ArrayList<>();
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select official_id, functie FROM jury WHERE wedstrijd_id = '" + wedstrijdId + "'");
        while (rs.next()) {
            int oId = rs.getInt("official_id");
            String functie = rs.getString("functie");
            juryPerWedstrijd.add(new Official(oId, functie));
        }
        return juryPerWedstrijd;
    }

    public void insertJury(Official official, int wedstrijdId) throws SQLException {
        PreparedStatement stmt = null;
        try {
            if (checkJury(official.getId(), wedstrijdId)) {
                if (!juryCompleet(wedstrijdId)) {
                    if (magOfficialFunctieUitvoeren(official, checkDiplomaOfficial(official))) {
                        stmt = this.con.prepareStatement("INSERT INTO jury (wedstrijd_id, official_id, functie) VALUES (?, ?, ?)");
                        stmt.setInt(1, wedstrijdId);
                        stmt.setInt(2, official.getId());
                        stmt.setString(3, official.getFunctie());
                        stmt.executeUpdate();
                    } else {
                        throw new IllegalArgumentException("Official heeft niet het juiste diploma");
                    }
                } else {
                    throw new IllegalArgumentException("Jury is al compleet!");
                }
            } else {
                throw new IllegalArgumentException("Elke official mag maar 1 functie per wedstrijd uitoefenen!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataLaag.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    private boolean juryCompleet(int wedId) throws SQLException {
        ArrayList<Official> jury = geefJuryPerWedstrijd(wedId);
        int maxTijdOpKeerPunt = aantalBanen(wedstrijdIdNaarZwembadId(wedId));
        int kampCount = 0;
        int secreCount = 0;
        int starterCount = 0;
        int zwemRechtcount = 0;
        int tijdOpneemCount = 0;
        int keerPuntCount =0;

        for (Official o : jury){
            if (o.getFunctie().equals(Functie.KAMPRECHTER.toString())) kampCount++;
            if (o.getFunctie().equals(Functie.JURYSECRETARIS.toString())) secreCount++;
            if (o.getFunctie().equals(Functie.STARTER.toString())) starterCount++;
            if (o.getFunctie().equals(Functie.ZWEMRECHTER.toString())) zwemRechtcount++;
            if (o.getFunctie().equals(Functie.TIJDOPNEMER.toString())) tijdOpneemCount++;
            if (o.getFunctie().equals(Functie.KEERPUNTRECHTER.toString())) keerPuntCount++;
        }

        if (kampCount == 1 || secreCount == 1 || starterCount == 1 || zwemRechtcount == 1 ) return true;
        if (tijdOpneemCount == maxTijdOpKeerPunt || keerPuntCount == maxTijdOpKeerPunt) return true;
        return false;

    }

    private boolean checkJury(int oId, int wedId) throws SQLException {
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select * FROM jury WHERE official_id = '" + oId + "' AND wedstrijd_id = '" + wedId + "'");
        return !rs.next();
    }

    private int checkDiplomaOfficial(Official official) throws SQLException {
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select kamprechter,jurysecretaris,starter,tak FROM officials where persoon_id =  '" + official.getId() + "'");
        while (rs.next()) {
            boolean kamp = rs.getBoolean("kamprechter");
            boolean jury = rs.getBoolean("jurysecretaris");
            boolean starter = rs.getBoolean("starter");
            boolean tak = rs.getBoolean("tak");
            if (kamp) return 1;
            if (jury) return 2;
            if (starter) return 3;
            if (tak) return 4;
        }
        return -1;
    }

    private boolean magOfficialFunctieUitvoeren(Official o, int diploma) {
        if (o.getFunctie().equals(Functie.KAMPRECHTER.toString()) && diploma != 1) return false;
        if (o.getFunctie().equals(Functie.STARTER.toString()) && diploma != 3) return false;
        if (o.getFunctie().equals(Functie.JURYSECRETARIS.toString()) && (diploma != 1 && diploma != 2)) return false;
        else return true;
    }

    public void deleteJury(Official official, int wedstrijdId) throws SQLException {
        PreparedStatement stmt = null;
        try {
            if (!checkJury(official.getId(), wedstrijdId)) {
                stmt = this.con.prepareStatement("DELETE FROM jury WHERE official_id = '" + official.getId() + "' AND wedstrijd_id = '" + wedstrijdId + "'");
                stmt.executeUpdate();
            } else throw new IllegalArgumentException("Jury niet in de lijst van deze wedstrijd");

        } catch (SQLException ex) {
            Logger.getLogger(DataLaag.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

}
