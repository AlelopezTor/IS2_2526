package es.unican.is2.SegurosCommon;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.*;
/**
 * Clase que representa un cliente de la empresa de seguros
 * Un cliente se identifica por su dni
 */
public class Cliente {

    private String dni;

    private String nombre;  
    
    private boolean minusvalia;

    private List<Seguro> seguros = new LinkedList<Seguro>();
    private static final Pattern ESTRUCTURA_MATRICULA = Pattern.compile("^[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][A-Z]$");
    public Cliente(String dni, String nombre, boolean minusvalia) {
    	if (dni == null) {
    		throw new NullPointerException("Dni no puede ser null");
    	}
    	if (!ESTRUCTURA_MATRICULA.matcher(dni).matches()) {
    		throw new IllegalArgumentException("Dni mal puesta");
    	}
    	if (nombre == null) {
    		throw new NullPointerException("Nombre no puede ser null");
    	}
        this.dni = dni;
        this.nombre = nombre;
        this.minusvalia = minusvalia;
        this.seguros = new ArrayList<>();
    }
    public Cliente() {
        this.seguros = new ArrayList<>();
    }
	/**
     * Retorna los seguros del cliente 
     */
    public List<Seguro> getSeguros() {
        return seguros;
    }
    
    /**
     * Asigna la lista de seguros
     */
    public void setSeguros(List<Seguro> seguros) {
        this.seguros = seguros;
    }

    /**
     * Retorna el nombre del cliente.   
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del cliente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Retorna el dni del cliente.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Asigna el dni del cliente
     * @param dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }
    
    /**
     * Indica si el cliente es minusvalido
     */
    public boolean getMinusvalia() {
    	return minusvalia;
    }

    /**
     * Asigna la minusvalia del cliente
     * @param minusvalia
     */
     public void setMinusvalia(boolean minusvalia) {
        this.minusvalia = minusvalia;
    }
    
    /**
     * Calcula el total a pagar por el cliente por 
     * todos los seguros a su nombre
     */
    public double totalSeguros() {
    	
        if (this.getSeguros() == null) {
            throw new NullPointerException("La lista de seguros es null");
        }
        if (this.getSeguros().size() == 0) {
            return 0;
        }
        if (this.getSeguros().contains(null)) {
            throw new NullPointerException("La lista tiene elementos nulos");
        }
    	double suma = 0;
    	for (Seguro seguro: seguros) {
    		suma += seguro.precio();
    	}
    	if (getMinusvalia() == true) {
    		suma -= suma*0.25;
    	}
        return suma;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
        	return true;
        }
        if (!(o instanceof Cliente)) {
        	return false;
        }
        Cliente other = (Cliente) o;
        return this.dni.equals(other.dni);
    }

    @Override
    public int hashCode() {
        return this.dni.hashCode();
    }
}
