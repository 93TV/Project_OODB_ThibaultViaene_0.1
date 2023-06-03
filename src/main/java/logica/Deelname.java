package logica;

import java.sql.Time;

/**
 * Project_OODB_ThibaultViaene_0.1 : deelnames
 *
 * @author viaen
 * @version 28/05/2023
 */
public class Deelname {
    private int zwemmerId;
    private int serieId;
    private int baan;
    private Time resultaat;


    public Deelname(int zwemmerId, int serieId, int baan) {
        this.zwemmerId = zwemmerId;
        this.serieId = serieId;
        this.baan = baan;
    }

    public Deelname(int zwemmerId, int baan, Time resultaat, int serieId) {
        this.zwemmerId = zwemmerId;
        this.baan = baan;
        this.resultaat = resultaat;
        this.serieId = serieId;
    }

    public int getZwemmerId() {
        return zwemmerId;
    }

    public int getSerieId() {
        return serieId;
    }

    public int getBaan() {
        return baan;
    }


    @Override
    public String toString() {
        return "Zwemmer_ID: " + zwemmerId + " / Serie: " + serieId + " / baan : " + baan + " / resultaat : " + resultaat;
    }
}
