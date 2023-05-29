package logica;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Project_OODB_ThibaultViaene_0.1 : Wedstrijden
 *
 * @author viaen
 * @version 28/05/2023
 */
public class Wedstrijden {
    private String naam;
    private Date datum;
    private TijdsRegistratie tijdRregistratie;
    private DagDeel dagDeel;
    private ArrayList<Official> jury;
    private ArrayList<WedstrijdProgramma> wedstrijdProgramma;

    public Wedstrijden(String naam, Date datum, TijdsRegistratie tijdRregistratie, DagDeel dagDeel, ArrayList<Official> jury, ArrayList<WedstrijdProgramma> wedstrijdProgramma) {
        this.naam = naam;
        this.datum = datum;
        this.tijdRregistratie = tijdRregistratie;
        this.dagDeel = dagDeel;
        this.jury = jury;
        this.wedstrijdProgramma = wedstrijdProgramma;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public TijdsRegistratie getTijdRregistratie() {
        return tijdRregistratie;
    }

    public void setTijdRregistratie(TijdsRegistratie tijdRregistratie) {
        this.tijdRregistratie = tijdRregistratie;
    }

    public DagDeel getDagDeel() {
        return dagDeel;
    }

    public void setDagDeel(DagDeel dagDeel) {
        this.dagDeel = dagDeel;
    }

    public ArrayList<Official> getJury() {
        return jury;
    }

    public void setJury(ArrayList<Official> jury) {
        this.jury = jury;
    }

    public ArrayList<WedstrijdProgramma> getWedstrijdProgramma() {
        return wedstrijdProgramma;
    }

    public void setWedstrijdProgramma(ArrayList<WedstrijdProgramma> wedstrijdProgramma) {
        this.wedstrijdProgramma = wedstrijdProgramma;
    }
}
