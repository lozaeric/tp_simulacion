
public class Main {
	public static void main (String[] args) {
		Sistema sistema = new Sistema (3,5,10, 2);
		Thread t = new Thread (sistema.getCronometro());
		t.run ();
	}
}
