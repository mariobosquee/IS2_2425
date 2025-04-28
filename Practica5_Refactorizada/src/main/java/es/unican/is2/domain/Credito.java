package es.unican.is2.domain;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import es.unican.is2.exceptions.datoErroneoException;
import es.unican.is2.exceptions.saldoInsuficienteException;


public class Credito extends Tarjeta {
	
	private double credito;
	private List<Movimiento> MovimientosMensuales;
	private List<Movimiento> historicoMovimientos;

	public Credito(String numero, String titular, String cvc,
			CuentaAhorro cuentaAsociada, double credito) { //WMC + 1 = 1
		super(numero, titular, cvc, cuentaAsociada);
		this.credito = credito;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. Se aplica una comisi�n del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	public void retirar(double x) 
			throws saldoInsuficienteException, datoErroneoException { //WMC + 3 = 4 //CCog + 3 = 3
		if (x<0)
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		Movimiento m = nuevoMovimiento("Retirada en cajero", x);
		
		if (getGastosAcumulados()+x > credito)
			throw new saldoInsuficienteException("Credito insuficiente");
		else {
			MovimientosMensuales.add(m);
		}
	}

	@Override
	public void pagoEnEstablecimiento(String datos, double x) 
			throws saldoInsuficienteException, datoErroneoException { //WMC + 3 = 7 //CCog + 2 = 5
		if (x<0)
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + x > credito)
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		Movimiento m = nuevoMovimiento("Compra a credito en: " + datos, x);
		MovimientosMensuales.add(m);
	}
	
	public Movimiento nuevoMovimiento(String datos, double x) { //WMC + 1 = 8
		return new Movimiento(datos, LocalDateTime.now(), -x);
	}
	
    private double getGastosAcumulados() { //WMC + 2 = 10 //CCog + 1 = 6
		double r = 0.0;
		for (int i = 0; i < this.MovimientosMensuales.size(); i++) {
			Movimiento m = (Movimiento) MovimientosMensuales.get(i);
			r += m.getI();
		}
		return r;
	}
	
	
	public LocalDate getCaducidadCredito() { //WMC + 1 = 11
		return this.cuentaAsociada.getCaducidadCredito();
	}

	/**
	 * Metodo que se invoca automaticamente el dia 1 de cada mes
	 */
	public void liquidar() { //WMC + 3 = 14 //CCog + 2 = 8
		double r = 0.0;
		for (int i = 0; i < this.MovimientosMensuales.size(); i++) {
			Movimiento m = (Movimiento) MovimientosMensuales.get(i);
			r += m.getI();
		}
		
		Movimiento liq = new Movimiento("Liquidacion de operaciones tarjeta credito", LocalDateTime.now(), -r);
	
		if (r != 0)
			cuentaAsociada.addMovimiento(liq);
		
		historicoMovimientos.addAll(MovimientosMensuales);
		MovimientosMensuales.clear();
	}

	public List<Movimiento> getMovimientosMensuales() { //WMC + 1 = 15
		return MovimientosMensuales;
	}
	
	public CuentaAhorro getCuentaAsociada() { //WMC + 1 = 16
		return cuentaAsociada;
	}
	
	public List<Movimiento> getMovimientos() { //WMC + 1 = 17
		return historicoMovimientos;
	}

}