package es.unican.is2.impuesto_circulacion.dao;

import java.util.List;

import es.unican.is2.impuesto_circulacion.domain.Contribuyente;
import es.unican.is2.impuesto_circulacion.exceptions.DataAccessException;

/**
 * Interfaz DAO para contribuyentes
 */
public interface IContribuyentesDAO  {
	
	/** 
	 * Persite un nuevo contribuyente
	 * @param c contribuyente a persistir
	 * @return El contribuyente una vez persistido
	 *         null si no se puede persistir porque ya existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Contribuyente creaContribuyente(Contribuyente c) throws DataAccessException;
	
	/**
	 * Retorna el contribuyente cuyo dni se indica
	 * @param dni DNI del contribuyente buscado
	 * @return El contribuyente correspondiente al dni
	 * 		   null si no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Contribuyente contribuyente(String dni) throws DataAccessException;
	
	/**
	 * Actualiza el estado del contribuyente
	 * @param nuevo Nuevo estado del contribuyente
	 * @return El contribuyente actualizado
	 *         null si el contribuyente no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Contribuyente actualizaContribuyente(Contribuyente nuevo) throws DataAccessException;
	
	/**
	 * Elimina el contribuyente cuyo dni se indica
	 * @param dni DNI del contribuyente
	 * @return El contribuyente eliminado
	 *         null si no el contribuyente no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Contribuyente eliminaContribuyente(String dni) throws DataAccessException;
	
	/**
	 * Retorna la lista completa de contribuyentes
	 * @return La lista de contribuyentes
	 *         Lista vacia si no hay ninguno
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public List<Contribuyente> contribuyentes() throws DataAccessException;
	
}
