package es.unican.is2;

import java.util.List;

import es.unican.is2.impuesto_circulacion.business.IGestionContribuyentes;
import es.unican.is2.impuesto_circulacion.business.IGestionVehiculos;
import es.unican.is2.impuesto_circulacion.business.IInfoImpuestoCirculacion;
import es.unican.is2.impuesto_circulacion.dao.IContribuyentesDAO;
import es.unican.is2.impuesto_circulacion.dao.IVehiculosDAO;
import es.unican.is2.impuesto_circulacion.domain.Contribuyente;
import es.unican.is2.impuesto_circulacion.domain.Vehiculo;
import es.unican.is2.impuesto_circulacion.exceptions.DataAccessException;
import es.unican.is2.impuesto_circulacion.exceptions.OperacionNoValidaException;

public class GestionImpuestoCirculacion implements IGestionContribuyentes, IGestionVehiculos, IInfoImpuestoCirculacion {
    
    private IContribuyentesDAO contribuyentesDAO;
    private IVehiculosDAO vehiculosDAO;
    
    public GestionImpuestoCirculacion(IContribuyentesDAO contribuyentesDAO, IVehiculosDAO vehiculosDAO) {
        this.contribuyentesDAO = contribuyentesDAO;
        this.vehiculosDAO = vehiculosDAO;
    }
    

    /**
	 * Registra un nuevo contribuyente
	 * @param c Contribuyente que desea registrar
	 * @return El contribuyente registrado
	 * 		   null si no se registra porque ya existe un 
	 *              contribuyente con el mismo dni
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
    public Contribuyente altaContribuyente(Contribuyente c) throws DataAccessException {
    	if (contribuyentesDAO.contribuyente(c.getDni()) != null) {
    		return null;
    	}
        return contribuyentesDAO.creaContribuyente(c);
    }
    

    /**
	 * Elimina el contribuyente cuyo dni se indica
	 * @param dni DNI del contribuyente que se quiere eliminar
	 * @return El contribuyente eliminado
	 * 		   null si no se elimina porque no se encuentra 
	 * @throws OperacionNoValidaException si el contribuyente existe 
	 *         pero tiene vehiculos a su nombre
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
    public Contribuyente bajaContribuyente(String dni) throws OperacionNoValidaException, DataAccessException {
    	Contribuyente cont = contribuyentesDAO.contribuyente(dni);
    	if (cont == null ) {
    		return null;
    	}
    	else if (cont.getVehiculos().isEmpty() != true) {
    		throw new OperacionNoValidaException("El contribuyente tiene vehiculos a su nombre");
    	}
        return contribuyentesDAO.eliminaContribuyente(dni);
    }


    /**
	 * Registra un nuevo vehiculo y lo asocia al contribuyente con el dni indicado
	 * @param v Vehiculo que desea registrar
	 * @param dni DNI del contribuyente
	 * @return El vehiculo ya registrado
	 * 		   null si no se registra porque no se encuentra el contribuyente
	 * @throws OperacionNoValidaException si ya existe un vehiculo con la misma matricula
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
    public Vehiculo altaVehiculo(Vehiculo v, String dni) throws DataAccessException {
    	Contribuyente cont = contribuyentesDAO.contribuyente(dni);
    	if (cont == null ) {
    		return null;
    	}
    	else if (vehiculosDAO.vehiculoPorMatricula(v.getMatricula()) != null) {
    		throw new OperacionNoValidaException("Ya existe un vehiculo con esa matricula");
    	}
        return vehiculosDAO.creaVehiculo(v);
    }


    /**
	 * Elimina el vehiculo cuya matricula se indica y 
	 * que pertenece al contribuyente cuyo dni se indica
	 * @param matricula Matricula del coche a eliminar
	 * @param dni DNI del propietario del vehiculo
 	 * @return El vehiculo eliminado
 	 *         null si el vehiculo o el propietario no existen
 	 * @throws OperacionNoValidaException si el vehiculo no pertenece al dni indicado
 	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
    public Vehiculo bajaVehiculo(String dni, String matricula) throws DataAccessException {
    	Contribuyente cont = contribuyentesDAO.contribuyente(dni);
    	Vehiculo vehi = vehiculosDAO.vehiculoPorMatricula(matricula);
    	if (cont == null || vehi == null) {
    		return null;
    	}
    	else if (cont.buscaVehiculo(matricula) == null) {
    		throw new OperacionNoValidaException("El vehiculo no pertenece al contribuyente indicado");
    	}
        return vehiculosDAO.eliminaVehiculo(matricula);
    }


    /**
	 * Cambia el propietario del vehiculo indicado
	 * @param matricula Matricula del vehiculo
	 * @param dniActual DNI del propietario actual del vehiculo
	 * @param dniNuevo DNI del nuevo propietario del vehiculo
	 * @return true si se realiza el cambio
	 *         false si no realiza el cambio porque el vehiculo o los contribuyentes no existen
	 * @throws OperacionNoValidaException si el vehiculo no pertenece al dni indicado
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
    public boolean cambiaTitularVehiculo(String matricula, String dniActual, String dniNuevo) throws DataAccessException {
    	Contribuyente contActual = contribuyentesDAO.contribuyente(dniActual);
    	Contribuyente contNuevo = contribuyentesDAO.contribuyente(dniNuevo);
    	Vehiculo vehi = vehiculosDAO.vehiculoPorMatricula(matricula);
    	if (contActual == null || contNuevo == null || vehi == null) {
    		return false;
    	}
    	else if (contActual.buscaVehiculo(matricula) == null) {
    		throw new OperacionNoValidaException("El vehiculo no pertenece al contribuyente indicado como actual");
    	}
    	contActual.getVehiculos().remove(vehi);
    	contNuevo.getVehiculos().add(vehi);
    	contribuyentesDAO.actualizaContribuyente(contNuevo);
    	contribuyentesDAO.actualizaContribuyente(contActual);
    	vehiculosDAO.actualizaVehiculo(vehi);
        return true;
    }

    
    /**
	 * Retorna el contribuyente cuyo dni se indica
	 * @param dni DNI del contribuyente buscado
	 * @return El contribuyente correspondiente al dni
	 * 		   null si no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
    public Contribuyente contribuyente(String dni) throws DataAccessException {
    	List<Contribuyente> contribuyentes = contribuyentesDAO.contribuyentes();
    	for (Contribuyente c: contribuyentes) {
    		if (c.getDni() == dni) {
    			return c;
    		}
    	}
        return null;
    }


    /**
	 * Retorna el vehiculo cuya matricula se indica
	 * @param matricula Matricula del vehiculo buscado
	 * @return El vehiculo correspondiente a la matricula
	 * 	       null si no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
    public Vehiculo vehiculo(String matricula) throws DataAccessException {
    	List<Vehiculo> vehiculos = vehiculosDAO.vehiculos();
    	for (Vehiculo v: vehiculos) {
    		if (v.getMatricula() == matricula) {
    			return v;
    		}
    	}
        return null;
    }
}
