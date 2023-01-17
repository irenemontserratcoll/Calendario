package ventanas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

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

import clases.Categoria;
import clases.Evento;
import clases.GestorEventos;

public class VentanaEvento extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Logger.class.getName());

	JLabel textoEvento;
	JTextField nombreEvento;
	JLabel horaIni;
	JLabel horaFin;
	JDatePicker depart_date_picker;
	JButton bEv;
	private JRadioButton si,no;
	private GestorEventos gestor;
	VentanaCategoria v;
	Categoria categoria;
	VentanaEvento VentanaEvento;
	VentanaPrincipal ventanaPrincipal;

	/**Constructor de la ventana para Crear nuevos eventos
	 * @param gestor
	 * @param nombreUsuario
	 */
	public VentanaEvento(GestorEventos gestor, String nombreUsuario, VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal=ventanaPrincipal;
		this.gestor=gestor;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
		
		JComboBox<String> horasInicio = new JComboBox<String>();
		JComboBox<String> horasFin = new JComboBox<String>();

		for (int i = 0; i < horasl.size(); i++) {
			horasInicio.addItem(horasl.get(i));
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
						VentanaEvento.this.categoria = VentanaCategoria.categoria;
						v = new VentanaCategoria(nombreUsuario);
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
		JPanel boton = new JPanel();
		bEv = new JButton("Crear evento");// Botón crear evento

		bEv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (nombreEvento.getText().length()== 0) {
					JOptionPane.showMessageDialog(null, "Es necesario introducir título de evento", "error", JOptionPane.WARNING_MESSAGE);
				}else if (picker.getModel().getValue() != null){
					Calendar fecha1 = (Calendar) picker.getModel().getValue();
					Calendar fecha2 = (Calendar) picker1.getModel().getValue();
					if (fecha2.compareTo(fecha1)<0) {
						JOptionPane.showMessageDialog(null, "La fecha de inicio tiene que ser anterior a la fecha de fin", "error", JOptionPane.WARNING_MESSAGE);
					}else { //Evento correcto
						//Fecha inicio
						Instant f1 = fecha1.toInstant();
						String a1 = (String) horasInicio.getSelectedItem();
						String[] ae = a1.split(":");
						int hora = Integer.parseInt(ae[0]);
						int minuto = Integer.parseInt(ae[1]);
						ZonedDateTime z1 = ZonedDateTime.ofInstant(f1, ZoneId.systemDefault());
						z1 = z1.plusHours(hora).plusHours(12);
						z1 =z1.plusMinutes(minuto);
						
						//Fecha fin
						Instant f2 = fecha2.toInstant();
						String a2 = (String) horasFin.getSelectedItem();
						String[] ae2 = a2.split(":");
						int hora2 = Integer.parseInt(ae2[0]);
						int minuto2 = Integer.parseInt(ae2[1]);
						ZonedDateTime z2 = ZonedDateTime.ofInstant(f2, ZoneId.systemDefault());	
						z2 = z2.plusHours(hora2).plusHours(12);
						z2 =z2.plusMinutes(minuto2);
						VentanaEvento.this.categoria = VentanaCategoria.categoria;

						Boolean urgente = si.isCursorSet();
						float duracion = 0.0F;
						Evento eventos = new Evento(nombreEvento.getText(),z1 , z2,duracion , VentanaEvento.this.categoria, urgente);
						gestor.addEvento(eventos);
						ventanaPrincipal.actualizarTabla();
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
		logger.info("Abierta ventana creación de evento");
	}
	
}
