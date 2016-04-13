
public class Cronometro extends Iterador implements Runnable {
	private long tiempo = 0;
	private double velocidad;
	
	public Cronometro (Sistema d) {
		super (d);
	}	
	
	public void iterar () {
		while (true) {
			try {
		      Thread.sleep (1);
	      }
	      catch (InterruptedException err) {
		      err.printStackTrace();
	      }
			tiempo++;
			changed ();
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
	

}
