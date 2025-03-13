package es.unican.is2.impuesto_circulacion.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import es.unican.is2.impuesto_circulacion.domain.Motocicleta;
import es.unican.is2.impuesto_circulacion.domain.TipoMotor;
import es.unican.is2.impuesto_circulacion.domain.Turismo;
import es.unican.is2.impuesto_circulacion.domain.Vehiculo;
import es.unican.is2.impuesto_circulacion.exceptions.DataAccessException;

/**
 * Clase de utilidad que mapea filas de la base de datos a 
 * instancias de Empleado
 */
public class VehiculoMapper {

	/**
	 * Recibe una fila de la BBDD correspondinete a un vehiculo 
	 * y devuelve un objeto Vehiculo con los datos correspondientes
	 * @param results Fila resultado de una consulta en base de datos
	 * @return Empleado El empleado en su estado actual en BBDD
	 */
	public static Vehiculo toVehiculo(ResultSet results) throws DataAccessException {

		Vehiculo veh = null;
		try {
			long id = results.getLong("id");
			String matricula = results.getString("matricula");
			LocalDate fecha = results.getDate("fechaMatricula").toLocalDate();
			TipoMotor motor = TipoMotor.valueOf(results.getString("motor"));
			if (results.getString("type").equals("Turismo")) {
				int potencia = Integer.valueOf(results.getString("potencia"));
				veh = new Turismo(id, matricula, fecha, motor, potencia);
			} else {
				int cilindrada = Integer.valueOf(results.getString("cilindrada"));
				veh = new Motocicleta(id, matricula, fecha, motor, cilindrada);
			}
			return veh;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException();
		}
	}

}
