package es.unican.is2.impuesto_circulacion.domain;

import java.time.LocalDate;

/**
 * Clase que representa un vehiculo de tipo turismo.
 */
public class Turismo extends Vehiculo {

	private double potencia;
	
	public Turismo(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, double potencia) {
		super(id, matricula, fechaMatriculacion, motor);
		this.potencia = potencia;
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
		
		if (potenciaEnCaballosFiscales < 8) {
			return 25;
		}
		else if (potenciaEnCaballosFiscales >= 8 && potenciaEnCaballosFiscales < 12) {
			return 67;
		}
		else if (potenciaEnCaballosFiscales >= 12 && potenciaEnCaballosFiscales < 16) {
			return 143;
		}
		else if (potenciaEnCaballosFiscales >= 16 && potenciaEnCaballosFiscales < 20) {
			return 178;
		}
		else {
			return 223;
		}
	}

}
