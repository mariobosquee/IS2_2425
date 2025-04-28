package es.unican.is2.domain;


import java.util.LinkedList;
import java.util.List;

public class CuentaValores extends Cuenta {

	private List<Valor> valores;
	
	public CuentaValores(String numCuenta) { //WMC + 1 = 1
		super(numCuenta);
		valores = new LinkedList<Valor>();
	}
	
	public List<Valor> getValores() { //WMC + 1 = 2
		return valores;
	}
	
	public boolean anhadeValor(Valor valor) { //WMC + 3 =  5 //CCog + 3 = 3
		for (Valor v:valores) {
			if (v.getEntidad().equals(valor.getEntidad()))
				return false;
		}
		valores.add(valor);
		return true;
	}
	
}
