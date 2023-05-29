package data;

import logica.Adres;
import logica.Zwembad;

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

    public List<Adres> geefAdressenLijst() throws SQLException {
        Statement stmt = null;
        List<Adres> adresList = null;
        try {
            stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("Select * FROM adressen");
            adresList = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String straat = rs.getString("straat");
                String huisnummer = rs.getString("huisnummer");
                String gemeente = rs.getString("gemeente");
                int postcode = rs.getInt("postcode");
                Adres adres = new Adres(id, straat, huisnummer, gemeente, postcode);
                adresList.add(adres);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataLaag.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return adresList;
    }

    public int maakAdresAan(Adres adres) throws SQLException {
        Statement stmt = null;
        try {
            if (checkAdres(adres) != -1) {return checkAdres(adres);}
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
        return 0;
    }

    private int checkAdres(Adres a) throws SQLException {
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select * FROM adressen");
        while (rs.next()) {
            int id = rs.getInt("id");
            String straat = rs.getString("straat");
            String huisNummer = rs.getString("huisnummer");
            String gemeente = rs.getString("gemeente");
            int postcode = rs.getInt("postcode");
            Adres adres = new Adres(id, straat, huisNummer, gemeente, postcode);
            if (a.equals(adres)) {
                return adres.getId();
            }
        }
        return -1;
    }

    private int geefAdres(int adresId) throws SQLException {
        for (Adres adres : geefAdressenLijst()) {
            if (adres.getId() == adresId) return adres.getId();
        }
        return 0;
    }

    private int geefAdresId(Adres adr) throws SQLException {
        for (Adres adres : geefAdressenLijst()) {
            if (adr.equals(adres)) return adres.getId();
        }
        return 0;
    }

    public int maakZwembadAan(Zwembad zwb) throws SQLException {
        Statement stmt = null;
        try {
            if (checkZwembad(zwb) != -1) {return checkZwembad(zwb);}
            else {
                stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("SELECT * FROM zwembaden");
                rs.moveToInsertRow();
                rs.updateInt("adres_id", maakAdresAan(zwb.getAdres()));
                rs.updateString ("naam", zwb.getNaam());
                rs.updateString("lengte",zwb.getLengte().toString().replace("_", ""));
                rs.updateInt("aantal_banen",Integer.parseInt(zwb.getAantalBanen().toString().replace("_", "")));
                rs.insertRow();
                return checkZwembad(zwb);
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


    private int checkZwembad(Zwembad zwb) throws SQLException {
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select * FROM zwembaden");
        while (rs.next()) {
            int id = rs.getInt("id");
            int adresId = rs.getInt("adres_id");
            int lengte = rs.getInt("lengte");
            int aantalBanen = rs.getInt("aantal_banen");
            String naam = rs.getString("naam");
            Zwembad zwembad = new Zwembad(id, geefAdres(adresId), lengteNaarEnum(lengte), aanntalBanenNaarEnum(aantalBanen), naam);
            if (zwb.equals(zwembad)) {
                return zwembad.getId();
            }
        }
        return -1;
    }

    public ArrayList<Zwembad> geefZwembadenMetAdressen() throws SQLException {
        ArrayList<Zwembad> zwembaden = new ArrayList<>();
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT zwembaden.id, lengte, aantal_banen, naam, adres_id, straat, huisnummer, gemeente, postcode  FROM zwembaden\n" +
                "INNER JOIN adressen ON adressen.id = adres_id;");
        while (rs.next()) {
            int idZwembad = rs.getInt("id");
            int lengte = rs.getInt("lengte");
            int aantalBanen = rs.getInt("aantal_banen");
            String naam = rs.getString("naam");
            int idAdres = rs.getInt("adres_id");
            String straat = rs.getString("straat");
            String huisNummer = rs.getString("huisnummer");
            String gemeente = rs.getString("gemeente");
            int postcode = rs.getInt("postcode");
            zwembaden.add(new Zwembad(new Adres(idAdres, straat, huisNummer, gemeente, postcode), naam, lengteNaarEnum(lengte), aanntalBanenNaarEnum(aantalBanen), idZwembad));
           }
        return zwembaden;
    }
}