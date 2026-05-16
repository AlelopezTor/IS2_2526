package es.unican.is2.Practica6Cambiado;

public class TransportePersonas extends Transporte {

    private int personas;
    public TransportePersonas(double horas, int personas) { // WMC 1
        super(horas);

        if (personas <= 0) { // WMC 1 CCog 1
            throw new IllegalArgumentException();
        }

        this.personas = personas;
    }

    @Override
    public CategoriaTransporte getCategoria() { // WMC 1
        return CategoriaTransporte.PERSONAS;
    }

    public int getPersonas() { // WMC 1
        return personas;
    }
    @Override
    public double sueldoExtra() { // WMC 1
    	
    	double sueldo;
		if (this.getPersonas() < 10) { // WMC 1 CCog 1
			sueldo = this.getHoras() * 0.5;
		} else { // CCog 1
			sueldo = this.getHoras();
		}
		return sueldo;
    }
}
//WMC = 6  WMCn = 1.5 CCog = 3 CCogn = 0.75
