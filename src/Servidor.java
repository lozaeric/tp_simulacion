
public class Servidor {
	private boolean estaOcupado;
	private double llegadaUltimo, mu, tiempoAtencion;
	private Sistema sistema;
	
	public Servidor (double mu) {
		this.mu = mu;
	}
	
	public void setSistema (Sistema sistema) {
		this.sistema = sistema;
	}
	
	public void iterar () {
		double tiempoActual = sistema.getCronometro ().getTiempo (), tiempoEnCola;
		
		if (estaOcupado && tiempoActual-llegadaUltimo>tiempoAtencion)
			estaOcupado = false;
		
		if (sistema.getCola ().getClientes ()>0 && !estaOcupado) {
			llegadaUltimo = tiempoActual;
			tiempoAtencion = -Math.log (1-Math.random ())/mu;
			tiempoEnCola = sistema.getCola ().despacharCliente ();
			sistema.getEstadistica ().sumaTiempoSistema (tiempoEnCola+tiempoAtencion);
			estaOcupado = true;
		}
	}
	
	public boolean estaOcupado () {
		return estaOcupado;
	}

	public String toString () {
	   return "Servidor [estaOcupado=" + estaOcupado + ", llegadaUltimo="
	         + llegadaUltimo + ", tiempoAtencion=" + tiempoAtencion + ", mu="
	         + mu + "]";
   }
	
	
}
