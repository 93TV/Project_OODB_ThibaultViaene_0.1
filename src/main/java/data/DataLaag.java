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
        ResultSet rs = stmt.executeQuery("Select aantal_banen FROM Zwembaden WHERE zwembad.id = '" + zwbID + "'");
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

    public Zwembad geefZwembadenNaamEnId(int wedId) throws SQLException {
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT zwembaden.naam FROM zwembaden\n" +
                "INNER JOIN wedstrijden ON zwembad_id = zwembaden.id\n" +
                "WHERE wedstrijden.id ='" + wedId + "'");
        while (rs.next()) {
            String naam = rs.getString("naam");
            return new Zwembad(naam);
        }
        return null;
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
        if (rs.next()) return rs.getInt("zwembad_id");
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
                if (!juryCompleet(wedstrijdId, official)) {
                    if (magOfficialFunctieUitvoeren(official, checkDiplomaOfficial(official))) {
                        stmt = this.con.prepareStatement("INSERT INTO jury (wedstrijd_id, official_id, functie) VALUES (?, ?, ?)");
                        stmt.setInt(1, wedstrijdId);
                        stmt.setInt(2, official.getId());
                        stmt.setString(3, official.getFunctie());
                        stmt.executeUpdate();
                    } else {
                        throw new IllegalArgumentException("Official heeft niet het juiste diploma!");
                    }
                } else {
                    throw new IllegalArgumentException("Functie al ingevuld!");
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

    private boolean juryCompleet(int wedId, Official official) throws SQLException {
        ArrayList<Official> jury = geefJuryPerWedstrijd(wedId);
        int maxTijdOpKeerPunt = aantalBanen(wedstrijdIdNaarZwembadId(wedId));
        int kampCount = 0;
        int secreCount = 0;
        int starterCount = 0;
        int zwemRechtcount = 0;
        int tijdOpneemCount = 0;
        int keerPuntCount = 0;

        for (Official o : jury) {
            if (o.getFunctie().equals(Functie.KAMPRECHTER.toString())) kampCount++;
            if (o.getFunctie().equals(Functie.JURYSECRETARIS.toString())) secreCount++;
            if (o.getFunctie().equals(Functie.STARTER.toString())) starterCount++;
            if (o.getFunctie().equals(Functie.ZWEMRECHTER.toString())) zwemRechtcount++;
            if (o.getFunctie().equals(Functie.TIJDOPNEMER.toString())) tijdOpneemCount++;
            if (o.getFunctie().equals(Functie.KEERPUNTRECHTER.toString())) keerPuntCount++;
        }
        if (official.getFunctie().equals(Functie.KAMPRECHTER.toString()) && kampCount == 1) return true;
        if (official.getFunctie().equals(Functie.JURYSECRETARIS.toString()) && secreCount == 1) return true;
        if (official.getFunctie().equals(Functie.STARTER.toString()) && starterCount == 1) return true;
        if (official.getFunctie().equals(Functie.ZWEMRECHTER.toString()) && zwemRechtcount == 2) return true;
        if (official.getFunctie().equals(Functie.TIJDOPNEMER.toString()) && tijdOpneemCount >= maxTijdOpKeerPunt)
            return true;
        if (official.getFunctie().equals(Functie.KEERPUNTRECHTER.toString()) && keerPuntCount >= maxTijdOpKeerPunt)
            return true;
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

    public void insertProgramma(WedstrijdProgramma programma) throws SQLException {
        PreparedStatement stmt = null;
        try {
            if (checkProgrammaId(programma) == -1) {
                stmt = this.con.prepareStatement("INSERT INTO programmas(slag, afstand, aflossing, geslacht) VALUES(?,?,?,?)");
                stmt.setString(1, programma.getSlag().toString());
                stmt.setString(2, programma.getAfstand().toString().replace("_", ""));
                stmt.setBoolean(3, programma.isAflossing());
                stmt.setString(4, programma.getGeslacht().toString());
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

    public void insertProgrammaWedstrijd(WedstrijdProgramma programma) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = this.con.prepareStatement("INSERT INTO wedstrijdprogrammas(wedstrijd_id, programma_id, programmanummer, leeftijdscategorie, aanvangsuur) VALUES(?,?,?,?,?)");
            stmt.setInt(1, programma.getWedstrijdId());
            stmt.setInt(2, checkProgrammaId(programma));
            stmt.setInt(3, maakProgrammaNummer(programma));
            stmt.setString(4, Helper.leeftijdsCategorie(programma.getLeeftijdsCategorie()));
            stmt.setTime(5, programma.getAanvangsUur());
            stmt.executeUpdate();
//            } else throw new IllegalArgumentException("Programma in deze wedstrijd bestaat al op dit uur!");
        } catch (SQLException ex) {
            Logger.getLogger(DataLaag.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    private int maakProgrammaNummer(WedstrijdProgramma programma) throws SQLException {
        int programmaNummer = 1;
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select count(*) FROM wedstrijdprogrammas WHERE wedstrijd_id = '" + programma.getWedstrijdId() + "' AND programma_id = '" + checkProgrammaId(programma) + "'");
        if (rs.next()) {
            programmaNummer = rs.getInt("count(*)") + 1;
        }
        return programmaNummer;
    }

    public int checkProgrammaId(WedstrijdProgramma programma) throws SQLException {
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select * FROM programmas");
        while (rs.next()) {
            int id = rs.getInt("id");
            String slag = rs.getString("slag");
            String afstand = rs.getString("afstand");
            Boolean aflossing = rs.getBoolean("aflossing");
            String geslacht = rs.getString("geslacht");
            WedstrijdProgramma p = new WedstrijdProgramma(id, Slag.valueOf(slag), Afstand.valueOf(afstand.replaceFirst("", "_")), aflossing, Geslacht.valueOf(geslacht));
            if (programma.equals(p)) {
                return p.getId();
            }
        }
        return -1;
    }

    public ArrayList<WedstrijdProgramma> geefWedstrijdProgrammas() throws SQLException {
        ArrayList<WedstrijdProgramma> wedstrijdProgrammas = new ArrayList<>();
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select * FROM wedstrijdprogrammas");
        while (rs.next()) {
            int id = rs.getInt("id");
            int wedId = rs.getInt("wedstrijd_id");
            int progId = rs.getInt("programma_id");
            int programmanummer = rs.getInt("programmanummer");
            LeeftijdsCategorie lc = Helper.reverseLeeftijd(rs.getString("leeftijdscategorie"));
            Time aanvangUur = rs.getTime("aanvangsuur");
            wedstrijdProgrammas.add(new WedstrijdProgramma(id, wedId, progId, programmanummer, lc, aanvangUur));
        }
        return wedstrijdProgrammas;
    }

    public ArrayList<WedstrijdProgramma> geefWedstrijdProgrammas(int wedId) throws SQLException {
            ArrayList<WedstrijdProgramma> wedstrijdProgrammas = new ArrayList<>();
            Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("Select * FROM wedstrijdprogrammas WHERE wedstrijd_id = '" + wedId + "'");
            while (rs.next()) {
                int id = rs.getInt("id");
                int progId = rs.getInt("programma_id");
                int programmanummer = rs.getInt("programmanummer");
                LeeftijdsCategorie lc = Helper.reverseLeeftijd(rs.getString("leeftijdscategorie"));
                Time aanvangUur = rs.getTime("aanvangsuur");
                wedstrijdProgrammas.add(new WedstrijdProgramma(id, wedId, progId, programmanummer, lc, aanvangUur));
            }
            return wedstrijdProgrammas;
    }

    public void insertSessie(Serie s) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = this.con.prepareStatement("INSERT INTO series(wedstrijdprogramma_id, reeksnummer,aanvangsuur) VALUES(?,?,?)");
            stmt.setInt(1, s.getWedstrijdProgrammaId());
            stmt.setInt(2, getReeksNummer(s));
            stmt.setTime(3, s.getAanvangsUur());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DataLaag.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    private int getReeksNummer(Serie s) throws SQLException {
        int reeksNummer = 1;
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select count(*) FROM series WHERE wedstrijdprogramma_id = '" + s.getWedstrijdProgrammaId() + "'");
        if (rs.next()) {
            reeksNummer = rs.getInt("count(*)") + 1;
        }
        return reeksNummer;
    }

    public ArrayList<Serie> geefSeries() throws SQLException {
        ArrayList<Serie> series = new ArrayList<>();
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select * FROM series");
        while (rs.next()) {
            int id = rs.getInt("id");
            int wedprogId = rs.getInt("wedstrijdprogramma_id");
            int reeksnummer = rs.getInt("reeksnummer");
            Time aanvangUur = rs.getTime("aanvangsuur");
            series.add(new Serie(id, reeksnummer, aanvangUur, wedprogId));
        }
        return series;
    }

    public ArrayList<Serie> geefSeries(int wedId) throws SQLException {
        ArrayList<Serie> series = new ArrayList<>();
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select * FROM series WHERE wedstrijdprogramma_id = '" + wedId + "'");
        while (rs.next()) {
            int id = rs.getInt("id");
            int wedprogId = rs.getInt("wedstrijdprogramma_id");
            int reeksnummer = rs.getInt("reeksnummer");
            Time aanvangUur = rs.getTime("aanvangsuur");
            series.add(new Serie(id, reeksnummer, aanvangUur, wedprogId));
        }
        return series;
    }

    public int getZwemmerId(String naam, String voornaam) throws SQLException {
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT id, persoon_id, voornaam, naam FROM personen INNER JOIN zwemmers ON persoon_id = personen.id WHERE naam  = '" + naam + "' AND voornaam = '" + voornaam + "'");
        if (rs.next()) {
            return rs.getInt("id");
        }
        return -1;

    }

    public void insertDeelname(Deelname d) throws SQLException {
        PreparedStatement stmt = null;
        try {
            if (!alInDeelname(d)) {
                stmt = this.con.prepareStatement("INSERT INTO deelnames(zwemmer_id, serie_id, baan, forfait) VALUES(?,?,?,?)");
                stmt.setInt(1, d.getZwemmerId());
                stmt.setInt(2, d.getSerieId());
                stmt.setInt(3, d.getBaan());
                stmt.setBoolean(4, false);
                stmt.executeUpdate();
            } else throw new IllegalArgumentException("Zwemmer al in serie!");
        } catch (SQLException ex) {
            Logger.getLogger(DataLaag.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    private boolean alInDeelname(Deelname d) throws SQLException {
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select * FROM deelnames WHERE zwemmer_id = '" + d.getZwemmerId() + "' AND serie_id = '" + d.getSerieId() + "'");
        return rs.next();
    }

    public int getEersteVrijeBaan(int serieId) throws SQLException {
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT aantal_banen, MAX(baan) FROM deelnames\n" +
                "RIGHT JOIN series ON serie_id = series.id \n" +
                "INNER JOIN wedstrijdprogrammas ON wedstrijdprogrammas.id = wedstrijdprogramma_id\n" +
                "INNER JOIN wedstrijden ON wedstrijd_id = wedstrijden.id \n" +
                "INNER JOIN zwembaden ON zwembaden.id = zwembad_id\n" +
                "WHERE series.id = '" + serieId + "'");
        if (rs.next()) {
            if (rs.getInt("max(baan)") < rs.getInt("aantal_banen"))
            return rs.getInt("max(baan)") + 1 ;
        }
        return -1;
    }

    public ArrayList<Deelname> getDeelnamesSerie(int serieId) throws SQLException {
        ArrayList<Deelname> deelnames = new ArrayList<>();
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select * FROM deelnames WHERE serie_id = '" + serieId + "'");
        while (rs.next()) {
            int zwemmerId = rs.getInt("zwemmer_id");
            int baan = rs.getInt("baan");
            Time resultaat = rs.getTime("resultaat");
            deelnames.add(new Deelname(zwemmerId, baan, resultaat, serieId));
        }
        return deelnames;
    }

    public ArrayList<Besttijd> getZwemmersEnBesttijden(int progId, int serieId) throws SQLException {
        ArrayList<Besttijd> zwemmersEnBesttijden = new ArrayList<>();
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT naam, voornaam, slag, afstand, besttijd FROM personen\n" +
                "INNER JOIN deelnames ON personen.id = deelnames.zwemmer_id\n" +
                "INNER JOIN besttijden ON besttijden.zwemmer_id = deelnames.zwemmer_id\n" +
                "INNER JOIN programmas ON besttijden.programma_id = programmas.id\n" +
                "WHERE  besttijden.programma_id = '" + progId + "' AND serie_id = '" + serieId + "'");
        while (rs.next()){
            String achterNaam = rs.getString("naam");
            String voorNaam = rs.getString("voornaam");
            Slag slag = Slag.valueOf(rs.getString("slag"));
            Afstand afstand = Afstand.valueOf(rs.getString("afstand").replaceFirst("", "_"));
            Time besttijd = rs.getTime("besttijd");
            zwemmersEnBesttijden.add(new Besttijd(voorNaam, achterNaam, slag, afstand, besttijd));
        }
        return  zwemmersEnBesttijden;
    }

    public void insertResultatenSim(int serieId,String resultaat, int baan) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = this.con.prepareStatement("UPDATE deelnames SET resultaat = ? WHERE serie_id = ? AND baan = ?");
            stmt.setTime(1, Time.valueOf(resultaat));
            stmt.setInt(2, serieId);
            stmt.setInt(3, baan);
            stmt.executeUpdate();

        } finally {
            if (stmt!=null){
                stmt.close();
            }

        }

    }

    public int getAantalZwemmers(int serieId) throws SQLException {
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("Select count(*) FROM deelnames WHERE serie_id = '" + serieId + "'");
        if (rs.next()) return rs.getInt("count(*)");
        else return -1;
    }

    public int getAfstandComp(int serieId) throws SQLException {
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT afstand from programmas\n" +
                "INNER JOIN wedstrijdprogrammas ON programmas.id = programma_id\n" +
                "INNER JOIN series ON wedstrijdprogramma_id = wedstrijdprogrammas.id\n" +
                "WHERE series.id = '" + serieId + "'");
        if (rs.next()) return rs.getInt("afstand");
        else return -1;
    }


    public int getLengteZwembad(int serieId) throws SQLException {
        Statement stmt = this.con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT lengte from programmas\n" +
                "INNER JOIN wedstrijdprogrammas ON programmas.id = programma_id\n" +
                "INNER JOIN series ON wedstrijdprogramma_id = wedstrijdprogrammas.id\n" +
                "INNER JOIN wedstrijden ON wedstrijd_id = wedstrijden.id\n" +
                "INNER JOIN zwembaden ON zwembaden.id = zwembad_id\n" +
                "WHERE series.id = '" + serieId + "'");
        if (rs.next()) return rs.getInt("lengte");
        else return -1;
    }
}