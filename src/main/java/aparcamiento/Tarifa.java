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
            importe = Vehiculo.COCHE.importe(horas, residente);
        } else if (tipo.equals("FURGO")) {
            importe = Vehiculo.FURGO.importe(horas, residente);
        } else if (tipo.equals("MOTO")) {
            importe = Vehiculo.MOTO.importe(horas, residente);
        }
        return importe;
    }

    private static int horasEmpezadas(int minutos) {
        return Math.ceilDiv(minutos, MINUTOS_POR_HORA);
    }
}
