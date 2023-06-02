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

    public int getReeksNummer() {
        return reeksNummer;
    }

    public void setReeksNummer(int reeksNummer) {
        this.reeksNummer = reeksNummer;
    }

    public Time getAanvangsUur() {
        return aanvangsUur;
    }

    public void setAanvangsUur(Time aanvangsUur) {
        this.aanvangsUur = aanvangsUur;
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
}
