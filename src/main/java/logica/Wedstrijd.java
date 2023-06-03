package logica;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Project_OODB_ThibaultViaene_0.1 : Wedstrijden
 *
 * @author viaen
 * @version 28/05/2023
 */
public class Wedstrijd {
    private String naam;
    private Date datum;
    private TijdsRegistratie tijdRregistratie;
    private DagDeel dagDeel;
    private int zwembadID;

    private int id;


    public Wedstrijd(int zwembadID, String naam, Date datum, TijdsRegistratie tijdRregistratie, DagDeel dagDeel) {
        this.zwembadID = zwembadID;
        this.naam = naam;
        this.datum = datum;
        this.tijdRregistratie = tijdRregistratie;
        this.dagDeel = dagDeel;
    }

    public Wedstrijd(String naam, Date datum, TijdsRegistratie tijdRregistratie, DagDeel dagDeel) {
        this.naam = naam;
        this.datum = datum;
        this.tijdRregistratie = tijdRregistratie;
        this.dagDeel = dagDeel;
    }

    public Wedstrijd(int id, String naam) {
        this.naam = naam;
        this.id  = id;
    }

    public int getZwembadID() {
        return zwembadID;
    }

    public String getNaam() {
        return naam;
    }

    public Date getDatum() {
        return datum;
    }

    public TijdsRegistratie getTijdRregistratie() {
        return tijdRregistratie;
    }

    public DagDeel getDagDeel() {
        return dagDeel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wedstrijd wedstrijd = (Wedstrijd) o;
        return Objects.equals(naam, wedstrijd.naam) && Objects.equals(datum, wedstrijd.datum) && tijdRregistratie == wedstrijd.tijdRregistratie && dagDeel == wedstrijd.dagDeel;
    }

    @Override
    public String toString() {
        return "Wedstrijd " + id + " " + naam;
    }
}
