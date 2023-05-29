package logica;

import java.util.Objects;

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

    private int id;

    public Adres(String straat, String huisnummer, String gemeente, int postcode) {
        if (straat == null) throw new IllegalArgumentException ("Vul een straat in.");
        if (huisnummer == null) throw new IllegalArgumentException("Vul een huisnummer in.");
        if (gemeente == null) throw new IllegalArgumentException("Vul een gemeente in.");
        if (postcode == 0) throw new IllegalArgumentException("Vul een Postcode in.");
        this.postcode = postcode;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.gemeente = gemeente;
    }

    public Adres(int id, String straat, String huisnummer, String gemeente, int postcode) {
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.gemeente = gemeente;
        this.postcode = postcode;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Adres: " + id + " " + straat + " " + huisnummer + " " + postcode + " " + gemeente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adres adres = (Adres) o;
        return postcode == adres.postcode && Objects.equals(straat, adres.straat) && Objects.equals(huisnummer, adres.huisnummer) && Objects.equals(gemeente, adres.gemeente);
    }

}
