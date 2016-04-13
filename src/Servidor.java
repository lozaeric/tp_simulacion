
public class Servidor extends Iterador {
	private boolean estaOcupado;
	private double llegadaUltimo, mu, tiempoAtencion;

	public Servidor (Sistema d, double mu) {
		super (d);
		this.mu = mu;
	}
	
	public void iterar () {
		double tiempoActual = getDirector().getCronometro ().getTiempo ();
		
		if (estaOcupado && tiempoActual-llegadaUltimo>tiempoAtencion)
			estaOcupado = false;
		
		if (getDirector().getCola ().getClientes ()>0 && !estaOcupado) {
			changed ();
			llegadaUltimo = tiempoActual;
			tiempoAtencion = -Math.log (1-Math.random ())/mu;
			estaOcupado = true;
		}
	}
	
	public boolean estaOcupado () {
		return estaOcupado;
	}
	
	public Sistema getDirector () {
		return (Sistema) super.getDirector ();
	}
	
	public double getTiempoAtencion () {
		return tiempoAtencion;
	}

	public String toString () {
	   return "Servidor [estaOcupado=" + estaOcupado + ", llegadaUltimo="
	         + llegadaUltimo + ", tiempoAtencion=" + tiempoAtencion + ", mu="
	         + mu + "]";
   }

	public double getMu() {
		return mu;
	}

	public void setMu(double mu) {
		this.mu = mu;
	}
	
	
}
