
public class Cronometro extends Thread {
	private long tiempo = 0;
	private Sistema sistema;
	private double velocidad;
	
	public Cronometro (double velocidad) {
		this.velocidad = velocidad;
	}	
	
	public void run () {
		while (true) {
			try {
		      Thread.sleep ((long) (10/velocidad));
	      }
	      catch (InterruptedException err) {
		      err.printStackTrace();
	      }
			tiempo++;
			sistema.iterar ();
		}
	}
	
	public double getTiempo () {
		return (double) tiempo/100;
	}
	
	public void setSistema (Sistema sistema) {
		this.sistema = sistema;
	}

	public String toString () {
	   return "Cronometro [tiempo=" + (double) tiempo/100 + "]";
   }
	

}
