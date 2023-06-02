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
    private String uitsluitingsCode;
    private boolean forfait;

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

    public void setZwemmerId(int zwemmerId) {
        this.zwemmerId = zwemmerId;
    }

    public int getSerieId() {
        return serieId;
    }

    public void setSerieId(int serieId) {
        this.serieId = serieId;
    }

    public int getBaan() {
        return baan;
    }

    public void setBaan(int baan) {
        this.baan = baan;
    }

    public Time getResultaat() {
        return resultaat;
    }

    public void setResultaat(Time resultaat) {
        this.resultaat = resultaat;
    }

    public String getUitsluitingsCode() {
        return uitsluitingsCode;
    }

    public void setUitsluitingsCode(String uitsluitingsCode) {
        this.uitsluitingsCode = uitsluitingsCode;
    }

    public boolean isForfait() {
        return forfait;
    }

    public void setForfait(boolean forfait) {
        this.forfait = forfait;
    }

    @Override
    public String toString() {
        return "Zwemmer_ID: " + zwemmerId + " / Serie: " + serieId + " / baan : " + baan + " / resultaat : " + resultaat;
    }
}
