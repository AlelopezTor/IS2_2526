package es.unican.is2.SegurosCommon;

import java.time.LocalDate;
import java.util.regex.*;

/**
 * Clase que representa un seguro de coche.
 */
public class Seguro {
	
	private long id;

    private String matricula;

	private int potencia;

    private Cobertura cobertura;
    
    private LocalDate fechaInicio;

	private String conductorAdicional;
	static final public int PRECIO_BASE_TERCEROS = 400;
	static final public int PRECIO_BASE_TERCEROS_LUNAS = 600;
	static final public int PRECIO_BASE_TODO_RIESGO = 1000;
	static final public int MAX_POTENCIA = 110;
	static final public double SUBIDA_MAX_POTENCIA = 0.2;
	static final public int POTENCIA_MEDIA = 90;
	static final public double SUBIDA_POTENCIA_MEDIA = 0.05;
	static final public double DESCUENTO_DE_PRIMER_ANHO = 0.2;
	static final public int ANHO_PARA_DESCUENTO = 1;
    private static final Pattern ESTRUCTURA_MATRICULA = Pattern.compile("^[0-9][0-9][0-9][0-9][A-Z][A-Z][A-Z]$");
    public Seguro(String matricula, int potencia, Cobertura cobertura, LocalDate fechaInicio) {
    	if (matricula == null) {
    		throw new NullPointerException("Matricula no puede ser null");
    	}
    	if (!ESTRUCTURA_MATRICULA.matcher(matricula).matches()) {
    		throw new IllegalArgumentException("Matricula mal puesta");
    	}
    	if (potencia < 0) {
    		throw new IllegalArgumentException("La potencia no puede ser menor que 0");
    	}
    	if (cobertura == null) {
    		throw new NullPointerException("Cobertura no puede ser null");
    	}
    	if (fechaInicio == null) {
    		throw new NullPointerException("LA fecha no puede ser null");
    	}
    	if (fechaInicio.isBefore(LocalDate.now())) {
    		throw new IllegalArgumentException("La fecha no puede ser menor que hoy");
    	}
    	
        this.matricula = matricula;
        this.potencia = potencia;
        this.cobertura = cobertura;
        this.fechaInicio = fechaInicio;
        this.conductorAdicional = null;
    }
    public Seguro() {}
	/**
	 * Retorna el identificador del seguro
	 */
	public long getId() {
		return id;
	}

	/**
	 *  Asigna el valor del identificador del seguro
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Retorna la matricula del coche 
	 * asociado al seguro
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 *  Asigna el valor de la matrícula del seguro
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	/**
	 * Retorna la fecha de contratacion del seguro
	 */
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Asigna la fecha de inicio del seguro
	 * @param fechaInicio La fecha de inicio del seguro
	 */
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Retorna el tipo de cobertura del seguro
	 */
	public Cobertura getCobertura() {
		return cobertura;
	}

	/**
	 * Asigna el tipo de cobertura del seguro
	 * @param cobertura El tipo de cobertura del seguro
	 */	
	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;		
}

	/**
     * Retorna la potencia del coche asociado 
     * al seguro. 
     */
    public int getPotencia() {
        return potencia;
    }

	/**
	 *  Asigna el valor del identificador del seguro
	 */
	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	/**
	 * Retorna el conductor adicional del seguro, si lo hay
	 * @return El conductor adicional si lo hay
	 * 		null en caso contrario
	 */
	public String getConductorAdicional() {
		return conductorAdicional;
	}

	/**
	 * Asigna el conductor adicional del seguro
	 * @param conductorAdicional
	 */
	public void setConductorAdicional(String conductorAdicional) {
		this.conductorAdicional = conductorAdicional;
	}
    
    /**
     * Retorna el precio del seguro. 
	 * El precio se calcula a partir de la cobertura, la potencia del coche y el tiempo que lleva contratado el seguro
	 * @return El precio del seguro
	 *         0 si el seguro todavía no está en vigor (no se ha alcanzado su fecha de inicio)
     */
	public double precio() {
		if (getFechaInicio().isAfter(LocalDate.now())) {
			return 0;
		}
		float precio_base = 0;

		switch(getCobertura()){
		case TERCEROS:
			 precio_base = PRECIO_BASE_TERCEROS;
			 break;
		case TERCEROS_LUNAS:
			precio_base = PRECIO_BASE_TERCEROS_LUNAS;
			break;
		case TODO_RIESGO:
			precio_base = PRECIO_BASE_TODO_RIESGO;
			break;
		}
		double suma = precio_base;

		if (potencia > MAX_POTENCIA){
			suma += precio_base*SUBIDA_MAX_POTENCIA;
		}
		else if (potencia >= POTENCIA_MEDIA) {
			suma += precio_base*SUBIDA_POTENCIA_MEDIA;
		}
		if (getFechaInicio().plusYears(ANHO_PARA_DESCUENTO).isAfter(LocalDate.now())) {
			suma -= suma*DESCUENTO_DE_PRIMER_ANHO;
		}
		return suma;
	}
    @Override
    public boolean equals(Object o) {
	        if (this == o) {
	        	return true;
	        }
        if (!(o instanceof Seguro)) {
        	return false;
        }
        Seguro other = (Seguro) o;
        return this.matricula.equals(other.matricula);
    }

    @Override
    public int hashCode() {
        return this.matricula.hashCode();
    }
}
