package aparcamiento;

public class Tarifa {

    private static final int MINUTOS_GRATIS = 15;
    private static final int MINUTOS_POR_HORA = 60;

    // calcula lo que paga un vehiculo en centimos
    public int calcula(int minutos, String tipo, boolean residente) {
        if (minutos <= MINUTOS_GRATIS) {
            return 0;
        }
        int importe = 0;
        int horas = horasEmpezadas(minutos);
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

    private static int horasEmpezadas(int minutos) {
        return minutos / MINUTOS_POR_HORA + 1;
    }
}
