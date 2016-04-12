import java.util.ArrayList;


public class Cola extends Iterador {
	//private int clientes;
	private double lambda, llegadaProximo, llegadaUltimo;
	private ArrayList<Cliente> _clientes  = new ArrayList<Cliente>();

	public Cola (Sistema d, double lambda) {
		super (d);
		this.lambda = lambda;
		llegadaProximo = -Math.log (1-Math.random ())/this.lambda;
	}
	
	public void iterar () {
		double tiempoActual = getDirector ().getCronometro ().getTiempo ();
				
		if (tiempoActual-llegadaUltimo>llegadaProximo) {
			llegadaUltimo = tiempoActual;
			_clientes.add(new Cliente (llegadaUltimo));
			llegadaProximo = -Math.log (1-Math.random ())/lambda;
			changed ();
		}
	}
	
	public void setClientes (int n) {
		double tiempoActual = getDirector ().getCronometro ().getTiempo ();
		for (int i=0; i<n; i++) {
			llegadaUltimo = tiempoActual;
			_clientes.add(new Cliente (llegadaUltimo));
			changed ();
		}
	}
	
	public Cliente despacharCliente () {
		//double tiempoEnCola = getDirector ().getCronometro ().getTiempo ()-llegadaUltimo;
		
		//clientes--;
		_clientes.get(0).setLlegadaServidor(getDirector ().getCronometro ().getTiempo ());
		getDirector ().getEstadistica ().sumaTiempoCola (_clientes.get(0).getLlegadaServidor()-_clientes.get(0).getLlegadaSistema());
		return _clientes.remove(0);
	}
	
	public int getClientes () {
		return _clientes.size();
	}
	
	public Sistema getDirector () {
		return (Sistema) super.getDirector ();
	}

	public String toString () {
	   return "Cola [clientes=" + _clientes + ", llegadaUltimo=" + llegadaUltimo
	         + ", llegadaProximo=" + llegadaProximo + ", lambda=" + lambda + "]";
   }

	public double getLambda() {
		return lambda;
	}

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}
	
	
}
