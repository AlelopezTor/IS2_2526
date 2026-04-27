package es.unican.is2.SegurosCommon;

import static org.junit.jupiter.api.Assertions.*;
 
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
public class ClienteTest {
    private Cliente clienteConMinusvalia;
    private Cliente clienteSinMinusvalia;
 
    private Seguro s1;
    private Seguro s2;
    private Seguro s3;
    private Seguro s4;
 
    @BeforeEach
    public void setUp() {
        clienteConMinusvalia  = new Cliente("12345678B", "Alejandro", true);
        clienteSinMinusvalia  = new Cliente("87654321Z", "Pepe", false);
 
        s1= new Seguro("1111AAA", 0, Cobertura.TODO_RIESGO, LocalDate.now());
        s2 = new Seguro("1111BBB", 44, Cobertura.TERCEROS_LUNAS, LocalDate.now());
        s3 = new Seguro("1111CCC", 105, Cobertura.TERCEROS, LocalDate.now());
        s4 = new Seguro("2222AAA", 44, Cobertura.TERCEROS_LUNAS, LocalDate.now());
    }

    @Test
    public void testConstructor() {
 
        Cliente clienteMinusvalía  = new Cliente("12345678B", "Alejandro", true);
        assertEquals("12345678B", clienteMinusvalía.getDni());
        assertTrue(clienteMinusvalía.getMinusvalia());
        Cliente clienteSinMinusvalia  = new Cliente("12345678B", "Alejandro", false);
        assertFalse(clienteSinMinusvalia.getMinusvalia());
 
        assertThrows(IllegalArgumentException.class, () -> new Cliente("aaaa1234", "Alejandro", true));
        assertThrows(IllegalArgumentException.class, () -> new Cliente("1111111111", "Alejandro", true));
 
        assertThrows(NullPointerException.class, () -> new Cliente(null, "Ana", true));
        assertThrows(NullPointerException.class, () -> new Cliente("12345678B", null, true));
        
        new Cliente();
    }
 
    @Test
    public void testTotalSeguros() {

        List<Seguro> tresSegurosMinus = Arrays.asList(s1, s2, s3);
        clienteConMinusvalia.setSeguros(tresSegurosMinus);
        assertEquals(1212, clienteConMinusvalia.totalSeguros());
        List<Seguro> unSeguro = Arrays.asList(s4);
        clienteSinMinusvalia.setSeguros(unSeguro);
        assertEquals(480, clienteSinMinusvalia.totalSeguros());
 
        Cliente clienteFallo = new Cliente("12345678E", "Alejandro", true);
        assertEquals(0, clienteFallo.totalSeguros());
 
        List<Seguro> conNull = Arrays.asList(s1, null, s3);
        clienteFallo.setSeguros(conNull);
        assertThrows(NullPointerException.class, () -> clienteFallo.totalSeguros());
        
        clienteFallo.setSeguros(null);
        assertThrows(NullPointerException.class, () -> clienteFallo.totalSeguros());
    }
    
    @SuppressWarnings("unlikely-arg-type")
	@Test
    public void testEquals() {
        Cliente mismoDni = new Cliente("12345678B", "Otro nombre", false);
        assertTrue(clienteConMinusvalia.equals(mismoDni));
        assertFalse(clienteConMinusvalia.equals("aaaaaa"));
        Cliente distintoDni = new Cliente("87654321Z", "Alejandro", true);
        assertFalse(clienteConMinusvalia.equals(distintoDni));
        assertFalse(clienteConMinusvalia.equals(null));
        //Cliente nullDni = new Cliente(null, "Alejandro", true); Da excepción y no llega a más
        //assertFalse(clienteConMinusvalia.equals(nullDni));
        
        assertTrue(clienteConMinusvalia.equals(clienteConMinusvalia));

    }
 
    @Test
    public void testHashCode() { 
        Cliente c1 = new Cliente("12345678B", "Alejandro", true);
        Cliente c2 = new Cliente("12345678B", "Otro",      false);
        assertEquals(c1.hashCode(), c2.hashCode());
 
        Cliente c3 = new Cliente("87654321Z", "Pepe", false);
        assertNotEquals(c1.hashCode(), c3.hashCode());
    }
    
}