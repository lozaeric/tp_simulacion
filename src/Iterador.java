
public abstract class Iterador {
	private Director d;
	public Iterador (Director d) {
		this.d = d;
	}
	public void changed () {
		d.iteradorCambiado (this);
	}
	public abstract void iterar ();
	
 	public Director getDirector () {
		return d;
	}
}
