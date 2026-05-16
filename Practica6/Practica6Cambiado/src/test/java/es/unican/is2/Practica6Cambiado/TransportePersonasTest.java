package es.unican.is2.Practica6Cambiado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TransportePersonasTest {
    TransportePersonas transporte = new TransportePersonas(10, 1);
    TransportePersonas transporteConMasPersonal = new TransportePersonas(10, 10);

    @Test
    public void testConstructor() {

        // Casos validos
        assertEquals(10, transporte.getHoras());
        assertEquals(CategoriaTransporte.PERSONAS, transporte.getCategoria());
        assertEquals(1, transporte.getPersonas());

        // Casos no validos
        assertThrows(IllegalArgumentException.class, () -> new TransportePersonas(-1, 10));
        assertThrows(IllegalArgumentException.class, () -> new TransportePersonas(1, -10));

    }
    @Test
    public void testSueldoExtra() {
    	assertEquals(5,transporte.sueldoExtra());
    	assertEquals(10,transporteConMasPersonal.sueldoExtra());

    }

}
