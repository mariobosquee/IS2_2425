package es.unican.is2.impuesto_circulacion.domain;



import java.time.LocalDate;
import java.util.EnumSet;

/**
 * Clase que representa un vehiculo de tipo motocicleta
 */
public class Motocicleta extends Vehiculo {

	private int cilindrada;

	public Motocicleta(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, int cilindrada) {
		super(id, matricula, fechaMatriculacion, motor);
		this.cilindrada = cilindrada;
		if (motor == null || !EnumSet.allOf(TipoMotor.class).contains(motor)) {
	        throw new IllegalArgumentException();
	    }
		if (fechaMatriculacion == null) {
	        throw new IllegalArgumentException();
	    }
	    if (fechaMatriculacion.isAfter(LocalDate.now())) {
	        throw new IllegalArgumentException();
	    }
	    if (cilindrada <= 0) {
	        throw new IllegalArgumentException();
	    }
	    if (matricula == null) {
	        throw new IllegalArgumentException();
	    }
	}

	/**
	 * Retorna la cilindrada en CC de la motocicleta.
	 */
	public int getCilindrada() {
		return cilindrada;
	}

	@Override
	public double precioImpuesto() {
		int cilindradaEnCC = getCilindrada();
		double importe = 0;
		
		if (cilindradaEnCC <= 125) {
			importe = 8;
		}
		else if (cilindradaEnCC > 125 && cilindradaEnCC <= 250) {
			importe = 15;
		}
		else if (cilindradaEnCC > 250 && cilindradaEnCC <= 500) {
			importe = 30;
		}
		else if (cilindradaEnCC > 500 && cilindradaEnCC <= 1000) {
			importe = 60;
		}
		else {
			importe = 120;
		}
		return aplicarDescuento(importe);
	}

}
