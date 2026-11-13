package aparcamiento;

import java.util.Arrays;
import java.util.Optional;

/** Precio por hora, tope por estancia y descuento de residente de cada vehículo, en céntimos. */
enum Vehiculo {
    COCHE(250, 2000, 20),
    // El nombre es el texto que llega de fuera: no se cambia a FURGONETA sin cambiar a quien llama.
    FURGO(350, 2800, 0),
    MOTO(100, 800, 0);

    private final int precioPorHora;
    private final int topePorEstancia;
    private final int descuentoResidente;

    Vehiculo(int precioPorHora, int topePorEstancia, int descuentoResidente) {
        this.precioPorHora = precioPorHora;
        this.topePorEstancia = topePorEstancia;
        this.descuentoResidente = descuentoResidente;
    }

    static Optional<Vehiculo> buscar(String tipo) {
        return Arrays.stream(values())
                .filter(vehiculo -> tipo.equals(vehiculo.name()))
                .findFirst();
    }

    int importe(int horas, boolean residente) {
        int importe = Math.min(horas * precioPorHora, topePorEstancia);
        if (residente) {
            importe -= importe * descuentoResidente / 100;
        }
        return importe;
    }
}
