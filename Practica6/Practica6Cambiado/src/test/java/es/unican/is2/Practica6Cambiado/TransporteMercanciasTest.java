package es.unican.is2.Practica6Cambiado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TransporteMercanciasTest {
    TransporteMercancias transporteMercancias = new TransporteMercancias(10, 11, CategoriaTransporte.MERCANCIAS);
    TransporteMercancias transporteMercanciasPeligrosas = new TransporteMercancias(10, 11, CategoriaTransporte.MERCANCIAS_PELIGROSAS);

    @Test
    public void testConstructor() {

        // Casos validos
        assertEquals(10, transporteMercancias.getHoras());
        assertEquals(CategoriaTransporte.MERCANCIAS, transporteMercancias.getCategoria());
        assertEquals(11, transporteMercancias.getToneladas());

        // Casos no validos
        assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(-1, 10, CategoriaTransporte.MERCANCIAS));
        assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(1, -10, CategoriaTransporte.MERCANCIAS));

    
	    assertEquals(10, transporteMercanciasPeligrosas.getHoras());
	    assertEquals(CategoriaTransporte.MERCANCIAS_PELIGROSAS, transporteMercanciasPeligrosas.getCategoria());
	    assertEquals(11, transporteMercanciasPeligrosas.getToneladas());
	
	    // Casos no validos
	    assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(-1, 10, CategoriaTransporte.MERCANCIAS_PELIGROSAS));
	    assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(1, -10, CategoriaTransporte.MERCANCIAS_PELIGROSAS));

}
    @Test
    public void testSueldoExtra() {
    	
    	assertEquals(22,transporteMercancias.sueldoExtra());
    	assertEquals(72, transporteMercanciasPeligrosas.sueldoExtra());

    }

}
