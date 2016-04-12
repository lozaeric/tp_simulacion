
public class Sistema implements Director {
	private Cola cola;
	private Servidor servidor;
	private Estadistica estadistica;
	private Cronometro cronometro;
	
	public Sistema (double lambda, double mu, double velocidad) {
		this.crearIteradores (lambda, mu, velocidad);
	}
	
	public void iteradorCambiado (Iterador i) {
		if (i.equals (cronometro)) {
			cola.iterar ();
			servidor.iterar ();
			estadistica.iterar ();
		}
		else if (i.equals (cola)) 
			estadistica.nuevoClienteUnico ();
		else if (i.equals (servidor)) {
			double tiempoEnCola = getCola ().despacharCliente ();
			getEstadistica ().sumaTiempoSistema (tiempoEnCola+getServidor().getTiempoAtencion ());
		}
	}

	public void crearIteradores (double lambda, double mu, double velocidad) {
		cola = new Cola (this, lambda);
		estadistica = new Estadistica (this);
		servidor = new Servidor (this, mu);
		cronometro = new Cronometro (this, velocidad);
	}

	public Cola getCola () {
		return cola;
	}

	public Servidor getServidor () {
		return servidor;
	}
	
	public Estadistica getEstadistica () {
		return estadistica;
	}
	
	public Cronometro getCronometro () {
		return cronometro;
	}

}
