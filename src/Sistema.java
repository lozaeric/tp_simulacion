
public class Sistema implements Director {
	private Cola cola;
	private Servidor servidores[];
	private Estadistica estadistica;
	private Cronometro cronometro;
	private Thread hilo;
	
	public Sistema (double lambda, double mu) {
		this.crearIteradores (lambda, mu, 1);
	}
	
	public Sistema (double lambda, double mu, int cantidadServidores) {
		this.crearIteradores (lambda, mu, cantidadServidores);
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

	public void crearIteradores (double lambda, double mu, int n) {
		cola = new Cola (this, lambda);
		estadistica = new Estadistica (this);
		servidores = new Servidor[n];
		for (int i=0; i<servidores.length; i++)
			servidores[i] = new Servidor (this, mu);
		cronometro = new Cronometro (this);
		hilo = new Thread (cronometro);
	}
	
	public void reanudar_pausar () {
		if (cronometro.estaDetenido ())
			cronometro.setDetenido (false);
		else
			cronometro.setDetenido (true);
	}
	
	public void detener () {
		hilo.interrupt ();
		cronometro.setTerminado (true);
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
	
	public Thread getHilo () {
		return hilo;
	}
}
