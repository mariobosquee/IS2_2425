import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.Test;

import es.unican.is2.impuesto_circulacion.domain.TipoMotor;
import es.unican.is2.impuesto_circulacion.domain.Turismo;

public class TurismoTest {

	public static void main(String[] args) {
        long[] ids = {1, 9, 29}; 
        
        String[] matriculas = {"7293FMS", "S1829JA"};
        
        LocalDate[] fechasMatriculacion = {
            LocalDate.now(),
            LocalDate.now().minusDays(1),
            LocalDate.now().minusYears(1),
            LocalDate.now().minusYears(1).plusDays(1),
            LocalDate.now().minusYears(4),
            LocalDate.now().minusYears(4).plusDays(1),
            LocalDate.now().minusYears(25),
            LocalDate.now().minusYears(25).plusDays(1)
        };
        
        TipoMotor[] motores = {TipoMotor.GASOLINA, TipoMotor.DIESEL, TipoMotor.HIBRIDO, TipoMotor.ELECTRICO, TipoMotor.GAS}; 

        double[] potencias = {1, 4, 7, 8, 10, 11, 12, 14, 15, 16, 18, 19, 20, 27};


        for (int i = 0; i < potencias.length; i++) {
            long id = ids[i % ids.length];
            String matricula = matriculas[i % matriculas.length];
            LocalDate fechaMatriculacion = fechasMatriculacion[i % fechasMatriculacion.length]; 
            TipoMotor motor = motores[i % motores.length]; 
            double potencia = potencias[i];

            Turismo turismo = new Turismo(id, matricula, fechaMatriculacion, motor, potencia);

            assertEquals(id, turismo.getId());
            assertEquals(matricula, turismo.getMatricula());
            assertEquals(fechaMatriculacion, turismo.getFechaMatriculacion());
            assertEquals(motor, turismo.getMotor());
            assertEquals(potencia, turismo.getPotencia(), 0.01);
        }
        
    }
	
	@Test
    public void testMatriculaNull() {
        long id = 1;
        LocalDate fechaMatriculacion = LocalDate.now().minusYears(1);
        TipoMotor motor = TipoMotor.GASOLINA;
        double potencia = 1;

        String matriculaInvalida = null; 

        assertThrows(IllegalArgumentException.class, () -> {
            new Turismo(id, matriculaInvalida, fechaMatriculacion, motor, potencia);
        });
    }

    @Test
    public void testFechaMatriculacionInvalida1() {
        long id = 1;
        String matricula = "7293FMS";
        TipoMotor motor = TipoMotor.GASOLINA;
        double potencia = 1;

        LocalDate fechaInvalida = LocalDate.now().plusDays(1);

        assertThrows(IllegalArgumentException.class, () -> {
            new Turismo(id, matricula, fechaInvalida, motor, potencia);
        });
    }
    
    @Test
    public void testFechaMatriculacionInvalida2() {
        long id = 1;
        String matricula = "7293FMS";
        TipoMotor motor = TipoMotor.GASOLINA;
        double potencia = 1;

        LocalDate fechaInvalida = null;

        assertThrows(IllegalArgumentException.class, () -> {
            new Turismo(id, matricula, fechaInvalida, motor, potencia);
        });
    }

    @Test
    public void testMotorInvalido() {
        long id = 1;
        String matricula = "7293FMS";
        LocalDate fechaMatriculacion = LocalDate.now().minusYears(1);
        double potencia = 1;

        TipoMotor motorInvalido = null;

        assertThrows(IllegalArgumentException.class, () -> {
            new Turismo(id, matricula, fechaMatriculacion, motorInvalido, potencia);
        });
    }

    @Test
    public void testPotenciaInvalida1() {
        long id = 1;
        String matricula = "7293FMS";
        LocalDate fechaMatriculacion = LocalDate.now().minusYears(1);
        TipoMotor motor = TipoMotor.GASOLINA;

        double potenciaInvalida = -5;

        assertThrows(IllegalArgumentException.class, () -> {
            new Turismo(id, matricula, fechaMatriculacion, motor, potenciaInvalida);
        });
    }
    
}