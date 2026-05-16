package es.unican.is2.Practica6Cambiado;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GestionTransportes {

	private Map<String, Conductor> conductores = new HashMap<>();
	
	public Conductor buscaConductor(String DNI) { // WMC 1
		return conductores.get(DNI);
	}
	
	public boolean anhadeConductor(String dni, String nombre, String apellido1, String apellido2, String direccion) { // WMC 1
		if (buscaConductor(dni) != null) // WMC 1 CCog 1
			return false;
		conductores.put(dni, new Conductor(dni, nombre, apellido1, apellido2,direccion));
		return true;
	}

	public Map<String, Conductor> getConductores() { // WMC 1
		return conductores;
	}
	
	public String mejorConductor() { // WMC 1
		List<Conductor> resultado = new LinkedList<Conductor>();
		double maxSueldo = 0.0;
		for (Conductor c : this.getConductores().values()) { // WMC 1 CCog 1
			double sueldoConductor = c.sueldo();
			if (sueldoConductor > maxSueldo) { // WMC 1 CCog 2
				maxSueldo = sueldoConductor;
				resultado.clear();
				resultado.add(c);
			} else if (sueldoConductor == maxSueldo) { // WMC 1 CCog 1
				resultado.add(c);
			}
		}		
		return formatearMensaje(resultado);
	}

	private String formatearMensaje(List<Conductor> resultado) { // WMC 1
		String msj = "";
		if (resultado.size() == 0) { // WMC 1 CCog 1
			msj = "No hay conductores";
		} else { // CCog 1
			for (Conductor c : resultado) { // WMC 1 CCog 2
				msj += c.getNombre()+"\n";
			}
		}
		return msj;
	}
	public String getSueldoConductor(String dni) { // WMC 1
		Conductor conductor = this.buscaConductor(dni);
		if (conductor == null) { // WMC 1 CCog 1
	        return "ERROR: No existe un conductor con DNI "+dni;

		}else { // CCog 1
			return ("Sueldo: El sueldo del conductor es: "+conductor.sueldo());

		}
		
	}
	public String crearTransporte(int horas, int personas, int toneladas, CategoriaTransporte tipo, String dni) { // WMC 1
	    if (tipo == null) { // WMC 1 CCog 1
	        return "ERROR: tipo de transporte no valido";
	    }
		Conductor conductor = this.buscaConductor(dni);
		if (conductor!=null) { // WMC 1 CCog 1
			switch (tipo) { //CCog 2
				case PERSONAS: // WMC 1
					conductor.anhadeTransporte(new TransportePersonas(horas, personas));
					break;
				case MERCANCIAS: // WMC 1
					
					conductor.anhadeTransporte(new TransporteMercancias(horas, toneladas, CategoriaTransporte.MERCANCIAS));
					break;
				case MERCANCIAS_PELIGROSAS: // WMC 1
					
					conductor.anhadeTransporte(new TransporteMercancias(horas, toneladas, CategoriaTransporte.MERCANCIAS_PELIGROSAS));
					break;		
			}
			return ("EXITO: el transporte ha sido anhadido correctamente al conductor con DNI: "+dni);

		} else { // CCog 1
			return ("ERROR: No existe un conductor con DNI "+dni);

		}
	
	}
}

//WMC = 19  WMCn = 2.71  CCog = 16  CCogn = 2.29
