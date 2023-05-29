package logica;

/**
 * Project_OODB_ThibaultViaene_0.1 : Adres
 *
 * @author viaen
 * @version 28/05/2023
 */
public class Adres {
    private String straat;
    private String huisnummer;
    private String gemeente;
    private int postcode;

    public Adres(String straat, String huisnummer, String gemeente, int postcode) {
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.gemeente = gemeente;
        this.postcode = postcode;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }
}
