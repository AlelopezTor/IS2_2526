package es.unican.is2.Practica6Cambiado;

public enum CategoriaTransporte {

	MERCANCIAS, MERCANCIAS_PELIGROSAS, PERSONAS;
	
    public static CategoriaTransporte desdeCodigo(String codigo) { // WMC 1
        if (codigo == null) { // WMC 1 CCog 1
        	return null; 
        }
        switch (codigo.trim().toUpperCase()) { // CCog 1
            case "P":  return PERSONAS; // WMC 1 
            case "M":  return MERCANCIAS; // WMC 1
            case "MP": return MERCANCIAS_PELIGROSAS; // WMC 1
            default:   return null;
        }
    }
}
//WMC = 5  WMCn = 5  CCog = 2  CCogn = 2
