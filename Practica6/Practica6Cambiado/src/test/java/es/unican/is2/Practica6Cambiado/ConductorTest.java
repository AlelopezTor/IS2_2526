package es.unican.is2.Practica6Cambiado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ConductorTest {
    TransporteMercancias transporteMercancias = new TransporteMercancias(10, 11, CategoriaTransporte.MERCANCIAS);
    TransporteMercancias transporteMercanciasPeligrosas = new TransporteMercancias(10, 11, CategoriaTransporte.MERCANCIAS_PELIGROSAS);
    Conductor conductor = new Conductor("123455", "Loto", "Lopez-Tormos", "Gervas", "aaaa");
    @Test
    public void testConstructor() {

        // Casos validos
        assertEquals("123455", conductor.getDni());
        assertEquals("Loto", conductor.getNombre());
        assertEquals("Lopez-Tormos", conductor.getApellido1());
        assertEquals("Gervas", conductor.getApellido2());
        assertEquals("aaaa", conductor.getDireccion());
       
        // Casos no validos
        assertThrows(IllegalArgumentException.class, () -> new Conductor(null, "Loto", "Lopez-Tormos", "Gervas", "aaaa"));
        assertThrows(IllegalArgumentException.class, () -> new Conductor("123455", null, "Lopez-Tormos", "Gervas", "aaaa"));
        assertThrows(IllegalArgumentException.class, () -> new Conductor("123455", "Loto", null, "Gervas", "aaaa"));
        assertThrows(IllegalArgumentException.class, () -> new Conductor("123455", "Loto", "Lopez-Tormos", "Gervas", null));
    }
    @Test
    public void testSueldo() {
    	conductor.anhadeTransporte(transporteMercancias);
    	conductor.anhadeTransporte(transporteMercanciasPeligrosas);
    	assertEquals(894, conductor.sueldo());

    }

}
