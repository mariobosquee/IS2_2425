package es.unican.is2.impuesto_circulacion.dao;

import java.util.List;

import es.unican.is2.impuesto_circulacion.domain.Vehiculo;
import es.unican.is2.impuesto_circulacion.exceptions.DataAccessException;

/**
 * Interfaz DAO para vehiculos
 */
public interface IVehiculosDAO  {
	
	/** 
	 * Persite un nuevo vehiculo
	 * @param v Vehiculo a persistir
	 * @return El vehiculo una vez persistido
	 *         null si no se persiste el vehiculo porque ya existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Vehiculo creaVehiculo(Vehiculo v) throws DataAccessException;
	
	/**
	 * Elimina el vehiculo cuya matricula se indica
	 * @param matricula Matricula del vehiculo
	 * @return El vehiculo eliminado
	 *         null si no se encuentra el vehiculo
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Vehiculo eliminaVehiculo(String matricula) throws DataAccessException;
	
	/**
	 * Actualiza el estado del vehiculo
	 * @param nuevo Nuevo estado del vehiculo
	 * @return El vehiculo actualizado
	 *         null si no se existe el vehiculo
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Vehiculo actualizaVehiculo(Vehiculo nuevo) throws DataAccessException;
	
	/**
	 * Retorna el vehiculo cuya matricula se indica
	 * @param matricula Matricula del vehiculo
	 * @return El vehiculo correspondiente a la matricula
	 * 		   null si no se encuentra
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Vehiculo vehiculoPorMatricula(String matricula) throws DataAccessException;
	
	/**
	 * Retorna la lista completa de vehiculos
	 * @return La lista de vehiculos
	 *         Lista vacia si no hay ninguno
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public List<Vehiculo> vehiculos() throws DataAccessException;
	
	/**
	 * Retorna el vehiculo cuyo identificador se indica
	 * @param id Identificador del vehiculo
	 * @return El vehiculo correspondiente al identificador
	 * 		   null si no se encuentra
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Vehiculo vehiculo(long id) throws DataAccessException;
}
