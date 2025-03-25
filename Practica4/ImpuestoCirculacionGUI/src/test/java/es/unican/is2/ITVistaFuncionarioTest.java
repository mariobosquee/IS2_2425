package es.unican.is2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.fest.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.impuesto_circulacion.business.IInfoImpuestoCirculacion;
import es.unican.is2.impuesto_circulacion.domain.Contribuyente;
import es.unican.is2.impuesto_circulacion.domain.Motocicleta;
import es.unican.is2.impuesto_circulacion.domain.TipoMotor;
import es.unican.is2.impuesto_circulacion.domain.Turismo;
import es.unican.is2.impuesto_circulacion.domain.Vehiculo;
import es.unican.is2.impuesto_circulacion.exceptions.DataAccessException;


public class ITVistaFuncionarioTest {

	private FrameFixture demo;

	IInfoImpuestoCirculacion info = new IInfoImpuestoCirculacion() {
		public Contribuyente contribuyente(String dni) throws DataAccessException {
			List<Contribuyente> contribuyentes = new ArrayList<Contribuyente>();
	        contribuyentes.add(new Contribuyente("Juan", "Perez", "Lopez", "11111111A"));
	        contribuyentes.add(new Contribuyente("Ana", "Cuesta", "Ruiz", "22222222A"));
	        contribuyentes.add(new Contribuyente("Luis", "Ruiz", "Rivas", "33333333A"));
	        contribuyentes.add(new Contribuyente("Pepe", "Lopez", "Abascal", "44444444A"));
	        
	        for (Contribuyente c : contribuyentes) {
	            if (c.getDni().equals(dni)) {
	                return c;
	            }
	        }
			return null;
		}
		public Vehiculo vehiculo(String matricula) throws DataAccessException {
			List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	        vehiculos.add(new Turismo(1L, "1111AAA", LocalDate.of(2002, 1, 15), TipoMotor.GASOLINA, 15));
	        vehiculos.add(new Turismo(2L, "1111BBB", LocalDate.of(2016, 5, 20), TipoMotor.ELECTRICO, 20));
	        vehiculos.add(new Motocicleta(3L, "1111CCC", LocalDate.of(2022, 5, 21), TipoMotor.GASOLINA, 100));
	        vehiculos.add(new Turismo(4L, "2222AAA", LocalDate.of(2010, 6, 1), TipoMotor.DIESEL, 25));
	        vehiculos.add(new Turismo(5L, "4444AAA", LocalDate.of(2024, 1, 2), TipoMotor.DIESEL, 40));
	        vehiculos.add(new Motocicleta(6L, "4444BBB", LocalDate.of(2024, 1, 2), TipoMotor.GASOLINA, 300));
	        
	        for (Vehiculo v : vehiculos) {
	            if (v.getMatricula().equals(matricula)) {
	                return v;
	            }
	        }
	        return null;
		}
	};

	@BeforeEach
	public void setUp() {
		VistaFuncionario gui = new VistaFuncionario(info);
		demo = new FrameFixture(gui);
		gui.setVisible(true);	
	}

	@AfterEach
	public void tearDown() {
		demo.cleanUp();
	}

	@Test 
	public void test() { 
		demo.textBox("txtNombreContribuyente").requireText("Pepe Lopez Abascal"); 
		demo.button("btnBuscar").click(); 
		demo.textBox("txtDniContribuyente").setText("44444444A"); 
		demo.list("listaMatriculasVehiculos");
		demo.textBox("txtTotalContribuyente");

		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}