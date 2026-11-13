package aparcamiento;

public class Tarifa {

    private static final int MINUTOS_GRATIS = 15;
    private static final int MINUTOS_POR_HORA = 60;

    // calcula lo que paga un vehiculo en centimos
    public int calcula(int minutos, String tipo, boolean residente) {
        if (minutos <= MINUTOS_GRATIS) {
            return 0;
        }
        return Vehiculo.buscar(tipo)
                .map(vehiculo -> vehiculo.importe(
                        horasEmpezadas(minutos), residente))
                .orElse(0);
    }

    private static int horasEmpezadas(int minutos) {
        return Math.ceilDiv(minutos, MINUTOS_POR_HORA);
    }
}
