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

    public String getStraat() {
        return straat;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public String getGemeente() {
        return gemeente;
    }

    public int getPostcode() {
        return postcode;
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
