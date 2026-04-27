package es.unican.is2.SegurosGUI;
import java.sql.Connection;

import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;

import es.unican.is2.SegurosBusiness.GestionSeguros;
import es.unican.is2.SegurosCommon.DataAccessException;
import es.unican.is2.SegurosCommon.IClientesDAO;
import es.unican.is2.SegurosCommon.ISegurosDAO;
import es.unican.is2.SegurosDAOH2.ClientesDAO;
import es.unican.is2.SegurosDAOH2.H2ServerConnectionManager;
import es.unican.is2.SegurosDAOH2.SegurosDAO;
public class VistaAgenteTestIT {
	
	private FrameFixture demo;
    private static Connection connectionDDBB;

	@BeforeEach
	public void setUp() {
	    System.out.println("Directorio de trabajo: " + System.getProperty("user.dir"));

		IClientesDAO daoClientes = new ClientesDAO();
		ISegurosDAO daoSeguros = new SegurosDAO();
		GestionSeguros negocio = new GestionSeguros(daoClientes, daoSeguros);
		VistaAgente gui = new VistaAgente(negocio, negocio, negocio);
		demo = new FrameFixture(gui);
		gui.setVisible(true);	
		
	}

    @BeforeAll
    public static void setUpDDBB() {
    	try {
    		connectionDDBB = H2ServerConnectionManager.getConnection();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
    }
	@AfterEach
	public void tearDown() {
		demo.cleanUp();
	}
	
	@Test
	public void testClienteConSeguros() {
				
		demo.textBox("txtDNICliente").enterText("11111111A");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreCliente").requireText("Juan");
		demo.textBox("txtTotalCliente").requireText("1820.0");
	}
	
	@Test
	public void testClienteSinSeguro() {
		demo.textBox("txtDNICliente").enterText("33333333A");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreCliente").requireText("Luis");
		demo.textBox("txtTotalCliente").requireText("0.0");
	   }
	@Test
	public void testClienteUnSeguro() {
		demo.textBox("txtDNICliente").enterText("22222222A");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreCliente").requireText("Ana");
		demo.textBox("txtTotalCliente").requireText("600.0");
	   }
	@Test
	public void testClienteNoExistente() {
		demo.textBox("txtDNICliente").enterText("00000000A");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreCliente").requireText("Error en BBDD");
	}
	
	@Test
	@Order(5)
	public void testErroDataBase() throws Exception {
	    connectionDDBB.close();
	    
	    demo.textBox("txtDNICliente").enterText("11111111A");
	    demo.button("btnBuscar").click();
	    demo.textBox("txtNombreCliente").requireText("Error en BBDD");
	    demo.textBox("txtTotalCliente").requireText("");
	}
}
