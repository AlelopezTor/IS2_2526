package es.unican.is2.AbstractDataTypes;

import static org.junit.jupiter.api.Assertions.*;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;

public class ConjuntoOrdenadoTest {
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
    	assertEquals(4, listaMuchosElementos.get(2));
    	assertEquals(110, listaMuchosElementos.get(4));
    	
        assertThrows(IndexOutOfBoundsException.class, () -> listaUnElemento.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> listaUnElemento.get(-200));
        assertThrows(IndexOutOfBoundsException.class, () -> listaMuchosElementos.get(5));
        assertThrows(IndexOutOfBoundsException.class, () -> listaMuchosElementos.get(200));
        assertThrows(IndexOutOfBoundsException.class, () -> listaVacia.get(1));

    }
    @Test
    @Order(3)
    public void testAdd() {
    	assertFalse(listaUnElemento.add(1));
    	
    	listaUnElemento.add(2);
    	assertEquals(2 ,listaUnElemento.get(1));
    	
    	listaMuchosElementos.add(3);
    	assertEquals(3 ,listaMuchosElementos.get(2));

    	listaVacia.add(1); 
    	assertEquals(1 ,listaVacia.get(0));
        assertThrows(NullPointerException.class, () -> listaUnElemento.add(null));

    }
    
    @Test
    @Order(4)
    public void testRemove() {
    	assertEquals(1, listaUnElemento.remove(0));
    	assertEquals(4, listaMuchosElementos.remove(2));
    	assertEquals(110, listaMuchosElementos.remove(3));
    	
        assertThrows(IndexOutOfBoundsException.class, () -> listaUnElemento.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> listaUnElemento.remove(-200));
        assertThrows(IndexOutOfBoundsException.class, () -> listaMuchosElementos.remove(5));
        assertThrows(IndexOutOfBoundsException.class, () -> listaMuchosElementos.remove(200));
        assertThrows(IndexOutOfBoundsException.class, () -> listaVacia.remove(1));

    }
    
	@Test
    @Order(2)
    public void testSize() {
		assertEquals(1 ,listaUnElemento.size());
		assertEquals(5 ,listaMuchosElementos.size());
		assertEquals(0 ,listaVacia.size());

    }
 
    @Test
    @Order(5)
    public void testClear() { 
    	listaUnElemento.clear();
    	listaVacia.clear();
    	listaMuchosElementos.clear();
    	assertEquals(0 ,listaUnElemento.size());
    	assertEquals(0 ,listaMuchosElementos.size());
    	assertEquals(0 ,listaVacia.size());
    	
    }
    
}