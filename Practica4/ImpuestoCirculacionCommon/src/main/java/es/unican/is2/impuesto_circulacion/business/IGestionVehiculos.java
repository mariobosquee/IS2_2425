package es.unican.is2.impuesto_circulacion.business;

import es.unican.is2.impuesto_circulacion.domain.Vehiculo;
import es.unican.is2.impuesto_circulacion.exceptions.DataAccessException;
import es.unican.is2.impuesto_circulacion.exceptions.OperacionNoValidaException;

/**
 * Interfaz de negocio para gestionar vehiculos
 */
public interface IGestionVehiculos {
	
	/**
	 * Registra un nuevo vehiculo y lo asocia al contribuyente con el dni indicado
	 * @param v Vehiculo que desea registrar
	 * @param dni DNI del contribuyente
	 * @return El vehiculo ya registrado
	 * 		   null si no se registra porque no se encuentra el contribuyente
	 * @throws OperacionNoValidaException si ya existe un vehiculo con la misma matricula
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
	public Vehiculo altaVehiculo(Vehiculo v, String dni) throws OperacionNoValidaException, DataAccessException;
	
	/**
	 * Elimina el vehiculo cuya matricula se indica y 
	 * que pertenece al contribuyente cuyo dni se indica
	 * @param matricula Matricula del coche a eliminar
	 * @param dni DNI del propietario del vehiculo
 	 * @return El vehiculo eliminado
 	 *         null si el vehiculo o el propietario no existen
 	 * @throws OperacionNoValidaException si el vehiculo no pertenece al dni indicado
 	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Vehiculo bajaVehiculo(String matricula, String dni) throws OperacionNoValidaException, DataAccessException;
	
	/**
	 * Cambia el propietario del vehiculo indicado
	 * @param matricula Matricula del vehiculo
	 * @param dniActual DNI del propietario actual del vehiculo
	 * @param dniNuevo DNI del nuevo propietario del vehiculo
	 * @return true si se realiza el cambio
	 *         false si no realiza el cambio porque el vehiculo o los contribuyentes no existen
	 * @throws OperacionNoValidaException si el vehiculo no pertenece al dni indicado
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
	public boolean cambiaTitularVehiculo(String matricula, String dniActual, String dniNuevo) throws OperacionNoValidaException, DataAccessException;

}
