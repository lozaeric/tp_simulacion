import java.util.Arrays;


public class Sistema {
	private int clientes;
	private long clientesUnicos;
	private Cronometro cronometro;
	private Cola cola;
	private Servidor servidores[];
	private Estadistica estadistica;	
	private double mu,lambda;
	
	
	public Sistema (Cola cola, Servidor servidores[], Cronometro cronometro) {
		super ();
		this.cronometro = cronometro;
		this.cronometro.setSistema (this);
		this.cola = cola;
		this.cola.setSistema (this);
		this.servidores = servidores;
		for (Servidor servidor : servidores)
			servidor.setSistema (this);
		estadistica = new Estadistica (this);
	}
	
	public void iniciar () {
		cronometro.start ();
	}
	
	public void iterar () {
		cola.iterar ();
		clientes = cola.getClientes ();
		for (Servidor servidor : servidores)
			clientes += servidor.estaOcupado ()? 1:0;
		estadistica.iterar ();
		for (Servidor servidor : servidores)
			servidor.iterar ();
		//System.out.println (toString ());
		System.out.println ("P0="+estadistica.getProbN (0)+", L="+estadistica.getL ()+", Lq="+estadistica.getLq ()+", W="+estadistica.getW()+", Wq="+estadistica.getWq());
	}
	
	public Cronometro getCronometro () {
		return cronometro;
	}
	
	public int getClientes () {
		return clientes;
	}
	
	public long getClientesUnicos () {
		return clientesUnicos;
	}
	
	public void nuevoClienteUnico () {
		clientesUnicos++;
	}
	
	public Cola getCola () {
		return cola;
	}
	
	public Estadistica getEstadistica () {
		return estadistica;
	}

	public String toString () {
	   return "Sistema [cronometro=" + cronometro + ", cola=" + cola
	         + ", servidor=" + Arrays.toString (servidores) + ", estadistica=" + estadistica + "]";
   }
	
	
}
