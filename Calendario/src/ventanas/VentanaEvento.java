package ventanas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;

public class VentanaEvento extends JFrame {

	private static final long serialVersionUID = 1L;
	JLabel textoEvento;
	JTextField nombreEvento;
	JLabel horaIni;
	JLabel horaFin;
	JDatePicker depart_date_picker;
	JButton bEv;
	private JRadioButton si,no;

	public VentanaEvento() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Ventana Evento");
		setSize(400, 500);
		JPanel principal = new JPanel(new GridLayout(6, 1));

		// Titulo evento
		JPanel titulo = new JPanel();
		textoEvento = new JLabel("Evento");
		titulo.add(textoEvento);
		nombreEvento = new JTextField(20);
		titulo.add(nombreEvento);

		// Combo box con calendario para elegir día
		JPanel fechaini = new JPanel();
		JPanel fechafin = new JPanel();

		// Combo box con horas desde las 00 hasta las 23:30 (cada media hora)
		List<String> horasl = new ArrayList<String>();

		for (int i = 0; i < 24; i++) {
			horasl.add(i + ":00");
			horasl.add(i + ":30");
		}
		JComboBox<Object> horasInicio = new JComboBox<Object>();

		for (int i = 0; i < horasl.size(); i++) {
			horasInicio.addItem(horasl.get(i));
		}
		JComboBox<Object> horasFin = new JComboBox<Object>();

		for (int i = 0; i < horasl.size(); i++) {
			horasFin.addItem(horasl.get(i));
		}

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
		fechaini.add(horasInicio);
		fechafin.add(horaFin);
		fechafin.add((JComponent) picker1);
		fechafin.add(horasFin);
		
		// Categoria
		JPanel categoria = new JPanel();
		JLabel categ = new JLabel("Categoria");
		JTextField categorias = new JTextField(20);
		
		
		categoria.add(categ);
		categoria.add(categorias);
		//Urgente si/no
		ButtonGroup grupo = new ButtonGroup();
		JPanel urgente = new JPanel();
		JLabel urgente1 = new JLabel("¿Es urgente?");
		si = new JRadioButton("SI");
		no = new JRadioButton("NO");
		urgente.add(urgente1);
		grupo.add(si);
		grupo.add(no);
		urgente.add(si);
		urgente.add(no);
		// Botón crear evento
		JPanel boton = new JPanel();
		bEv = new JButton("Crear evento");

		bEv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (nombreEvento.getText().length()== 0) {
					JOptionPane.showMessageDialog(null, "Es necesario introducir título de evento", "error", JOptionPane.WARNING_MESSAGE);
				}else {
					
				}
			}
		});

		boton.add(bEv);

		principal.add(titulo);
		principal.add(fechaini);
		principal.add(fechafin);
		principal.add(categoria);
		principal.add(urgente);
		principal.add(boton);
		
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
