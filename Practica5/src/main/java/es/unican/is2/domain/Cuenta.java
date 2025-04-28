package es.unican.is2.domain;


public class Cuenta {
	
	private String numCuenta;
	
	public Cuenta(String numCuenta) { //WMC + 1 = 1
		this.numCuenta = numCuenta;
	}
	
	public String getNumCuenta() { //WMC + 1 = 2
		return numCuenta;
	}
	
}
