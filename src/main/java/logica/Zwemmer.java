package logica;

import java.util.ArrayList;

/**
 * Project_OODB_ThibaultViaene_0.1 : Zwemmers
 *
 * @author viaen
 * @version 28/05/2023
 */
public class Zwemmer extends Persoon {
    private int finapunten;
    private ArrayList<Deelname> deelNames;

    public Zwemmer(Adres adres, String voornaam, String achternaam, int geboorteJaar, Geslacht geslacht, String licentieNummer, String club, int finapunten, ArrayList<Deelname> deelNames) {
        super(adres, voornaam, achternaam, geboorteJaar, geslacht, licentieNummer, club);
        this.finapunten = finapunten;
        this.deelNames = deelNames;
    }

    public Zwemmer(Adres adres, String voornaam, String achternaam, int geboorteJaar, Geslacht geslacht, String licentieNummer, String club, int finapunten) {
        super(adres, voornaam, achternaam, geboorteJaar, geslacht, licentieNummer, club);
        this.finapunten = finapunten;
    }

    public int getFinapunten() {
        return finapunten;
    }

    public void setFinapunten(int finapunten) {
        this.finapunten = finapunten;
    }

    public ArrayList<Deelname> getDeelNames() {
        return deelNames;
    }

    public void setDeelNames(ArrayList<Deelname> deelNames) {
        this.deelNames = deelNames;
    }
}
