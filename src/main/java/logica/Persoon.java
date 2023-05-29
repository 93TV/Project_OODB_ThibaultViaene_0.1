package logica;

/**
 * Project_OODB_ThibaultViaene_0.1 : Personen
 *
 * @author viaen
 * @version 28/05/2023
 */
public class Persoon {
    private Adres adres;
    private String voornaam;
    private String achternaam;
    private int geboorteJaar;
    private Geslacht geslacht;
    private String licentieNummer;
    private String club;

    public Persoon(Adres adres, String voornaam, String achternaam, int geboorteJaar, Geslacht geslacht, String licentieNummer, String club) {
        this.adres = adres;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboorteJaar = geboorteJaar;
        this.geslacht = geslacht;
        this.licentieNummer = licentieNummer;
        this.club = club;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public int getGeboorteJaar() {
        return geboorteJaar;
    }

    public void setGeboorteJaar(int geboorteJaar) {
        this.geboorteJaar = geboorteJaar;
    }

    public Geslacht getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(Geslacht geslacht) {
        this.geslacht = geslacht;
    }

    public String getLicentieNummer() {
        return licentieNummer;
    }

    public void setLicentieNummer(String licentieNummer) {
        this.licentieNummer = licentieNummer;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }
}
