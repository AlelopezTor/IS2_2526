package es.unican.is2.AbstractDataTypes;

import static org.junit.jupiter.api.Assertions.*;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;

public class ConjuntoOrdenadoBlancaTest {
    ConjuntoOrdenado<Integer> listaVacia;
    ConjuntoOrdenado<Integer> listaUnElemento;
    ConjuntoOrdenado<Integer> listaMuchosElementos;

    @BeforeEach
    public void setUp() {
    	listaVacia = new ConjuntoOrdenado<Integer>();
    	listaUnElemento = new ConjuntoOrdenado<Integer>();
        listaUnElemento.add(1);
    	listaMuchosElementos = new ConjuntoOrdenado<Integer>();
    	listaMuchosElementos.add(1);
    	listaMuchosElementos.add(2);
    	listaMuchosElementos.add(4);
    	listaMuchosElementos.add(8);
    	listaMuchosElementos.add(110);

    	
    }

    @Test
    @Order(1)
    public void testGet() {
    	assertEquals(1, listaUnElemento.get(0));

    }
    @Test
    @Order(3)
    public void testAdd() {
        assertThrows(NullPointerException.class, () -> listaUnElemento.add(null));
    	assertTrue(listaVacia.add(3));
    	listaMuchosElementos.add(3);
    	assertEquals(3 ,listaMuchosElementos.get(2));
    	assertFalse(listaUnElemento.add(1));
    	listaMuchosElementos.add(0);
    	assertEquals(0 ,listaMuchosElementos.get(0));
    	listaMuchosElementos.add(1000);
    	assertEquals(1000 ,listaMuchosElementos.get(7));
    	listaUnElemento.add(3);
    	assertEquals(3 ,listaUnElemento.get(1));


    }
    
    @Test
    @Order(4)
    public void testRemove() {
    	assertEquals(1, listaUnElemento.remove(0));

    }
    
	@Test
    @Order(2)
    public void testSize() {
		assertEquals(5 ,listaMuchosElementos.size());
    }
 
    @Test
    @Order(5)
    public void testClear() { 
    	listaMuchosElementos.clear();
    	assertEquals(0 ,listaMuchosElementos.size());
    	
    }
    
}