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

}
