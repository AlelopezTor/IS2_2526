package es.unican.is2.Practica6Cambiado;

public class TransporteMercancias extends Transporte{
    private int toneladas;
    private CategoriaTransporte categoria;
    public TransporteMercancias(double horas, int toneladas, CategoriaTransporte categoria) { // WMC 1
        super(horas);

        if (toneladas<= 0) { // WMC 1 CCog 1
            throw new IllegalArgumentException();
        }
        this.toneladas = toneladas;
        this.categoria = categoria;
    }

    @Override
    public CategoriaTransporte getCategoria() { // WMC 1
        return categoria;
    }
    @Override
    public double sueldoExtra() { // WMC 1
        double sueldo = toneladas * 2;

    	if (this.getCategoria().equals(CategoriaTransporte.MERCANCIAS_PELIGROSAS)) { // WMC 1 CCog 1
            sueldo += 50;
        }

        return sueldo;
    }
    public int getToneladas() { // WMC 1
        return toneladas;
	}
}
//WMC = 6  WMCn = 1.5 CCog = 2 CCogn = 0.5
