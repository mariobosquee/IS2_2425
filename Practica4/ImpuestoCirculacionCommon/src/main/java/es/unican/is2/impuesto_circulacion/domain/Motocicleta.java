package es.unican.is2.impuesto_circulacion.domain;



import java.time.LocalDate;

/**
 * Clase que representa un vehiculo de tipo motocicleta
 */
public class Motocicleta extends Vehiculo {

	private int cilindrada;

	public Motocicleta(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, int cilindrada) {
		super(id, matricula, fechaMatriculacion, motor);
		this.cilindrada = cilindrada;
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
		
		if (cilindradaEnCC <= 125) {
			return 8;
		}
		else if (cilindradaEnCC > 125 && cilindradaEnCC <= 250) {
			return 15;
		}
		else if (cilindradaEnCC > 250 && cilindradaEnCC <= 500) {
			return 30;
		}
		else if (cilindradaEnCC > 500 && cilindradaEnCC <= 1000) {
			return 60;
		}
		else {
			return 120;
		}
	}

}
