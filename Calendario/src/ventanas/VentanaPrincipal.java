package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

public class VentanaPrincipal extends JFrame {
	ZonedDateTime fecha = ZonedDateTime.now();
	

	private static final long serialVersionUID = 1L;
	public VentanaPrincipal() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Calendario");
		setSize(1600,1000);
		
		JSplitPane barra = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		barra.setBorder(null); //No se ve el borde
		barra.setResizeWeight(0.75); // Una parte ocupa este procentaje de panel
		barra.setEnabled(false); // El usuario no puede moverla
		barra.setDividerSize(0); //No se ve
		
		JPanel principal = new JPanel();
		principal.setLayout(new BorderLayout());
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(Color.YELLOW);
		JLabel titulo = new JLabel("Deusto Calendar");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 50));
		
		JComboBox mes = new JComboBox();
		mes.setModel(new DefaultComboBoxModel(Month.values()));
		mes.setSelectedItem(fecha.getMonth()); //Mes actual
		
		String[] anyos = new String[12];
		for (int i=0;i<12;i++) {
			int opcionAnyo = fecha.getYear()-5+i;
			anyos[i]= ""+ opcionAnyo;
		}
		JComboBox anyo = new JComboBox(anyos);
		anyo.setSelectedItem(""+fecha.getYear()); //Mes actual
		
		JButton siguiente = new JButton(">");
		siguiente.setFont(new Font("Tahoma", 0, 20));
		
		JButton anterior = new JButton("<");
		anterior.setFont(new Font("Tahoma", 0, 20));
		
		

		
		panelTitulo.add(mes);
		panelTitulo.add(anyo);
		panelTitulo.add(titulo);
		panelTitulo.add(anterior);
		panelTitulo.add(siguiente);
		
		
		JPanel panelCalendario = new JPanel();
		panelCalendario.setBackground(Color.RED);
		
		principal.add(panelTitulo, BorderLayout.NORTH);
		principal.add(panelCalendario, BorderLayout.CENTER);
		
		
		JPanel barraDerecha = new JPanel(new GridLayout(2,1));
		
		JPanel categorias = new JPanel();
		categorias.setBackground(Color.BLUE);
		
		
		JPanel pendientes = new JPanel();
		pendientes.setBackground(Color.LIGHT_GRAY);
		
		
		barraDerecha.add(categorias);
		barraDerecha.add(pendientes);
		barra.add(principal);
		barra.add(barraDerecha);
		add(barra);
		
		
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new VentanaPrincipal();
			}
		});
	}
}
