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
    private Time besttijd;

    public Besttijd(int programma, Time besttijd) {
        this.programma = programma;
        this.besttijd = besttijd;
    }

    public int getProgramma() {
        return programma;
    }

    public void setProgramma(int programma) {
        this.programma = programma;
    }

    public Time getBesttijd() {
        return besttijd;
    }

    public void setBesttijd(Time besttijd) {
        this.besttijd = besttijd;
    }
}
