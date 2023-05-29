package logica;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Project_OODB_ThibaultViaene_0.1 : WedstrijdProgramma
 *
 * @author viaen
 * @version 28/05/2023
 */
public class WedstrijdProgramma {
    private int programmaNummer;
    private LeeftijdsCategorie leeftijdsCategorie;
    private Time aanvangsUur;
    private ArrayList<Serie> series;

    public WedstrijdProgramma(int programmaNummer, LeeftijdsCategorie leeftijdsCategorie, Time aanvangsUur, ArrayList<Serie> series) {
        this.programmaNummer = programmaNummer;
        this.leeftijdsCategorie = leeftijdsCategorie;
        this.aanvangsUur = aanvangsUur;
        this.series = series;
    }

    public int getProgrammaNummer() {
        return programmaNummer;
    }

    public void setProgrammaNummer(int programmaNummer) {
        this.programmaNummer = programmaNummer;
    }

    public LeeftijdsCategorie getLeeftijdsCategorie() {
        return leeftijdsCategorie;
    }

    public void setLeeftijdsCategorie(LeeftijdsCategorie leeftijdsCategorie) {
        this.leeftijdsCategorie = leeftijdsCategorie;
    }

    public Time getAanvangsUur() {
        return aanvangsUur;
    }

    public void setAanvangsUur(Time aanvangsUur) {
        this.aanvangsUur = aanvangsUur;
    }

    public ArrayList<Serie> getSeries() {
        return series;
    }

    public void setSeries(ArrayList<Serie> series) {
        this.series = series;
    }
}
