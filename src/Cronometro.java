
public class Cronometro extends Iterador {
	private long tiempo = 0;
	private double velocidad;
	
	public Cronometro (Sistema d , double velocidad) {
		super (d);
		this.velocidad = velocidad;
	}	
	
	public void iniciar () {
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
	
	public double getTiempo () {
		return (double) tiempo/100;
	}
	
	public String toString () {
	   return "Cronometro [tiempo=" + (double) tiempo/100 + "]";
   }
	

}
