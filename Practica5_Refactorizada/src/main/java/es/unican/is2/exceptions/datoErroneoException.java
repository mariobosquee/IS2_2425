package es.unican.is2.exceptions;



@SuppressWarnings("serial")
public class datoErroneoException extends RuntimeException {
	
	public datoErroneoException (String mensaje) {
		super(mensaje);
	}

}
