
public class Cola extends Iterador {
	private int clientes;
	private double lambda, llegadaProximo, llegadaUltimo;

	public Cola (Sistema d, double lambda) {
		super (d);
		this.lambda = lambda;
		llegadaProximo = -Math.log (1-Math.random ())/this.lambda;
	}
	
	public void iterar () {
		double tiempoActual = getDirector ().getCronometro ().getTiempo ();
				
		if (tiempoActual-llegadaUltimo>llegadaProximo) {
			llegadaUltimo = tiempoActual;
			llegadaProximo = -Math.log (1-Math.random ())/lambda;
			clientes++;
			changed ();
		}
	}
	
	public double despacharCliente () {
		double tiempoEnCola = getDirector ().getCronometro ().getTiempo ()-llegadaUltimo;
		
		clientes--;
		getDirector ().getEstadistica ().sumaTiempoCola (tiempoEnCola);
		return tiempoEnCola;
	}
	
	public int getClientes () {
		return clientes;
	}
	
	public Sistema getDirector () {
		return (Sistema) super.getDirector ();
	}

	public String toString () {
	   return "Cola [clientes=" + clientes + ", llegadaUltimo=" + llegadaUltimo
	         + ", llegadaProximo=" + llegadaProximo + ", lambda=" + lambda + "]";
   }
	
	
}
