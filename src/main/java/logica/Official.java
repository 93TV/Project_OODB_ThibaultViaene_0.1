package logica;

/**
 * Project_OODB_ThibaultViaene_0.1 : officials
 *
 * @author viaen
 * @version 28/05/2023
 */
public class Official {
    int id;
    private String functie;
    private String naam;
    private String voornaam;

    public Official(int id, String functie) {
        this.id = id;
        this.functie = functie;
    }

    public Official(int oId, String naam, String voornaam) {
        this.id = oId;
        this.naam = naam;
        this.voornaam = voornaam;
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
    public String toString2() {
        return "Official ID: " + id + " " + naam + " " + voornaam;
    }
}
