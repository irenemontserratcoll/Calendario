package ventanas;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


public class VentanaEvento extends JFrame {

	private static final long serialVersionUID = 1L;
	JLabel textoEvento;
	JTextField nombreEvento;
	JLabel horaIni;
	JLabel horaFin;
	JDatePicker depart_date_picker;

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
		
		//Combo box con calendario para elegir día
		JPanel fechaini = new JPanel();
		JPanel fechafin = new JPanel();
		 JDatePicker picker = new JDateComponentFactory().createJDatePicker();
		    picker.setTextEditable(true);
		    picker.setShowYearButtons(true);

		 JDatePicker picker1 = new JDateComponentFactory().createJDatePicker();
		 	picker1.setTextEditable(true);
		    picker1.setShowYearButtons(true);

		horaIni = new JLabel("Fecha inicio");
		horaFin = new JLabel("Fecha fin");
		fechaini.add(horaIni);
		fechaini.add((JComponent) picker);
		fechafin.add(horaFin);
	    fechafin.add((JComponent) picker1);

	
		
	principal.add(titulo);
	principal.add(fechaini);
	principal.add(fechafin);
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