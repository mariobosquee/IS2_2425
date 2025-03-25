package es.unican.is2.impuesto_circulacion.domain;

import java.time.LocalDate;
import java.util.EnumSet;

/**
 * Clase que representa un vehiculo de tipo turismo.
 */
public class Turismo extends Vehiculo {

	private double potencia;
	
	public Turismo(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, double potencia) {
		super(id, matricula, fechaMatriculacion, motor);
		this.potencia = potencia;
		if (motor == null || !EnumSet.allOf(TipoMotor.class).contains(motor)) {
	        throw new IllegalArgumentException();
	    }
		if (fechaMatriculacion == null) {
	        throw new IllegalArgumentException();
	    }
	    if (fechaMatriculacion.isAfter(LocalDate.now())) {
	        throw new IllegalArgumentException();
	    }
	    if (potencia <= 0) {
	        throw new IllegalArgumentException();
	    }
	    if (matricula == null) {
	        throw new IllegalArgumentException();
	    }
	}

	/**
	 * Retorna la potencia en caballos fiscales del vehiculo.
	 */
	public double getPotencia() {
		return potencia;
	}

	@Override
	public double precioImpuesto() {
		double potenciaEnCaballosFiscales = getPotencia();
		double importe = 0;
		
		if (potenciaEnCaballosFiscales > 0 && potenciaEnCaballosFiscales < 8) {
			importe = 25;
		}
		else if (potenciaEnCaballosFiscales >= 8 && potenciaEnCaballosFiscales < 12) {
			importe = 67;
		}
		else if (potenciaEnCaballosFiscales >= 12 && potenciaEnCaballosFiscales < 16) {
			importe = 143;
		}
		else if (potenciaEnCaballosFiscales >= 16 && potenciaEnCaballosFiscales < 20) {
			importe = 178;
		}
		else {
			importe = 223;
		}
		
		return aplicarDescuento(importe);
	}

}
