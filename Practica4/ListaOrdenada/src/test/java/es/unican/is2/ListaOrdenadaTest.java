package es.unican.is2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

import es.unican.is2.adt.IListaOrdenada;

public class ListaOrdenadaTest {

    private IListaOrdenada<Integer> lista;

    @Before
    public void setUp() {
        lista = new ListaOrdenada<>();
    }

    @Test
    public void testGetLista10ElementosValoresValidos() {
        for (int i = 0; i < 10; i++) {
            lista.add(i);
        }

        assertEquals(Integer.valueOf(0), lista.get(0));
        assertEquals(Integer.valueOf(5), lista.get(5)); 
        assertEquals(Integer.valueOf(9), lista.get(9)); 
    }

    @Test
    public void testGetLista10ElementosValoresNoValidos() {
        for (int i = 0; i < 10; i++) {
            lista.add(i);
        }

        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-3)); 
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(10)); 
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(44));
    }

    @Test
    public void testGetListaVaciaValoresNoValidos() {
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-3)); 
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(1)); 
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(5)); 
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(10)); 
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(44)); 
    }
    
    @Test
    public void testAddValoresValidos() {
        lista.add(5);
        lista.add(3);
        lista.add(7);

        assertEquals(Integer.valueOf(3), lista.get(0));
        assertEquals(Integer.valueOf(5), lista.get(1));
        assertEquals(Integer.valueOf(7), lista.get(2));
    }

    @Test
    public void testAddElementoNulo() {
        assertThrows(NullPointerException.class, () -> lista.add(null));
    }
    
    @Test
    public void testRemoveValidosListaConElementos() {
        for (int i = 0; i < 10; i++) {
            lista.add(i);
        }

        assertEquals(Integer.valueOf(0), lista.remove(0));
        assertEquals(9, lista.size());
        assertEquals(Integer.valueOf(1), lista.get(0));

        assertEquals(Integer.valueOf(5), lista.remove(4));
        assertEquals(8, lista.size());
        assertEquals(Integer.valueOf(6), lista.get(4));

        assertEquals(Integer.valueOf(9), lista.remove(7)); 
        assertEquals(7, lista.size());
    }

    @Test
    public void testRemoveInvalidosListaConElementos() {
        for (int i = 0; i < 10; i++) {
            lista.add(i);
        }

        assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(-3));
        assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(10));
        assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(44));
    }

    @Test
    public void testRemoveInvalidosListaVacia() {
        assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(-3));
        assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(0));  
        assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(5)); 
    }
    
    @Test
    public void testSizeConListaLlena() {
        for (int i = 0; i < 10; i++) {
            lista.add(i);
        }

        assertEquals(10, lista.size());
    }

    @Test
    public void testSizeConListaVacia() {
        assertEquals(0, lista.size());
    }
    
    @Test
    public void testClearConListaLlena() {
        for (int i = 0; i < 10; i++) {
            lista.add(i);
        }

        lista.clear();

        assertEquals(0, lista.size());
    }

    @Test
    public void testClearConListaVacia() {
        lista.clear();

        assertEquals(0, lista.size());
    }
}
