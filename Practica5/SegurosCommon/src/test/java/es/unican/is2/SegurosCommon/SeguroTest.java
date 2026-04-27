package es.unican.is2.SegurosCommon;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SeguroTest {

    private Seguro seguroTest;

    @BeforeEach
    public void setUp() {
        this.seguroTest = new Seguro("1111AAA", 0, Cobertura.TODO_RIESGO, LocalDate.now());
    }

    @Test
    public void testConstructor() {
        Seguro s1 = new Seguro("1111AAA", 0, Cobertura.TODO_RIESGO, LocalDate.now());
        assertEquals("1111AAA", s1.getMatricula());
        assertEquals(0, s1.getPotencia());
        assertEquals(Cobertura.TODO_RIESGO, s1.getCobertura());
        assertEquals(LocalDate.now(), s1.getFechaInicio());
        new Seguro("2222BBB", 44, Cobertura.TERCEROS_LUNAS,
                LocalDate.now().plusDays(1));       
        new Seguro("2222BBB", 200, Cobertura.TERCEROS,
                   LocalDate.now().plusYears(1));

        
        assertThrows(IllegalArgumentException.class, () -> new Seguro("Azps123", 44, Cobertura.TERCEROS, LocalDate.now()));
        assertThrows(NullPointerException.class, () -> new Seguro(null, 44, Cobertura.TERCEROS, LocalDate.now()));
        assertThrows(IllegalArgumentException.class, () -> new Seguro("1111AAA", -1, Cobertura.TERCEROS, LocalDate.now()));
        assertThrows(NullPointerException.class, () -> new Seguro("1111AAA", 44, null, LocalDate.now()));
        assertThrows(NullPointerException.class, () -> new Seguro("1111AAA", 44, Cobertura.TERCEROS, null));
        assertThrows(IllegalArgumentException.class, () -> new Seguro("1111AAA", 44, Cobertura.TERCEROS, LocalDate.now().minusDays(1)));
        assertThrows(IllegalArgumentException.class, () -> new Seguro("1111AAA", 44, Cobertura.TERCEROS, LocalDate.now().minusYears(1)));
        new Seguro();
        }

    @Test
    public void testPrecio() {
        Seguro s1 = new Seguro("1111AAA", 0, Cobertura.TODO_RIESGO, LocalDate.now());
        assertEquals(800, s1.precio());
        Seguro s2 = new Seguro("2222BBB", 110, Cobertura.TODO_RIESGO, LocalDate.now().plusDays(1));
        assertEquals(0, s2.precio());
        Seguro s3 = new Seguro("3333CCC", 111, Cobertura.TODO_RIESGO, LocalDate.now().plusMonths(6));
        assertEquals(0, s3.precio());
        
        
        Seguro s4 = new Seguro("4444DDD", 44, Cobertura.TERCEROS, LocalDate.now());
        assertEquals(320, s4.precio());
        Seguro s5 = new Seguro("5555EEE", 44, Cobertura.TERCEROS_LUNAS, LocalDate.now());
        assertEquals(480, s5.precio());
        Seguro s6 = new Seguro("6666FFF", 90, Cobertura.TERCEROS, LocalDate.now());
        assertEquals(336, s6.precio());
        Seguro s7 = new Seguro("1343GGG", 100, Cobertura.TERCEROS, LocalDate.now());
        assertEquals(336, s7.precio());
        Seguro s8 = new Seguro("5642HHH", 110, Cobertura.TERCEROS, LocalDate.now());
        assertEquals(336, s8.precio());
        Seguro s9 = new Seguro("8977III", 111, Cobertura.TERCEROS, LocalDate.now());
        assertEquals(384, s9.precio());
        Seguro s10 = new Seguro("9234JJJ", 200, Cobertura.TERCEROS, LocalDate.now());
        assertEquals(384, s10.precio());

    }

    @Test
    public void testEquals() {

        Seguro mismaMatricula = new Seguro("1111AAA", 200, Cobertura.TERCEROS, LocalDate.now());
        assertTrue(seguroTest.equals(mismaMatricula));
        assertFalse(seguroTest.equals("aaaaaa"));
        Seguro distintoMatricula = new Seguro("2222BBB", 0, Cobertura.TODO_RIESGO, LocalDate.now());
        assertFalse(seguroTest.equals(distintoMatricula));
        assertFalse(seguroTest.equals(null));
        assertTrue(mismaMatricula.equals(mismaMatricula));

    }


    @Test
    public void testHashCode() {

        Seguro s1 = new Seguro("1111AAA", 0,  Cobertura.TODO_RIESGO, LocalDate.now());
        Seguro s2 = new Seguro("1111AAA", 50, Cobertura.TERCEROS, LocalDate.now().plusMonths(1));
        assertEquals(s1.hashCode(), s2.hashCode());
        Seguro s3 = new Seguro("2222BBB", 0, Cobertura.TODO_RIESGO, LocalDate.now());
        assertNotEquals(s1.hashCode(), s3.hashCode());
    }
}