package logica;

import java.sql.Time;

/**
 * Project_OODB_ThibaultViaene_0.1 : Bestijden
 *
 * @author viaen
 * @version 28/05/2023
 */
public class Besttijd {
    private int programma;
    private String voorNaam;
    private String achterNaam;
    private Slag slag;
    private Afstand afstand;
    private Time besttijd;

    public Besttijd(String voorNaam, String achterNaam, Slag slag, Afstand afstand, Time besttijd) {
        this.voorNaam = voorNaam;
        this.achterNaam = achterNaam;
        this.slag = slag;
        this.afstand = afstand;
        this.besttijd = besttijd;
    }

    @Override
    public String toString() {
        return voorNaam + " " + achterNaam + " " + slag + " " + afstand.toString().replace("_","") + " PR: " + besttijd;
    }
}
