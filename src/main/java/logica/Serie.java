package logica;

import java.sql.Time;

/**
 * Project_OODB_ThibaultViaene_0.1 : Series
 *
 * @author viaen
 * @version 28/05/2023
 */
public class Serie {

    private int id;
    private int reeksNummer;
    private Time aanvangsUur;
    private int wedstrijdProgrammaId;

    public Serie(int wedstrijdProgrammaId, int reeksNummer, Time aanvangsUur) {
        this.reeksNummer = reeksNummer;
        this.aanvangsUur = aanvangsUur;
        this.wedstrijdProgrammaId = wedstrijdProgrammaId;
    }

    public Serie(int wedstrijdProgrammaId , Time aanvangsUur) {
        this.aanvangsUur = aanvangsUur;
        this.wedstrijdProgrammaId = wedstrijdProgrammaId;
    }

    public Serie(int id, int reeksNummer, Time aanvangsUur, int wedstrijdProgrammaId) {
        this.id = id;
        this.reeksNummer = reeksNummer;
        this.aanvangsUur = aanvangsUur;
        this.wedstrijdProgrammaId = wedstrijdProgrammaId;
    }

    public Time getAanvangsUur() {
        return aanvangsUur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWedstrijdProgrammaId() {
        return wedstrijdProgrammaId;
    }

    public void setWedstrijdProgrammaId(int wedstrijdProgrammaId) {
        this.wedstrijdProgrammaId = wedstrijdProgrammaId;
    }

    @Override
    public String toString() {
        return "Serie: " + id + " / Reeks: " + reeksNummer + " / Aanvang: " + aanvangsUur;
    }
}
