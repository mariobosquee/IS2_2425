package es.unican.is2;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import es.unican.is2.impuesto_circulacion.exceptions.DataAccessException;


/**
 * Clase que gestiona el acceso a la base de datos H2 en memoria.
 * Permite abrir conexiones y crear y rellenar la propia base de datos
 * al inicio de la aplicacion
 */
public class H2ServerConnectionManager {

	// Conexion con la base de datos
	protected static Connection connection;

	// Atributos de acceso a la Base de Datos
	protected static String dbName = "test";
	protected static String user = "sa";
	protected static String pass = "";

	/**
	 * Obtiene una conexion con la base de datos
	 * @return La conexion
	 * @throws DataAccessException si no se puede establecer al conexion
	 */
	public static Connection getConnection() throws DataAccessException {

		if (connection == null) { 
			try {
				Class.forName("org.h2.Driver"); //comprueba que el driver esta instalado
				connection = DriverManager.getConnection("jdbc:h2:mem:test", "sa","");		
				cargaDatos();
			} catch (SQLException | ClassNotFoundException e) {
				throw new DataAccessException();
			}
		}
		return connection;
	}

	/**
	 * Crea la base de datos e introduce los datos iniciales
	 * @throws DataAccessException Si hay un fallo en la conexion
	 */
	public static void cargaDatos() throws DataAccessException {
		try {
			Connection con = getConnection();
			
			// Creacion programatica de la BBDD
			Statement stm = con.createStatement(); 
			
			// Creacion de la tabla Contribuyentes
			String sql= "CREATE TABLE Contribuyentes (dni CHAR(9) NOT NULL, nombre VARCHAR(100) NOT NULL, "
					+ "apellido1 VARCHAR(100) NOT NULL, apellido2 VARCHAR(100), PRIMARY KEY(dni))";
			stm.execute(sql); 
			
			// Creacion de la tabla Vehiculos
			sql  = "CREATE TABLE Vehiculos (id BIGINT NOT NULL AUTO_INCREMENT, type VARCHAR(100) NOT NULL, matricula CHAR(7) NOT NULL, fechaMatricula DATE NOT NULL, "
					+ "motor VARCHAR(100) NOT NULL, potencia INT, cilindrada INT, contribuyente_FK CHAR(9) NOT NULL, "
					+ "PRIMARY KEY(id), FOREIGN KEY(contribuyente_FK) REFERENCES Contribuyentes(dni))";
			stm.execute(sql); 		
			
			// Inicializa contribuyentes
			sql = "INSERT INTO Contribuyentes (dni, nombre, apellido1, apellido2) VALUES ('11111111A', 'Juan', 'Perez', 'Lopez')"; 
			stm.executeUpdate(sql);
			sql = "INSERT INTO Contribuyentes (dni, nombre, apellido1, apellido2) VALUES ('22222222A', 'Ana', 'Cuesta', 'Ruiz')"; 
			stm.executeUpdate(sql);
			sql = "INSERT INTO Contribuyentes (dni, nombre, apellido1, apellido2) VALUES ('33333333A', 'Luis', 'Ruiz', 'Rivas')"; 
			stm.executeUpdate(sql);
			sql = "INSERT INTO Contribuyentes (dni, nombre, apellido1, apellido2) VALUES ('44444444A', 'Pepe', 'Lopez', 'Abascal')"; 
			stm.executeUpdate(sql);
			
			// Inicializa vehiculos
			sql = "INSERT INTO Vehiculos (type, matricula, fechaMatricula, motor, potencia, contribuyente_FK) "
					+ "VALUES ('Turismo', '1111AAA', '2002-01-15', 'GASOLINA', 15, '11111111A')";
			stm.executeUpdate(sql);
			sql = "INSERT INTO Vehiculos (type, matricula, fechaMatricula, motor, potencia, contribuyente_FK) "
					+ "VALUES ('Turismo', '1111BBB', '2016-05-20', 'ELECTRICO', 20, '11111111A')";
			stm.executeUpdate(sql);			
			sql = "INSERT INTO Vehiculos (type, matricula, fechaMatricula, motor, cilindrada, contribuyente_FK) "
					+ "VALUES ('Motocicleta', '1111CCC', '2022-05-21', 'GASOLINA', 100, '11111111A')";
			stm.executeUpdate(sql);
			
			sql = "INSERT INTO Vehiculos (type, matricula, fechaMatricula, motor, potencia, contribuyente_FK) "
					+ "VALUES ('Turismo', '2222AAA', '2010-06-01', 'DIESEL', 25, '22222222A')";
			stm.executeUpdate(sql);
			
			sql = "INSERT INTO Vehiculos (type, matricula, fechaMatricula, motor, potencia, contribuyente_FK) "
					+ "VALUES ('Turismo', '4444AAA', '2024-01-02', 'DIESEL', 40, '44444444A')";
			stm.executeUpdate(sql);
			sql = "INSERT INTO Vehiculos (type, matricula, fechaMatricula, motor, cilindrada, contribuyente_FK) "
					+ "VALUES ('Motocicleta', '4444BBB', '2024-01-02', 'GASOLINA', 300, '44444444A')";
			stm.executeUpdate(sql);
			
			
			// Cierra el statement
			stm.close();
			
		} catch (SQLException e) {
			System.out.println(e);
			throw new DataAccessException();
			
		} 		
	}

	/**
	 * Metodo estatico para ejecutar operaciones SQL y manejar los errores.
	 * 
	 * IMPORTANTE: este metodo solo puede ser llamado para operaciones de INSERT, UPDATE
	 * y DELETE. No debe usarse para realizar SELECTs ni llamadas a PROCEDIMIENTO, las ejecuciones
	 * de ese tipo de operaciones tienen que implementarse en sus respectivos metodos.
	 * @param stringStatement Instruccion a ejecutar
	 * @return true si se ha ejecutado la operacion
	 *         false si no se ha ejecutado la operacion
	 * @throws DataAccessException si hay un error en la conexion
	 */
	public static void executeSqlStatement(String stringStatement) throws DataAccessException {
		Connection con = getConnection(); 
		try {
			Statement stm = con.createStatement(); 
			stm.execute(stringStatement); 
			stm.close(); 
		}
		catch (SQLException e) {
			throw new DataAccessException(); 
		}
	}
}
