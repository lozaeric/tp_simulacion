import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Interfaz extends JFrame {
	private JTextField lambda = new JTextField (5), mu = new JTextField (5), nq = new JTextField (5), s = new JTextField (5), nSistemas = new JTextField (5), tiempoActual = new JTextField (6);
	private JTextArea estadistica = new JTextArea (12, 60);
	private JButton iniciar  = new JButton ("Iniciar"), detener = new JButton ("Detener"), reanudar_pausar = new JButton ("Reanudar/Pausar");
	private JLabel _lambda = new JLabel ("λ"), _mu = new JLabel ("µ"), _nq = new JLabel ("Nq"), _s = new JLabel ("Servidores"), _nSistemas = new JLabel ("Sistemas"), _tiempoActual = new JLabel ("Tiempo transcurrido");
	private Sistema sistemas[];
	private ActualizadorEstadistica actualizador;
	
	public Interfaz () { 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout (new FlowLayout ());
		setSize(825, 300);
		setTitle ("TP de Simulación y Modelización   -   Loza, Eric   Azzi, Federico   Martinho, Matias");
		
		iniciar.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int n = Integer.parseInt (nSistemas.getText()), __s = Integer.parseInt (s.getText()), __nq = Integer.parseInt(nq.getText());
				double __lambda = Double.parseDouble(lambda.getText()), __mu = Double.parseDouble(mu.getText());
				
				sistemas = new Sistema [n];
				for (int i=0; i<n; i++) {
					sistemas[i] = new Sistema (__lambda, __mu, __s);
					sistemas[i].getCola().setClientes(__nq);
					sistemas[i].getHilo ().start ();
				}
				if (actualizador==null)
					actualizador = new ActualizadorEstadistica ();
			}
			
		});
		reanudar_pausar.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (sistemas==null)
					return;
				int __nq = Integer.parseInt(nq.getText());
				double __lambda = Double.parseDouble(lambda.getText()), __mu = Double.parseDouble(mu.getText());
				
				for (Sistema s : sistemas) {
					if (s.getCronometro ().estaPausado ()) {
						s.getCola ().setClientes (__nq);
						s.getCola ().setLambda (__lambda);
						for (Servidor server : s.getServidores ())
							server.setMu (__mu);
					}
					s.reanudar_pausar ();
				}
			}
			
		});
		detener.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (sistemas==null)
					return;
				for (Sistema s : sistemas) 
					s.detener ();
				sistemas = null;
			}
			
		});
		estadistica.setEditable (false);
		tiempoActual.setEditable (false);
		estadistica.setBackground (new Color (180,240,180));
		add (_lambda);
		add (lambda);
		add (_mu);
		add (mu);
		add (_nq);
		add (nq);
		add (_s);
		add (s);
		add (_nSistemas);
		add (nSistemas);
		add (iniciar);
		add (detener);
		add (reanudar_pausar);
		add (_tiempoActual);
		add (tiempoActual);
		add (new JLabel ("segundos"));
		add (estadistica);
		setVisible(true);
	}
	
	public static void main (String args[]) {
		new Interfaz ();
	}
	
	private class ActualizadorEstadistica implements ActionListener
	{
		public ActualizadorEstadistica() {
			(new Timer(333,this)).start();
		}
		public void actionPerformed (ActionEvent e) {
			StringBuilder mostrar = new StringBuilder ();
			
			if (sistemas==null || sistemas[0].getCronometro ().estaPausado ()|| sistemas[0].getCronometro ().isTerminado ())
				return;
			mostrar.append ("P(0)\tP(1)\tP(2)\tL\tLq\tW\tWq\tNq\n");
			for (Sistema s: sistemas) 
				mostrar.append (s.getEstadistica ().toString ()+'\n');
			estadistica.setText (mostrar.toString ());
			tiempoActual.setText (String.valueOf (sistemas[0].getCronometro ().getTiempo ()));
		}
	}
}
