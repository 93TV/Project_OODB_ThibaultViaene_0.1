package logica;

import java.sql.Time;

/**
 * Project_OODB_ThibaultViaene_0.1 : Series
 *
 * @author viaen
 * @version 28/05/2023
 */
public class Serie {
    private int reeksNummer;
    private Time aanvangsUur;

    public Serie(int reeksNummer, Time aanvangsUur) {
        this.reeksNummer = reeksNummer;
        this.aanvangsUur = aanvangsUur;
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
}
