import java.text.DecimalFormat;
import java.util.HashMap;


public class Estadistica extends Iterador {
	private HashMap<Integer, Integer> clientesSistema = new HashMap<Integer, Integer>  ();
	private double sumaSistema,sumaCola, sumaEnSistema, sumaEnCola;
	private int clientesUnicos;
	
	public Estadistica (Director d) {
	   super (d);
   }
	
	public void iterar () {
		int clientesS = 0, clientesC = getDirector ().getCola ().getClientes (), clientesServidor = 0;
		
		for (Servidor s :  getDirector().getServidores()) 
			clientesServidor += s.estaOcupado ()? 1:0;
		clientesS = getDirector().getCola ().getClientes () + clientesServidor;
		sumaSistema += clientesS;
		sumaCola += clientesC;
		if (clientesSistema.containsKey (clientesS))
			clientesSistema.put (clientesS, clientesSistema.get (clientesS)+1);
		else
			clientesSistema.put (clientesS, 1);
	}
	
	public void nuevoClienteUnico () {
		clientesUnicos++;
	}
	
	public double getProbN (int nClientes) {
		if (clientesSistema.containsKey (nClientes))
			return ((double) clientesSistema.get (nClientes))/getDirector().getCronometro ().getTiempo ()/1000;
		return 0;
	}
	
	public double getL () {
		return sumaSistema/getDirector().getCronometro ().getTiempo ()/1000;
	}
	
	public double getLq () {
		return sumaCola/getDirector().getCronometro ().getTiempo ()/1000;	
	}
	
	public void sumaTiempoCola (double tiempoCola) {
		sumaEnCola += tiempoCola;
	}
	
	public void sumaTiempoSistema (double tiempoSistema) {
		sumaEnSistema += tiempoSistema;
	}

	public double getWq () {
		return sumaEnCola/clientesUnicos;
	}
	
	public double getW () {
		return sumaEnSistema/clientesUnicos;
	}
	
	public String toString () {
		DecimalFormat tresDigitos=new DecimalFormat("0.000"); 
		return tresDigitos.format (getProbN (0))+"\t"+tresDigitos.format (getProbN (1))+"\t"+tresDigitos.format (getProbN (2))+"\t"+tresDigitos.format (getL ())+"\t"+tresDigitos.format (getLq ())+"\t"+tresDigitos.format (getW())+"\t"+tresDigitos.format (getWq())+"\t"+tresDigitos.format (getDirector().getCola ().getClientes ());
	}
	
	public Sistema getDirector () {
		return (Sistema) super.getDirector ();
	}
	
}
