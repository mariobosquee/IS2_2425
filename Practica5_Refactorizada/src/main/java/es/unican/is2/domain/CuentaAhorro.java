package es.unican.is2.domain;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import es.unican.is2.exceptions.datoErroneoException;
import es.unican.is2.exceptions.saldoInsuficienteException;

public class CuentaAhorro extends Cuenta {

	private List<Movimiento> Movimientos;
	private LocalDate caducidadDebito;
	private LocalDate caducidadCredito;
	private double limiteDebito;

	public CuentaAhorro(String numCuenta)  throws datoErroneoException { //WMC + 1 = 1
		super(numCuenta);
		Movimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	}

	public void ingresar(double x) throws datoErroneoException { //WMC + 1 = 2 
		ingresar("Ingreso en efectivo", x);
	}

	public void retirar(double x) 
			throws saldoInsuficienteException, datoErroneoException { //WMC + 1 = 3
		retirar("Retirada de efectivo", x);
	}

	public void ingresar(String concepto, double x) 
			throws datoErroneoException { //WMC + 2 = 5 //CCog + 1 = 1
		if (x <= 0)
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		this.Movimientos.add(new Movimiento(concepto, LocalDateTime.now(), x));
	}

	public void retirar(String concepto, double x) 
			throws saldoInsuficienteException, datoErroneoException { //WMC + 3 = 8 //CCog + 2 = 3
		if (getSaldo() < x)
			throw new saldoInsuficienteException("Saldo insuficiente");
		if (x <= 0)
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		this.Movimientos.add(new Movimiento(concepto, LocalDateTime.now(), -x));
	}

	public double getSaldo() { //WMC + 2 = 10 //CCog + 1 = 4
		double r = 0.0;
		for (int i = 0; i < this.Movimientos.size(); i++) {
			Movimiento m = (Movimiento) Movimientos.get(i);
			r += m.getI();
		}
		return r;
	}

	public void addMovimiento(Movimiento m) { //WMC + 1 = 11
		Movimientos.add(m);
	}

	public List<Movimiento> getMovimientos() { //WMC + 1 = 12
		return Movimientos;
	}

	public LocalDate getCaducidadDebito() { //WMC + 1 = 13
		return caducidadDebito;
	}

	public void setCaducidadDebito(LocalDate caducidadDebito) { //WMC + 1 = 14
		this.caducidadDebito = caducidadDebito;
	}

	public LocalDate getCaducidadCredito() { //WMC + 1 = 15
		return caducidadCredito;
	}

	public void setCaducidadCredito(LocalDate caducidadCredito) { //WMC + 1 = 16
		this.caducidadCredito = caducidadCredito;
	}

	public double getLimiteDebito() { //WMC + 1 = 17
		return limiteDebito;
	}

}