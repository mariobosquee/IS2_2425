package es.unican.is2.domain;


import java.time.LocalDate;

import es.unican.is2.exceptions.datoErroneoException;
import es.unican.is2.exceptions.saldoInsuficienteException;

public class Debito extends Tarjeta {
	
	private double saldoDiarioDisponible;

	public Debito(String numero, String titular, String cvc, CuentaAhorro cuentaAsociada) { //WMC + 1 = 1
		super(numero, titular, cvc, cuentaAsociada);
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}

	@Override
	public void retirar(double x) 
			throws saldoInsuficienteException, datoErroneoException { //WMC + 1 = 2
		restarSaldo("Retirada en cajero", x);
	}
	
	@Override
	public void pagoEnEstablecimiento(String datos, double x) 
			throws saldoInsuficienteException, datoErroneoException { //WMC + 1 = 3
		restarSaldo("Compra en : " + datos, x);
	}
	
	public void restarSaldo(String datos, double x) 
			throws saldoInsuficienteException, datoErroneoException { //WMC + 2 = 5 //CCog + 1 = 1
		if (saldoDiarioDisponible<x) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar(datos, x);
		saldoDiarioDisponible-=x;
	}
	
	
	public LocalDate getCaducidadDebito() { //WMC + 1 = 6
		return this.cuentaAsociada.getCaducidadDebito();
	}
	
	/**
	 * Metodo invocado automaticamente a las 00:00 de cada dia
	 */
	public void restableceSaldo() { //WMC + 1 = 7
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}
	
	public CuentaAhorro getCuentaAsociada() { //WMC + 1 = 8
		return cuentaAsociada;
	}

}