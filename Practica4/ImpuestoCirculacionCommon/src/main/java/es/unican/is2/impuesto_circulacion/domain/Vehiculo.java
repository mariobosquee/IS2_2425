package es.unican.is2.impuesto_circulacion.domain;


import java.time.LocalDate;

/**
 * Clase abstracta que representa un vehiculo. 
 * Cada vehiculo tiene una matricula unica.
 */
public abstract class Vehiculo {

	// Clave primaria autogenerada
	private long id;

	private String matricula;
	private LocalDate fechaMatriculacion;
	private TipoMotor motor;

	public Vehiculo(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor) {
		this.id = id;
		this.matricula = matricula;
		this.fechaMatriculacion = fechaMatriculacion;
		this.motor = motor;
	}

	/**
	 * Retorna la matricula del vehiculo.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Retorna la fecha de primera matriculacion del vehiculo.
	 */
	public LocalDate getFechaMatriculacion() {
		return fechaMatriculacion;
	}

	/**
	 * Retorna el tipo de motor del vehiculo.
	 */
	public TipoMotor getMotor() {
		return motor;
	}

	/**
	 * Retorna el identificador del vehiculo.
	 */
	public long getId() {
		return id;
	}

	public abstract double precioImpuesto();
	
	protected double aplicarDescuento(double importe) {
		if (fechaMatriculacion.plusYears(25).isBefore(LocalDate.now())) {
			importe = importe * 0.0;
		}		
		else if (motor == TipoMotor.ELECTRICO) {
			importe = importe * 0.25;
		}
		else if (motor == TipoMotor.HIBRIDO && fechaMatriculacion.plusYears(4).isAfter(LocalDate.now())) {
			importe = importe * 0.25;
		}
		else if (motor == TipoMotor.GAS && fechaMatriculacion.plusYears(1).isAfter(LocalDate.now())) {
			importe = importe * 0.5;
		}
		
		return importe;
	}

}
