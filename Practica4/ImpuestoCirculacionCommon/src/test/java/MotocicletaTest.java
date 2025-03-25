import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.Test;

import es.unican.is2.impuesto_circulacion.domain.Motocicleta;
import es.unican.is2.impuesto_circulacion.domain.TipoMotor;

public class MotocicletaTest {

    @Test
    public void testValoresValidos() {
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
        int[] cilindradas = {1, 36, 125, 126, 180, 250, 251, 354, 500, 777, 1000}; 

        for (int i = 0; i < cilindradas.length; i++) {
            long id = ids[i % ids.length];
            String matricula = matriculas[i % matriculas.length];
            LocalDate fechaMatriculacion = fechasMatriculacion[i % fechasMatriculacion.length];
            TipoMotor motor = motores[i % motores.length];
            int cilindrada = cilindradas[i];

            Motocicleta moto = new Motocicleta(id, matricula, fechaMatriculacion, motor, cilindrada);

            assertEquals(id, moto.getId());
            assertEquals(matricula, moto.getMatricula());
            assertEquals(fechaMatriculacion, moto.getFechaMatriculacion());
            assertEquals(motor, moto.getMotor());
            assertEquals(cilindrada, moto.getCilindrada());
        }
    }

    @Test
    public void testMatriculaInvalida() {
        long id = 1;
        LocalDate fechaMatriculacion = LocalDate.now().minusYears(1);
        TipoMotor motor = TipoMotor.GASOLINA;
        int cilindrada = 125;
        
        String matriculaInvalida = null; 

        assertThrows(IllegalArgumentException.class, () -> {
            new Motocicleta(id, matriculaInvalida, fechaMatriculacion, motor, cilindrada);
        });
    }

    @Test
    public void testFechaMatriculacionInvalida() {
        long id = 1;
        String matricula = "7293FMS";
        LocalDate fechaInvalida = LocalDate.now().plusDays(1); 
        TipoMotor motor = TipoMotor.GASOLINA;
        int cilindrada = 125;

        assertThrows(IllegalArgumentException.class, () -> {
            new Motocicleta(id, matricula, fechaInvalida, motor, cilindrada);
        });
    }

    @Test
    public void testMotorInvalido() {
        long id = 1;
        String matricula = "7293FMS";
        LocalDate fechaMatriculacion = LocalDate.now().minusYears(1);
        TipoMotor motorInvalido = null; 
        int cilindrada = 125;

        assertThrows(IllegalArgumentException.class, () -> {
            new Motocicleta(id, matricula, fechaMatriculacion, motorInvalido, cilindrada);
        });
    }

    @Test
    public void testCilindradaInvalida() {
        long id = 1;
        String matricula = "7293FMS";
        LocalDate fechaMatriculacion = LocalDate.now().minusYears(1);
        TipoMotor motor = TipoMotor.GASOLINA;
        
        int cilindradaInvalida = -100; 

        assertThrows(IllegalArgumentException.class, () -> {
            new Motocicleta(id, matricula, fechaMatriculacion, motor, cilindradaInvalida);
        });
    }
}
