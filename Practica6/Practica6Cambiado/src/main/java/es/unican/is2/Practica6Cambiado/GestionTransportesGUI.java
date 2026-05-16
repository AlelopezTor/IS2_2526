package es.unican.is2.Practica6Cambiado;

import fundamentos.*;

/**
 * Gestion de una empresa de transportes
 */
public class GestionTransportesGUI { 
	 private static final int ANHADE_CONDUCTOR = 0, ANHADE_TRANSPORTE = 1, 
				SUELDO_CONDUCTOR = 2, MEJOR_CONDUCTOR = 3, SALIR = 4;
	 private static final String CAMPO_DNI = "DNI";
	/**
	 * Programa principal basado en menu
	 */
	public static void main(String[] args) { // WMC 1
		// opciones del menu

		// crea la empresa de transportes
		GestionTransportes gestionTransportes = new GestionTransportes();
		// crea la ventana de menu
		Menu menu = new Menu("Transportes");
		menu.insertaOpcion("Anhade conductor", ANHADE_CONDUCTOR);
		menu.insertaOpcion("Anhade transporte", ANHADE_TRANSPORTE);
		menu.insertaOpcion("Sueldo conductor", SUELDO_CONDUCTOR);
		menu.insertaOpcion("Mejor conductor", MEJOR_CONDUCTOR);
		menu.insertaOpcion("Salir", SALIR);

		
		int opcion;

		// lazo de espera de comandos del usuario
		while(true) { // WMC 1 CCog 1
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) { // CCog 2
			case  ANHADE_CONDUCTOR: // WMC 1
				mensaje ("RESULTADO", anhadeConductor(gestionTransportes));
				break;

			case ANHADE_TRANSPORTE: // WMC 1
				mensaje("RESULTADO", anhadeTransporte(gestionTransportes));
				break;
				
			case SUELDO_CONDUCTOR: // WMC 1
				mensaje("RESULTADO", sueldoConductor(gestionTransportes));
 				break;

			case MEJOR_CONDUCTOR: // WMC 1
				mensaje("MEJOR CONDUCTOR", gestionTransportes.mejorConductor());
				break;
			case SALIR: // WMC 1
				return;
			default: // WMC 1
				break;
			}
		}
	}



	private static String sueldoConductor(GestionTransportes gestionTransportes) { // WMC 1

		Lectura lectura = new Lectura("Transportes Peligrosos");
		lectura.creaEntrada(CAMPO_DNI, "");
		lectura.esperaYCierra();
		return gestionTransportes.getSueldoConductor(lectura.leeString("DNI"));
	}

	private static String anhadeTransporte(GestionTransportes gestionTransportes) { // WMC 1
		
		Lectura lectura = new Lectura("Nuevo transporte");
		lectura.creaEntrada(CAMPO_DNI, "");
		lectura.creaEntrada("Tipo Transporte: P | M | MP", "");
		lectura.creaEntrada("Horas", 0);
		lectura.creaEntrada("Personas", 0);
		lectura.creaEntrada("Toneladas", 0);
		lectura.esperaYCierra();
		CategoriaTransporte tipo = CategoriaTransporte.desdeCodigo(lectura.leeString("Tipo Transporte: P | M | MP"));
		return gestionTransportes.crearTransporte(lectura.leeInt("Horas"), lectura.leeInt("Personas"), lectura.leeInt("Toneladas"), tipo, lectura.leeString("DNI"));
	}


	private static String anhadeConductor(GestionTransportes gestionTransportes) { // WMC 1
		
		Lectura lectura = new Lectura("Datos Conductor");
		lectura.creaEntrada(CAMPO_DNI, "");
		lectura.creaEntrada("Nombre","");
		lectura.creaEntrada("Apellido1", "");
		lectura.creaEntrada("Apellido2", "");
		lectura.creaEntrada("Direccion", "");
		lectura.esperaYCierra();
		String dni = lectura.leeString("DNI");
		String nombre = lectura.leeString("Nombre");
		String apellido1 = lectura.leeString("Apellido1");
		String apellido2 = lectura.leeString("Apellido2");
		String direccion = lectura.leeString("Direccion");
		// Anhade el conductor
		if (!gestionTransportes.anhadeConductor(dni, nombre, apellido1, apellido2, direccion)) { // WMC 1 CCog 1
			return ("ERROR: Ya existe un conductor con DNI "+dni);
		}else { // CCog 1
			return("EXITO: conductor anhadido con exito");
		}
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * @param titulo titulo de la ventana
	 * @param txt texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) {// WMC 1
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}
}

//WMC = 13  WMCn = 2.6 CCog = 5 CCogn = 1