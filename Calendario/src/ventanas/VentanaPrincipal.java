package ventanas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.Month;
import java.time.ZonedDateTime;

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
		barra.setResizeWeight(0.70); // Una parte ocupa este procentaje de panel
		barra.setEnabled(false); // El usuario no puede moverla
		barra.setDividerSize(0); //No se ve
		
		JPanel principal = new JPanel();
		principal.setLayout(new BorderLayout());
		
		JPanel panelTitulo = new JPanel();
		
		JLabel titulo = new JLabel("      Deusto Calendar     ");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 50));

		
		JComboBox<Object> mes = new JComboBox<Object>();
		mes.setModel(new DefaultComboBoxModel<Object>(Month.values()));
		mes.setSelectedItem(fecha.getMonth()); //Mes actual
		
		String[] anyos = new String[12];
		for (int i=0;i<12;i++) {
			int opcionAnyo = fecha.getYear()-5+i;
			anyos[i]= ""+ opcionAnyo;
		}
		JComboBox<Object> anyo = new JComboBox<Object>(anyos);
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
		JTable tablaCalendario = new JTable(15,8);
		
		panelCalendario.add(tablaCalendario);
		
		principal.add(panelTitulo, BorderLayout.NORTH);
		principal.add(panelCalendario, BorderLayout.CENTER);
		
		
		JPanel barraDerecha = new JPanel(new GridLayout(2,1));
		
		JPanel categorias = new JPanel();
		
		JLabel tituloCategorias = new JLabel("    Categorias ");
		tituloCategorias.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		
//		List<clases.Categoria> listaCategorias = List.of(
//				new Categoria("Estudiar", null, Color.BLUE),
//				new Categoria("Deporte", null, Color.RED),
//				new Categoria("Proyecto Programacion", null, Color.YELLOW));
//		
//		DefaultListModel<Categoria> modeloCategorias = new DefaultListModel<>();
//		modeloCategorias.addAll(listaCategorias);
//		JList<Categoria> jListCategorias = new JList<>();
//		jListCategorias.setCellRenderer(new RendererCategoria());
//		jListCategorias.setModel(modeloCategorias);
//		
		categorias.add(tituloCategorias);
//		categorias.add(jListCategorias);
				
		
		
		JPanel pendientes = new JPanel();
		JLabel tituloPendientes = new JLabel(" Tareas pendientes ");
		tituloPendientes.setFont(new Font("Tahoma", Font.BOLD, 20));
		pendientes.add(tituloPendientes);
		
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
