package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.table.TableColumnModel;

import clases.Categoria;
import clases.Evento;


public class VentanaPrincipal extends JFrame {
	private static Logger logger = Logger.getLogger(Logger.class.getName());
	ZonedDateTime fecha = ZonedDateTime.now();
	
	JComboBox<Object> anyo;
	JComboBox<Object> mes;
	
	ModeloTablaCalendario modelo;
	JTable tablaCalendario;

	

	public ZonedDateTime getFecha() {
		return fecha;
	}


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

		
		mes = new JComboBox<Object>();
		mes.setModel(new DefaultComboBoxModel<Object>(Month.values()));
		mes.setSelectedItem(fecha.getMonth()); //Mes actual
		mes.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				Month mes = (Month) e.getItem();
				fecha = fecha.withMonth(mes.getValue());
				tablaCalendario.repaint();
				
				logger.info("Nueva fecha seleccionada: " + fecha);
			}
		});
		
		String[] anyos = new String[12];
		for (int i=0;i<12;i++) {
			int opcionAnyo = fecha.getYear()-5+i;
			anyos[i]= ""+ opcionAnyo;
		}
		anyo = new JComboBox<Object>(anyos);
		anyo.setSelectedItem(""+fecha.getYear()); //Mes actual
		anyo.addItemListener(new ItemListener() {		
			@Override
			public void itemStateChanged(ItemEvent e) {
				String a = (String) e.getItem();
				int sumaryAnyos = Integer.parseInt(a)-fecha.getYear();
				fecha = fecha.plusYears(sumaryAnyos);
				tablaCalendario.setModel(new ModeloTablaCalendario(VentanaPrincipal.this));
				tablaCalendario.setModel(new ModeloTablaCalendario(VentanaPrincipal.this));
				tablaCalendario.repaint();
				logger.info("Nueva fecha seleccionada: " + fecha);
			}
		});
		
		JButton siguiente = new JButton(">");
		siguiente.setFont(new Font("Tahoma", 0, 20));
		siguiente.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				fecha = fecha.plusDays(7);
				mes.setSelectedItem(fecha.getMonth());
				anyo.setSelectedItem(""+fecha.getYear());
				tablaCalendario.setModel(new ModeloTablaCalendario(VentanaPrincipal.this));
				tablaCalendario.repaint();
				logger.info("Nueva fecha seleccionada: " + fecha);
			}
		});
		
		JButton anterior = new JButton("<");
		anterior.setFont(new Font("Tahoma", 0, 20));
		anterior.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				fecha = fecha.minusDays(7);
				mes.setSelectedItem(fecha.getMonth());
				anyo.setSelectedItem(""+fecha.getYear());
				tablaCalendario.setModel(new ModeloTablaCalendario(VentanaPrincipal.this));
				tablaCalendario.repaint();
				logger.info("Nueva fecha seleccionada: " + fecha);
			}
		});
		
		
		panelTitulo.add(mes);
		panelTitulo.add(anyo);
		panelTitulo.add(titulo);
		panelTitulo.add(anterior);
		panelTitulo.add(siguiente);
		
		
//TABLA
		
		modelo = new ModeloTablaCalendario(this);
		tablaCalendario = new JTable(modelo);
		//TODO Actualizar tabla con la fecha seleccionada
		
		
		TableColumnModel columnModel = tablaCalendario.getColumnModel();
	    columnModel.getColumn(0).setPreferredWidth(60);

	    
	    JScrollPane panelCalendario = new JScrollPane(tablaCalendario);
		principal.add(panelTitulo, BorderLayout.NORTH);
		principal.add(panelCalendario, BorderLayout.CENTER);
		
		
		JPanel barraDerecha = new JPanel(new GridLayout(2,1));
		
		JPanel categorias = new JPanel();
		
		JLabel tituloCategorias = new JLabel("    Categorias ");
		tituloCategorias.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		
//Apartado lista de categorias
		
		List<clases.Categoria> listaCategorias = List.of(
				new Categoria("Estudiar", null, Color.BLUE),
				new Categoria("Deporte", null, Color.RED),
				new Categoria("Proyecto Programacion", null, Color.GREEN));
		
		DefaultListModel<Categoria> modeloCategorias = new DefaultListModel<>();
		modeloCategorias.addAll(listaCategorias);
		JList<Categoria> jListCategorias = new JList<>();
		jListCategorias.setModel(modeloCategorias);
		RendererCategoria renderer = new RendererCategoria();
		jListCategorias.addMouseListener(renderer);
		jListCategorias.setCellRenderer(renderer);
		
		categorias.setLayout(new BorderLayout());
		categorias.add(tituloCategorias, BorderLayout.NORTH);
		categorias.add(jListCategorias, BorderLayout.CENTER);
		
	
		//Apartado Tareas Pendientes
		
		JPanel pendientes = new JPanel();
		JLabel tituloPendientes = new JLabel(" Tareas pendientes ");
		tituloPendientes.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		List<clases.Evento> listaTareasPendientes = List.of(
				new Evento("Correr", listaCategorias.get(1),true),
				new Evento ("Saltar", listaCategorias.get(1) , false)			
				);
		
		DefaultListModel<Evento> modeloTareasPendientes = new DefaultListModel<>();
		modeloTareasPendientes.addAll(listaTareasPendientes);
		JList<Evento> jListTareasPendientes = new JList<>();
		jListTareasPendientes.setModel(modeloTareasPendientes);
		RendererPendientes renderer2 = new RendererPendientes();
		jListTareasPendientes.addMouseListener(renderer2);
		jListTareasPendientes.setCellRenderer(renderer2);
		
		pendientes.setLayout(new BorderLayout());
		pendientes.add(tituloPendientes, BorderLayout.NORTH);
		pendientes.add(jListTareasPendientes, BorderLayout.CENTER);
		
		
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