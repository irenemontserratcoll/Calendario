package ventanas;

import java.awt.Color;
import java.awt.GridLayout;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class VentanaEvento extends JFrame {

	private static final long serialVersionUID = 1L;
	JLabel textoEvento;
	JTextField nombreEvento;
	JLabel horaIni;
	JLabel horaFin;
	public VentanaEvento() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Ventana Evento");
		setSize(400,500);
		JPanel principal = new JPanel(new GridLayout(6,1));
		
		//Titulo evento
		JPanel titulo = new JPanel();
		textoEvento = new JLabel("Evento");
		titulo.add(textoEvento);
		nombreEvento = new JTextField(20);
		titulo.add(nombreEvento);
		
		//Combo box con horas desde las 00 hasta las 23:30 (cada media hora)
		JPanel horas = new JPanel();
		horas.setBackground(Color.WHITE);
		List<String> horasl = new ArrayList<String>();
	
		for (int i=0;i<24;i++) {
			horasl.add(i + ":00");
			horasl.add(i + ":30");
		}
		JComboBox<Object> horasInicio = new JComboBox<Object>();
	
		for(int i=0;i<horasl.size();i++){
			horasInicio.addItem(horasl.get(i));
		}
		JComboBox<Object> horasFin = new JComboBox<Object>();
	
		for(int i=0;i<horasl.size();i++){
			horasFin.addItem(horasl.get(i));
		}	
		horaIni = new JLabel("Hora inicio");
		horaFin = new JLabel("Hora fin");
		horas.add(horaIni);
		horas.add(horasInicio);
		horas.add(horaFin);
		horas.add(horasFin);
	
	
	
	principal.add(titulo);
	principal.add(horas);
	add(principal);
	setVisible(true);
}

public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			new VentanaEvento();
		}
	});
}
}