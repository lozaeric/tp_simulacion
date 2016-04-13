import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Interfaz extends JFrame {
	private JTextField lambda = new JTextField (5), mu = new JTextField (5), nq = new JTextField (5), s = new JTextField (5), nSistemas = new JTextField (5);
	private JButton iniciar  = new JButton ("Iniciar"), detener = new JButton ("Detener"), verEstadistica = new JButton ("Ver estadistica"), reanudar_pausar = new JButton ("Reanudar/Pausar");
	private JLabel _lambda = new JLabel ("λ"), _mu = new JLabel ("µ"), _nq = new JLabel ("Nq"), _s = new JLabel ("Servidores"), _nSistemas = new JLabel ("Sistemas");
	private Sistema sistemas[];
	
	public Interfaz () { 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout (new FlowLayout ());
		setSize(550, 130);
		setVisible(true);
		
		iniciar.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int n = Integer.parseInt (nSistemas.getText()), __s = Integer.parseInt (s.getText()), __nq = Integer.parseInt(nq.getText());
				double __lambda = Double.parseDouble(lambda.getText()), __mu = Double.parseDouble(mu.getText());
				
				sistemas = new Sistema [n];
				for (int i=0; i<n; i++) {
					sistemas[i] = new Sistema (__lambda, __mu, 1, __s);
					sistemas[i].getCola().setClientes(__nq);
				}
				for (Sistema s : sistemas) 
					s.getCronometro().run();
				
			}
			
		});
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
		add (verEstadistica);
	}
	
	public static void main (String args[]) {
		new Interfaz ();
	}
}
