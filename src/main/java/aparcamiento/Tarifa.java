package aparcamiento;

public class Tarifa {

    // calcula lo que paga un vehiculo en centimos
    public int calcula(int minutos, String tipo, boolean residente) {
        if (minutos <= 15) {
            return 0;
        }
        int importe = 0;
        int horas = minutos / 60;
        if (minutos % 60 > 0) {
            horas++;
        }
        if (tipo.equals("COCHE")) {
            importe = horas * 250;
            if (importe > 2000) {
                importe = 2000;
            }
            if (residente) {
                importe = importe - importe * 20 / 100;
            }
        } else if (tipo.equals("FURGO")) {
            importe = horas * 350;
            if (importe > 2800) {
                importe = 2800;
            }
        } else if (tipo.equals("MOTO")) {
            importe = horas * 100;
            if (importe > 800) {
                importe = 800;
            }
        }
        return importe;
    }
}
