package presentacion.views;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.beans.PropertyVetoException;

import javax.swing.JPanel;
import javax.swing.JFrame;

public class SupervisorControlView extends JInternalFrame {

	private static final long serialVersionUID = 4306672868994985561L;

	private static SupervisorControlView instance;
	
	public static SupervisorControlView getInstance() {
		if(instance == null) instance = new SupervisorControlView();
		return instance;
	}
	
	private SupervisorControlView() {
		try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setMaximizable(true);
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Supervisor control view");
		setBounds(100, 100, 859, 647);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel clientesPanel = new JPanel();
		tabbedPane.addTab("Clientes", null, clientesPanel, null);

		JPanel turnosPanel = new JPanel();
		tabbedPane.addTab("Turnos", null, turnosPanel, null);

		JPanel otPresupuestadasPanel = new JPanel();
		tabbedPane.addTab("OT presupuestadas", null, otPresupuestadasPanel, null);

		JPanel panel = new JPanel();
		tabbedPane.addTab("OT para cerrar", null, panel, null);

	}

	public void display() {
		setVisible(true);
	}

	public void close() {
		setVisible(false);
	}

	public void clearData() {
		// TODO Auto-generated method stub
		
	}
}
