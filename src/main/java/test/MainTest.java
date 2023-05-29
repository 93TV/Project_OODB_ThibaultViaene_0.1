package test;

import data.DataLaag;

import java.sql.SQLException;

/**
 * Project_OODB_ThibaultViaene_0.1 : MainTest
 *
 * @author viaen
 * @version 29/05/2023
 */
public class MainTest {
    public static void main(String[] args) throws SQLException {
        DataLaag dl = new DataLaag();
        for (Dvd dvd : dl.geefDvdLijst()) {
            System.out.println(dvd);
        }
        Klant Luca = new Klant("luca", "vlakdaar 6", 9000, "Gent", new java.sql.Date(System.currentTimeMillis()), "0498844674");
        System.out.println("voeg klant toe");
        System.out.println(dl.maakKlantAan(Luca));
        System.out.println("doe ontlening");
//        dl.doeOntlening(Luca, 101);
        for (Dvd dvd : dl.geefDvdLijst()) {
            System.out.println(dvd);
        }

    }
}
}
