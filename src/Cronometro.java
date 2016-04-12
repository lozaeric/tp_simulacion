
public class Cronometro extends Iterador implements Runnable {
	private long tiempo = 0;
	private double velocidad;
	
	public Cronometro (Sistema d , double velocidad) {
		super (d);
		this.velocidad = velocidad;
	}	
	
	public void iterar () {
		while (true) {
			try {
		      Thread.sleep ((long) (10/velocidad));
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
		return (double) tiempo/100;
	}
	
	public String toString () {
	   return "Cronometro [tiempo=" + (double) tiempo/100 + "]";
   }
	

}
