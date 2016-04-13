
public class Cronometro extends Iterador implements Runnable {
	private long tiempo = 0;
	private boolean detenido, terminado;
	
	public Cronometro (Sistema d) {
		super (d);
	}	
	
	public void iterar () {
		while (!terminado) {
			try {
		      Thread.sleep (1);
	      }
	      catch (InterruptedException err) {
		      //err.printStackTrace();
	      }
			if (!detenido) {
				tiempo++;
				changed ();
			}
		}
	}
	
	public void run () {
		iterar ();
	}
	
	public double getTiempo () {
		return (double) tiempo/1000;
	}
	
	public String toString () {
	   return "Cronometro [tiempo=" + (double) tiempo/1000 + "]";
   }

	public boolean estaDetenido () {
		return detenido;
	}

	public void setDetenido (boolean detenido) {
		this.detenido = detenido;
	}

	public boolean isTerminado () {
		return terminado;
	}

	public void setTerminado (boolean terminado) {
		this.terminado = terminado;
	}
	
	
	
}
