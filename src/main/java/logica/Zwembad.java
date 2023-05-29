package logica;

import java.util.ArrayList;

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

    public Zwembad(Adres adres, String naam, Lengte lengte, AantalBanen aantalBanen) {
        if (adres == null) throw new IllegalArgumentException("Vul een adres in.");
        if (naam == null) throw new IllegalArgumentException("vul een naam in.");
        this.naam = naam;
        this.adres = adres;
        this.lengte = lengte;
        this.aantalBanen = aantalBanen;
    }

    public Zwembad(Adres adres, String naam, Lengte lengte, AantalBanen aantalBanen, ArrayList<Wedstrijden> wedstrijden) {
        this.adres = adres;
        this.naam = naam;
        this.lengte = lengte;
        this.aantalBanen = aantalBanen;
        this.wedstrijden = wedstrijden;
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
}
