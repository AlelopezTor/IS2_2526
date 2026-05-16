package es.unican.is2.Practica6Cambiado;

/* Clase que representa un transporte realizado por un conductor */
public abstract class Transporte {
	
	private double horas;
	
	/**
	 * Constructor de la clase Transporte
	 * @param horas Horas que ha durado el transporte
	 * @param cat Categoria del transporte
	 * @param valor En caso de ser un transporte de tipo Personas, 
	 * representa el numero de personas, en caso de ser de tipo Mercancias 
	 * representa las toneladas
	 */ 
	public Transporte(double horas) throws IllegalArgumentException { // WMC 1
		if (horas <= 0) { // WMC 1 CCog 1
			throw new IllegalArgumentException();
		}
		this.horas = horas;
	}
	
	public double getHoras() { // WMC 1
		return horas;
	}
    public abstract CategoriaTransporte getCategoria(); // WMC 1
    public abstract double sueldoExtra(); // WMC 1
}
//WMC = 5  WMCn = 1.25 CCog = 1 CCogn = 0.25
