
public class Iterador {
	private Director d;
	public Iterador (Director d) {
		this.d = d;
	}
	public void changed () {
		d.iteradorCambiado (this);
	}
	public Director getDirector () {
		return d;
	}
}
