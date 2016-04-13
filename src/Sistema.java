
public class Sistema implements Director {
	private Cola cola;
	private Servidor servidores[];
	private Estadistica estadistica;
	private Cronometro cronometro;
	
	public Sistema (double lambda, double mu, double velocidad) {
		this.crearIteradores (lambda, mu, velocidad, 1);
	}
	
	public Sistema (double lambda, double mu, double velocidad, int cantidadServidores) {
		this.crearIteradores (lambda, mu, velocidad, cantidadServidores);
	}
	
	public void iteradorCambiado (Iterador i) {
		if (i.equals (cronometro)) {
			cola.iterar ();
			for (Servidor s : servidores)
				s.iterar ();
			estadistica.iterar ();
		}
		else if (i.equals (cola)) 
			estadistica.nuevoClienteUnico ();
		else {
			for (Servidor s : servidores) {
				if (s.equals(i)) {
					Cliente c = cola.despacharCliente ();
					c.setSalidaSistema(cronometro.getTiempo()+s.getTiempoAtencion());
					getEstadistica ().sumaTiempoSistema (c.getSalidaSistema()-c.getLlegadaSistema());
				}
			}
		}
	}

	public void crearIteradores (double lambda, double mu, double velocidad, int n) {
		cola = new Cola (this, lambda);
		estadistica = new Estadistica (this);
		servidores = new Servidor[n];
		for (int i=0; i<servidores.length; i++)
			servidores[i] = new Servidor (this, mu);
		cronometro = new Cronometro (this, velocidad);
	}

	public Cola getCola () {
		return cola;
	}

	public Servidor[] getServidores () {
		return servidores;
	}
	
	public Estadistica getEstadistica () {
		return estadistica;
	}
	
	public Cronometro getCronometro () {
		return cronometro;
	}
}
