package test;

import data.DataLaag;
import logica.Adres;
import logica.Wedstrijd;
import logica.Zwembad;

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
        for (Wedstrijd wed : dl.geefWedstrijdNaamEnId()){
            System.out.println(wed);

        }


        for (Zwembad zwb : dl.geefZwembadenNaamEnId()){
            System.out.println(zwb);
        }


    }
}
