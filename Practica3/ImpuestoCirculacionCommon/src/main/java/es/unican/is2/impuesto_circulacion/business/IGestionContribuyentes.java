package es.unican.is2.impuesto_circulacion.business;

import es.unican.is2.impuesto_circulacion.domain.Contribuyente;
import es.unican.is2.impuesto_circulacion.exceptions.DataAccessException;
import es.unican.is2.impuesto_circulacion.exceptions.OperacionNoValidaException;

/**
 * Interfaz de negocio para gestionar contribuyentes
 */
public interface IGestionContribuyentes {
	
	/**
	 * Registra un nuevo contribuyente
	 * @param c Contribuyente que desea registrar
	 * @return El contribuyente registrado
	 * 		   null si no se registra porque ya existe un 
	 *              contribuyente con el mismo dni
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
	public Contribuyente altaContribuyente(Contribuyente c) throws DataAccessException;
	
	/**
	 * Elimina el contribuyente cuyo dni se indica
	 * @param dni DNI del contribuyente que se quiere eliminar
	 * @return El contribuyente eliminado
	 * 		   null si no se elimina porque no se encuentra 
	 * @throws OperacionNoValidaException si el contribuyente existe 
	 *         pero tiene vehiculos a su nombre
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
	public Contribuyente bajaContribuyente(String dni) throws OperacionNoValidaException, DataAccessException;
	
	

}
