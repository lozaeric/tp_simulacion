import java.util.HashMap;


public class Estadistica {
	private HashMap<Integer, Integer> clientesSistema = new HashMap<Integer, Integer>  (), clientesCola = new HashMap<Integer, Integer>  ();
	private Sistema sistema;
	private double sumaSistema,sumaCola, sumaEnSistema, sumaEnCola;
	
	public Estadistica (Sistema sistema) {
		this.sistema = sistema;
	}

	public void iterar () {
		int clientesS = sistema.getClientes (), clientesC = sistema.getCola ().getClientes ();
		
		sumaSistema += clientesS;
		sumaCola += clientesC;
		if (clientesSistema.containsKey (clientesS))
			clientesSistema.put (clientesS, clientesSistema.get (clientesS)+1);
		else
			clientesSistema.put (clientesS, 1);
		
	}
	
	public double getProbN (int nClientes) {
		if (clientesSistema.containsKey (nClientes))
			return ((double) clientesSistema.get (nClientes))/sistema.getCronometro ().getTiempo ()/100;
		return 0;
	}
	
	public double getL () {
		return sumaSistema/sistema.getCronometro ().getTiempo ()/100;
	}
	
	public double getLq () {
		return sumaCola/sistema.getCronometro ().getTiempo ()/100;	
	}
	
	public void sumaTiempoCola (double tiempoCola) {
		sumaEnCola += tiempoCola;
	}
	
	public void sumaTiempoSistema (double tiempoSistema) {
		sumaEnSistema += tiempoSistema;
	}

	public double getWq () {
		return sumaEnCola/sistema.getClientesUnicos ();
	}
	
	public double getW () {
		return sumaEnSistema/sistema.getClientesUnicos ();
	}
	
	public String toString () {
	   return "Estadistica [contador=" + clientesSistema + "]";
   }
	
	
}
