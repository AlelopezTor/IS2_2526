package es.unican.is2.Practica6Cambiado;

import java.util.ArrayList;

/**
 * Clase que representa a un conductor, con sus datos personales
 * y los transportes que ha realizado. 
 */
public class Conductor {

	private ArrayList<Transporte> transportes = new ArrayList<Transporte>();
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String direccion;
	private static final int PRECIO_FIJO_TRANSPORTES = 700;
	private static final int EXTRA_BASICO = 5;


	public Conductor(String dni, String nombre, String apellido1,
			String apellido2, String direccion) { // WMC 1
		if (dni == null || nombre == null || apellido1 == null || direccion == null) { // WMC 4 CCog 2
			throw new IllegalArgumentException();
		}
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.direccion = direccion;
	}

	public String getDni() { // WMC 1
		return dni;
	}

	public String getNombre() { // WMC 1
		return nombre;
	}

	public String getApellido1() { // WMC 1
		return apellido1;
	}

	public String getApellido2() { // WMC 1
		return apellido2;
	}

	public String getDireccion() { // WMC 1
		return direccion;
	}

	public double sueldo() { // WMC 1
		double sueldoTransportes = 0;
	
		for (Transporte t : transportes) { // WMC 1 CCog 1
			sueldoTransportes += t.getHoras() * EXTRA_BASICO + t.sueldoExtra();
		}
		return PRECIO_FIJO_TRANSPORTES + sueldoTransportes;
	}

	public void anhadeTransporte(Transporte t) { // WMC 1
		transportes.add(t);
	}

}
//WMC = 13  WMCn = 1.6 CCog = 3 CCogn = 0.4
