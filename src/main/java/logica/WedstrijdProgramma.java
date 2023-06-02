package logica;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Project_OODB_ThibaultViaene_0.1 : WedstrijdProgramma
 *
 * @author viaen
 * @version 28/05/2023
 */
public class WedstrijdProgramma {
    private int id;
    private int programmaNummer;
    private LeeftijdsCategorie leeftijdsCategorie;
    private Time aanvangsUur;
    private int wedstrijdId;
    private Slag slag;
    private Afstand afstand;
    private boolean aflossing;
    private Geslacht geslacht;
    private ArrayList<Serie> series;

    public WedstrijdProgramma(int id, Slag slag, Afstand afstand, boolean aflossing, Geslacht geslacht) {
        this.id = id;
        this.slag = slag;
        this.afstand = afstand;
        this.aflossing = aflossing;
        this.geslacht = geslacht;
    }

    public WedstrijdProgramma(LeeftijdsCategorie leeftijdsCategorie, Time aanvangsUur, int wedstrijdId, Slag slag, Afstand afstand, boolean aflossing, Geslacht geslacht) {
        this.leeftijdsCategorie = leeftijdsCategorie;
        this.aanvangsUur = aanvangsUur;
        this.wedstrijdId = wedstrijdId;
        this.slag = slag;
        this.afstand = afstand;
        this.aflossing = aflossing;
        this.geslacht = geslacht;
    }

    public int getId() {
        return id;
    }

    public int getProgrammaNummer() {
        return programmaNummer;
    }

    public LeeftijdsCategorie getLeeftijdsCategorie() {
        return leeftijdsCategorie;
    }

    public Time getAanvangsUur() {
        return aanvangsUur;
    }

    public int getWedstrijdId() {
        return wedstrijdId;
    }

    public Slag getSlag() {
        return slag;
    }

    public Afstand getAfstand() {
        return afstand;
    }

    public boolean isAflossing() {
        return aflossing;
    }

    public Geslacht getGeslacht() {
        return geslacht;
    }

    public ArrayList<Serie> getSeries() {
        return series;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WedstrijdProgramma that = (WedstrijdProgramma) o;
        return aflossing == that.aflossing && slag == that.slag && afstand == that.afstand && geslacht == that.geslacht;
    }

    @Override
    public String toString() {
        return "WedstrijdProgramma " + id + leeftijdsCategorie + slag + afstand + geslacht + "Aflossing: " + aflossing;
    }
}
