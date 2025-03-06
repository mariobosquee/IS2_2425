package es.unican.is2.impuesto_circulacion.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import es.unican.is2.impuesto_circulacion.domain.Contribuyente;
import es.unican.is2.impuesto_circulacion.exceptions.DataAccessException;

/**
 * Clase de utilidad que mapea filas de la base de datos a 
 * instancias de Contribuyente
 */
public class ContribuyenteMapper {

	/**
	 * Recibe una fila de la BBDD correspondinete a un contribuyente 
	 * y devuelve un objeto Contribuyente con los datos correspondientes
	 * a sus atributos primitivos (no asociaciones múltiples)
	 * @param results Fila resultado de una consulta en base de datos
	 * @return Contribuyente El contribuyente en su estado actual en BBDD
	 */
	public static Contribuyente toContribuyente(ResultSet results) throws DataAccessException {

		Contribuyente cont =null;
		try {
			String dni = results.getString("dni");
			String nombre = results.getString("nombre");
			String apellido1 = results.getString("apellido1");
			String apellido2 = results.getString("apellido2");
			cont = new Contribuyente(nombre, apellido1, apellido2, dni);
		}
		catch (SQLException e) {
			throw new DataAccessException();
		}
		return cont;
	}
}
