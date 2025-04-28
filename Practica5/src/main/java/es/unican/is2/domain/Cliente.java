package es.unican.is2.domain;



import java.util.LinkedList;
import java.util.List;

public class Cliente {
	
	public String nombre;
	public String calle;
	public String zip;
	public String localidad;
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();
    
    private List<Tarjeta> tarjetas = new LinkedList<Tarjeta>();

 	public Cliente(String titular, String calle, String zip, String localidad, //WMC + 1 = 1
 			String telefono, String dni) {  
		this.nombre = titular;
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public void cambiaDireccion(String calle, String zip, String localidad) { //WMC + 1 = 2
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}
	
	public void anhadeCuenta(Cuenta c) { //WMC + 1 = 3
		Cuentas.add(c);
	}
	
	public void anhadeTarjeta(Tarjeta t) { //WMC + 2 = 5 //CCog + 2 = 2
		tarjetas.add(t);
		if (t instanceof Debito) {
			Debito td = (Debito)t;
			td.getCuentaAsociada().setCaducidadDebito(td.getCaducidadDebito());
		} else {
			Credito tc = (Credito) t;
			tc.getCuentaAsociada().setCaducidadCredito(tc.getCaducidadCredito());
		}
	}
	
	public double getSaldoTotal() { //WMC + 5 = 10 //CCog + 7 = 9
		double total = 0.0;
		for (Cuenta c: Cuentas) {  
			if (c instanceof CuentaAhorro) {
				total += ((CuentaAhorro) c).getSaldo();
			} else if (c instanceof CuentaValores)  {
				for (Valor v: ((CuentaValores) c).getValores()) {
					total += v.getCotizacion()*v.getNumValores();
				}
			}
		}
		return total;
	}
	
	public String getNombre() { //WMC + 1 = 11
		return nombre;
	}

	public String getCalle() { //WMC + 1 = 12
		return calle;
	}

	public String getZip() { //WMC + 1 = 13
		return zip;
	}

	public String getLocalidad() { //WMC + 1 = 14
		return localidad;
	}

	public String getTelefono() { //WMC + 1 = 15
		return telefono;
	}

	public String getDni() { //WMC + 1 = 16
		return dni;
	}
	
	
	
}