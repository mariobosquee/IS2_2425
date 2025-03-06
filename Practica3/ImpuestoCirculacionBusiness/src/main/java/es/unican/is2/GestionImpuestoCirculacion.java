package es.unican.is2;

import es.unican.is2.impuesto_circulacion.business.IGestionContribuyentes;
import es.unican.is2.impuesto_circulacion.business.IGestionVehiculos;
import es.unican.is2.impuesto_circulacion.business.IInfoImpuestoCirculacion;
import es.unican.is2.impuesto_circulacion.dao.IContribuyentesDAO;
import es.unican.is2.impuesto_circulacion.dao.IVehiculosDAO;
import es.unican.is2.impuesto_circulacion.domain.Contribuyente;
import es.unican.is2.impuesto_circulacion.domain.Vehiculo;

public class GestionImpuestoCirculacion implements IGestionContribuyentes, IGestionVehiculos, IInfoImpuestoCirculacion {
    
    private IContribuyentesDAO contribuyentesDAO;
    private IVehiculosDAO vehiculosDAO;
    
    public GestionImpuestoCirculacion(IContribuyentesDAO contribuyentesDAO, IVehiculosDAO vehiculosDAO) {
        this.contribuyentesDAO = contribuyentesDAO;
        this.vehiculosDAO = vehiculosDAO;
    }


    public Contribuyente altaContribuyente(Contribuyente c) {
        return null;
    }


    public Contribuyente bajaContribuyente(String dni) {
        return null;
    }


    public Vehiculo altaVehiculo(Vehiculo v, String dni) {
        return null;
    }


    public Vehiculo bajaVehiculo(String dni, String matricula) {
        return null;
    }


    public boolean cambiaTitularVehiculo(String matricula, String dniActual, String dniNuevo) {
        return false;
    }


    public Contribuyente contribuyente(String dni) {
        return null;
    }


    public Vehiculo vehiculo(String matricula) {
        return null;
    }
}
