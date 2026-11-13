package aparcamiento;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests de caracterización: fijan lo que Tarifa hace hoy, no lo que debería hacer.
 * Los importes van en céntimos.
 */
class TarifaTest {

    private final Tarifa tarifa = new Tarifa();

    @Test
    void los_primeros_15_minutos_son_gratis_incluido_el_minuto_15() {
        assertEquals(0, tarifa.calcula(15, "COCHE", false));
    }

    @Test
    void el_minuto_16_ya_cobra_una_hora_entera() {
        assertEquals(250, tarifa.calcula(16, "COCHE", false));
    }

    @Test
    void una_hora_justa_cobra_una_hora() {
        assertEquals(250, tarifa.calcula(60, "COCHE", false));
    }

    @Test
    void cada_hora_empezada_se_cobra_entera() {
        assertEquals(500, tarifa.calcula(61, "COCHE", false));
    }

    @Test
    void el_coche_no_paga_mas_de_20_euros_por_estancia() {
        assertEquals(2000, tarifa.calcula(3 * 24 * 60, "COCHE", false));
    }

    @Test
    void el_coche_de_residente_tiene_un_20_por_ciento_de_descuento() {
        assertEquals(400, tarifa.calcula(120, "COCHE", true));
    }

    @Test
    void el_descuento_de_residente_se_aplica_despues_del_tope() {
        assertEquals(1600, tarifa.calcula(10 * 60, "COCHE", true));
    }

    @Test
    void la_furgoneta_paga_3_euros_50_por_hora() {
        assertEquals(700, tarifa.calcula(120, "FURGO", false));
    }

    @Test
    void la_furgoneta_no_paga_mas_de_28_euros_por_estancia() {
        assertEquals(2800, tarifa.calcula(10 * 60, "FURGO", false));
    }

    @Test
    void la_furgoneta_de_residente_no_tiene_descuento() {
        assertEquals(700, tarifa.calcula(120, "FURGO", true));
    }

    @Test
    void un_tipo_desconocido_no_paga() {
        assertEquals(0, tarifa.calcula(120, "BICI", false));
    }

    @Test
    void el_tipo_distingue_mayusculas() {
        assertEquals(0, tarifa.calcula(120, "coche", false));
    }

    @Test
    void la_moto_paga_1_euro_por_hora() {
        assertEquals(200, tarifa.calcula(120, "MOTO", false));
    }

    @Test
    void la_moto_no_paga_mas_de_8_euros_por_estancia() {
        assertEquals(800, tarifa.calcula(10 * 60, "MOTO", false));
    }

    @Test
    void la_moto_de_residente_no_tiene_descuento() {
        assertEquals(200, tarifa.calcula(120, "MOTO", true));
    }
}
