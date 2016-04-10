
public class Cola {
	private int clientes;
	private double lambda, llegadaProximo, llegadaUltimo;
	private Sistema sistema;
	
	public Cola (double lambda) {
		this.lambda = lambda;
		llegadaProximo = -Math.log (1-Math.random ())/this.lambda;
	}
	
	public void iterar () {
		double tiempoActual = sistema.getCronometro ().getTiempo ();
		
		if (tiempoActual-llegadaUltimo>llegadaProximo) {
			sistema.nuevoClienteUnico ();
			llegadaUltimo = tiempoActual;
			llegadaProximo = -Math.log (1-Math.random ())/lambda;
			clientes++;
		}
	}
	
	public void setSistema (Sistema sistema) {
		this.sistema = sistema;
	}
	
	public double despacharCliente () {
		double tiempoEnCola = sistema.getCronometro ().getTiempo ()-llegadaUltimo;
		
		clientes--;
		sistema.getEstadistica ().sumaTiempoCola (tiempoEnCola);
		return tiempoEnCola;
	}
	
	public int getClientes () {
		return clientes;
	}

	public String toString () {
	   return "Cola [clientes=" + clientes + ", llegadaUltimo=" + llegadaUltimo
	         + ", llegadaProximo=" + llegadaProximo + ", lambda=" + lambda + "]";
   }
	
	
}
