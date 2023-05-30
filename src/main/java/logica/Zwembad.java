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
    private int id;
    private Adres adres;
    private String naam;
    private Lengte lengte;
    private AantalBanen aantalBanen;

    public Zwembad(String naam) {
        this.naam = naam;
    }

    public Zwembad(Adres adres, String naam, Lengte lengte, AantalBanen aantalBanen) {
        this.naam = naam;
        this.adres = adres;
        this.lengte = lengte;
        this.aantalBanen = aantalBanen;
    }

    public String getNaam() {
        return naam;
    }

    public Lengte getLengte() {
        return lengte;
    }

    public AantalBanen getAantalBanen() {
        return aantalBanen;
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
