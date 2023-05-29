package logica;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Project_OODB_ThibaultViaene_0.1 : Zwembaden
 *
 * @author viaen
 * @version 28/05/2023
 */
public class Zwembad {
    private Adres adres;
    private String naam;
    private Lengte lengte;
    private AantalBanen aantalBanen;
    private ArrayList<Wedstrijden> wedstrijden;

    private int adresId;

    private int id;

    public Zwembad(Adres adres, String naam, Lengte lengte, AantalBanen aantalBanen, int id) {
        if (adres == null) throw new IllegalArgumentException("Vul een adres in.");
        if (naam == null) throw new IllegalArgumentException("vul een naam in.");
        this.adres = adres;
        this.naam = naam;
        this.lengte = lengte;
        this.aantalBanen = aantalBanen;
        this.id = id;
    }

    public Zwembad(Adres adres, String naam, Lengte lengte, AantalBanen aantalBanen) {
        if (adres == null) throw new IllegalArgumentException("Vul een adres in.");
        if (naam == null) throw new IllegalArgumentException("vul een naam in.");
        this.naam = naam;
        this.adres = adres;
        this.lengte = lengte;
        this.aantalBanen = aantalBanen;
    }

    public Zwembad(Adres adres, String naam, Lengte lengte, AantalBanen aantalBanen, ArrayList<Wedstrijden> wedstrijden) {
        if (adres == null) throw new IllegalArgumentException("Vul een adres in.");
        if (naam == null) throw new IllegalArgumentException("vul een naam in.");
        this.adres = adres;
        this.naam = naam;
        this.lengte = lengte;
        this.aantalBanen = aantalBanen;
        this.wedstrijden = wedstrijden;
    }

    public Zwembad(int id, int adresId, Lengte lengte,AantalBanen aantalBanen,String naam) {
        if (naam == null) throw new IllegalArgumentException("vul een naam in.");
        this.naam = naam;
        this.lengte = lengte;
        this.aantalBanen = aantalBanen;
        this.adresId = adresId;
        this.id = id;
    }

    public int getAdresId() {
        return adresId;
    }

    public void setAdresId(int adresId) {
        this.adresId = adresId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Lengte getLengte() {
        return lengte;
    }

    public void setLengte(Lengte lengte) {
        this.lengte = lengte;
    }

    public AantalBanen getAantalBanen() {
        return aantalBanen;
    }

    public void setAantalBanen(AantalBanen aantalBanen) {
        this.aantalBanen = aantalBanen;
    }

    public ArrayList<Wedstrijden> getWedstrijden() {
        return wedstrijden;
    }

    public void setWedstrijden(ArrayList<Wedstrijden> wedstrijden) {
        this.wedstrijden = wedstrijden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zwembad zwembad = (Zwembad) o;
        return Objects.equals(naam, zwembad.naam);
    }

    @Override
    public String toString() {
        return "Zwembad: " + id + " " + naam + " / " + adres;
    }
}
