package es.unican.is2;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.domain.CuentaValores;
import es.unican.is2.domain.Valor;

public class CuentaValoresTest {
	
	private CuentaValores sut;
	
	@BeforeEach
	public void inicializa() {
		sut = new CuentaValores("794311");
	}
	
	@Test
	public void testConstructor() {
		assertEquals("794311", sut.getNumCuenta());
		assertEquals(0, sut.getValores().size());
	}
	
	@Test
	public void testAnhadeValor() {
		// CASOS VALIDOS
		Valor v = new Valor("Telepizza", 25, 1.05);
		assertTrue(sut.anhadeValor(v));
		assertEquals(1, sut.getValores().size());
		assertEquals(sut.getValores().get(0), v);
		
		v = new Valor("BancoSantander", 100, 200);
		assertTrue(sut.anhadeValor(v));
		assertEquals(2, sut.getValores().size());
		assertEquals(sut.getValores().get(1), v);
		
		// CASOS NO VALIDOS
		assertFalse(sut.anhadeValor(new Valor("Telepizza", 10, 2.5)));
		
	}
}
