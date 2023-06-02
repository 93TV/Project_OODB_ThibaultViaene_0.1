package logica;

public class Helper {

    public static Lengte lengteNaarEnum(int lengte) {
        if (lengte == 25) return Lengte._25;
        if (lengte == 50) return Lengte._50;
        return null;
    }

    public static AantalBanen aanntalBanenNaarEnum(int aantalBanen) {
        if (aantalBanen == 4) return AantalBanen._4;
        if (aantalBanen == 5) return AantalBanen._5;
        if (aantalBanen == 6) return AantalBanen._6;
        if (aantalBanen == 8) return AantalBanen._8;
        if (aantalBanen == 10) return AantalBanen._10;
        return null;
    }

    public static String leeftijdsCategorie(LeeftijdsCategorie l) {
        String lc = "";
        if (l.equals(LeeftijdsCategorie._9_10)) lc = "9-10";
        if (l.equals(LeeftijdsCategorie._11_12)) lc = "11-12";
        if (l.equals(LeeftijdsCategorie._13_14)) lc = "13-14";
        if (l.equals(LeeftijdsCategorie._15_16)) lc = "15-16";
        if (l.equals(LeeftijdsCategorie._17_18)) lc = "17_18";
        if (l.equals(LeeftijdsCategorie._11plus)) lc = "11+";
        return lc;
    }

    public static LeeftijdsCategorie reverseLeeftijd(String l) {
        LeeftijdsCategorie lc = null;
        if (l.equals("9-10")) lc = LeeftijdsCategorie._9_10;
        if (l.equals("11-12")) lc = LeeftijdsCategorie._11_12;
        if (l.equals("13-14")) lc = LeeftijdsCategorie._13_14;
        if (l.equals("15-16")) lc = LeeftijdsCategorie._15_16 ;
        if (l.equals("17_18")) lc = LeeftijdsCategorie._17_18;
        if (l.equals("11+")) lc = LeeftijdsCategorie._11plus;
        return lc;
    }
}


