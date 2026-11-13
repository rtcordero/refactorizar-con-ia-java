package aparcamiento;

public class Tarifa {

    // calcula lo que paga un vehiculo en centimos
    public int calcula(int min, String t, boolean res) {
        int p = 0;
        if (min <= 15) {
            return 0;
        } else {
            int h = min / 60;
            if (min % 60 > 0) {
                h++;
            }
            if (t.equals("COCHE")) {
                p = h * 250;
                if (p > 2000) {
                    p = 2000;
                }
                if (res) {
                    p = p - p * 20 / 100;
                }
            } else {
                if (t.equals("FURGO")) {
                    p = h * 350;
                    if (p > 2800) {
                        p = 2800;
                    }
                } else if (t.equals("MOTO")) {
                    p = h * 100;
                    if (p > 800) {
                        p = 800;
                    }
                }
            }
        }
        return p;
    }
}
