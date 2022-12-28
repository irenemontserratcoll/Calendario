package ventanas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
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
		JButton categ = new JButton("Categoria");
		categ.addActionListener( new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						new VentanaCategoria();
					}
				});
			}
		});
				
		
		categoria.add(categ);
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
				}if (picker.getModel().getValue() != null){
					Calendar fecha1 = (Calendar) picker.getModel().getValue();
					Calendar fecha2 = (Calendar) picker1.getModel().getValue();
					if (fecha2.compareTo(fecha1)<0) {
						JOptionPane.showMessageDialog(null, "La fecha de inicio tiene que ser anterior a la fecha de fin", "error", JOptionPane.WARNING_MESSAGE);
					} 
					
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
		
		setLocationRelativeTo(null);
		add(principal);
		setVisible(true);
	}
	
	//YO CREO QUE ESTO NO HACE FALTA PORQUE AL FINAL
	//LLAMAMOS DESDE UNA VENTANA A LA OTRA 

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				new VentanaEvento();
//			}
//		});
//	}
}
