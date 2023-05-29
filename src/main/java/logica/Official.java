package logica;

/**
 * Project_OODB_ThibaultViaene_0.1 : officials
 *
 * @author viaen
 * @version 28/05/2023
 */
public class Official extends Persoon {
    private boolean camprechter;
    private boolean jurysecretaris;
    private boolean starter;
    private boolean tak;



    public Official(Adres adres, String voornaam, String achternaam, int geboorteJaar, Geslacht geslacht, String licentieNummer, String club) {
        super(adres, voornaam, achternaam, geboorteJaar, geslacht, licentieNummer, club);
    }

    public boolean isCamprechter() {
        return camprechter;
    }

    public void setCamprechter(boolean camprechter) {
        this.camprechter = camprechter;
    }

    public boolean isJurysecretaris() {
        return jurysecretaris;
    }

    public void setJurysecretaris(boolean jurysecretaris) {
        this.jurysecretaris = jurysecretaris;
    }

    public boolean isStarter() {
        return starter;
    }

    public void setStarter(boolean starter) {
        this.starter = starter;
    }

    public boolean isTak() {
        return tak;
    }

    public void setTak(boolean tak) {
        this.tak = tak;
    }
}
