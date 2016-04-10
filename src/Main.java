
public class Main {
	public static void main (String[] args) {
		Cronometro cronometro = new Cronometro (1);
		Cola cola = new Cola (1);
		Servidor servidores[] = new Servidor[] {new Servidor (5)};
		Sistema s = new Sistema (cola, servidores, cronometro);
		s.iniciar ();
	}
}
