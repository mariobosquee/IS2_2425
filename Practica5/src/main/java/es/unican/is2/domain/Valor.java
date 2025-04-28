package es.unican.is2.domain;


/**
 * Clase que representa un valor en bolsa (paquete de acciones). 
 * Cada valor contiene un n�mero de acciones 
 * de una de las entidades del IBEX 35
 */
public class Valor {
	
	private String entidad;	
	private int numAcciones;
	private double cotizacion;
	
	public Valor(String entidad, int numAcciones, double cotizacionActual) { //WMC + 1 = 1
		this.entidad = entidad;
		this.numAcciones = numAcciones;
		this.cotizacion = cotizacionActual;
	}
	
	public int getNumValores() { //WMC + 1 = 2
		return numAcciones;
	}

	public void setNumValores(int numValores) { //WMC + 1 = 3
		this.numAcciones = numValores;
	}

	public double getCotizacion() { //WMC + 1 = 4
		return cotizacion;
	}
	
	public void setCotizacion(double cotizacion) { //WMC + 1 = 5
		this.cotizacion = cotizacion;
	}

	public String getEntidad() { //WMC + 1 = 6
		return entidad;
	}
	
	@Override
	public boolean equals(Object obj) { //WMC + 1 = 7 //CCog + 1 = 1
		Valor other = (Valor)obj;
		return (entidad.equals(other.entidad) && numAcciones==other.numAcciones);

	}

}