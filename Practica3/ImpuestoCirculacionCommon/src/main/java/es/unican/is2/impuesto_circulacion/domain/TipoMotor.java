package es.unican.is2.impuesto_circulacion.domain;



public enum TipoMotor {
	
	GASOLINA(0), DIESEL(0), HIBRIDO(0.50), ELECTRICO(0.75), GAS (0.50);

	public final double descuentoImpuesto;

    private TipoMotor(double descuentoImpuesto) {
        this.descuentoImpuesto= descuentoImpuesto;
    }
    
    public double getDescuentoImpuesto() {
    	return descuentoImpuesto;
    }
	
}
