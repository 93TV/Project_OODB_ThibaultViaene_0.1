package logica;

/**
 * Project_OODB_ThibaultViaene_0.1 : officials
 *
 * @author viaen
 * @version 28/05/2023
 */
public class Official {
    int id;
    private boolean camprechter;
    private boolean jurysecretaris;
    private boolean starter;
    private boolean tak;
    private String functie;

    public Official(int id, String functie) {
        this.id = id;
        this.functie = functie;
    }

    public Official(int id, boolean camprechter, boolean jurysecretaris, boolean starter, boolean tak) {
        this.id = id;
        this.camprechter = camprechter;
        this.jurysecretaris = jurysecretaris;
        this.starter = starter;
        this.tak = tak;
    }

    public int getId() {
        return id;
    }

    public String getFunctie() {
        return functie;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Official ID: " + id + " - Functie: " + functie;
    }
}
