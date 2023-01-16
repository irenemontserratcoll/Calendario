package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import baseDeDatos.GestorBaseDatos;
import clases.Categoria;
import clases.Evento;
import clases.GestorEventos;


public class VentanaPrincipal extends JFrame {
	private GestorEventos gestorEventos;
	List<Evento> listaEventosVisibles = new ArrayList<Evento>();
	private static Logger logger = Logger.getLogger(Logger.class.getName());
	ZonedDateTime fecha = ZonedDateTime.now();
	
	JComboBox<Object> anyo;
	JComboBox<Object> mes;
	
	ModeloTablaCalendario modelo;
	JTable tablaCalendario;
    List<String> diasMuestraPantalla = new ArrayList<String>();
	

	public ZonedDateTime getFecha() {
		return fecha;
	}


	private static final long serialVersionUID = 1L;

	//La ventana Principal se crea a partir del gestor de eventos con los eventos de un usuario concreto.
	public VentanaPrincipal(GestorEventos gestorEventos, String nombreUsuario) {
		this.gestorEventos = gestorEventos;
		this.listaEventosVisibles = gestorEventos.getListaEventosSemanal(fecha);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Calendario");
		setSize(1600,1000);
		
		JSplitPane barra = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		barra.setBorder(null); //No se ve el borde
		barra.setResizeWeight(0.80); // Una parte ocupa este procentaje de panel
		barra.setEnabled(false); // El usuario no puede moverla
		barra.setDividerSize(0); //No se ve
		
		JPanel principal = new JPanel();
		principal.setLayout(new BorderLayout());
		
		JPanel panelTitulo = new JPanel();
		
		
		ImageIcon icono = new ImageIcon(VentanaUsuario.class.getResource("/Logobien2.0.jpg"));
		JLabel titulo = new JLabel(icono,JLabel.CENTER);


		mes = new JComboBox<Object>();
		mes.setModel(new DefaultComboBoxModel<Object>(Month.values()));
		mes.setSelectedItem(fecha.getMonth()); //Mes actual
		mes.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				Month mes = (Month) e.getItem();
				fecha = fecha.withMonth(mes.getValue());
				actualizarTabla();
				//tablaCalendario.repaint();
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
				actualizarTabla();
				tablaCalendario.setModel(new ModeloTablaCalendario(VentanaPrincipal.this));
				tablaCalendario.setModel(new ModeloTablaCalendario(VentanaPrincipal.this));
				//tablaCalendario.repaint();
			
				logger.info("Nueva fecha seleccionada: " + fecha);
			}
		});
		
		JButton siguiente = new JButton(">");
		siguiente.setFont(new Font("Tahoma", 0, 10));
		siguiente.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				fecha = fecha.plusDays(7);
				mes.setSelectedItem(fecha.getMonth());
				anyo.setSelectedItem(""+fecha.getYear());
				actualizarTabla();

				logger.info("Nueva fecha seleccionada: " + fecha);
				diasMuestraPantalla = new ArrayList<>();
			    for( int i=1 ; i<10 ; ++i ) {
			    	diasMuestraPantalla.add(modelo.getColumnName(i));
			    }
			}
		});
		
		JButton anterior = new JButton("<");
		anterior.setFont(new Font("Tahoma", 0, 10));
		anterior.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				fecha = fecha.minusDays(7);
				mes.setSelectedItem(fecha.getMonth());
				anyo.setSelectedItem(""+fecha.getYear());
				actualizarTabla();
				
				logger.info("Nueva fecha seleccionada: " + fecha);
				diasMuestraPantalla = new ArrayList<>();
			    for( int i=1 ; i<10 ; ++i ) {
			    	diasMuestraPantalla.add(modelo.getColumnName(i));
			    }
			}
		});
		// Botón crear evento
			JPanel bajo = new JPanel();
				JButton bEv = new JButton("Crear evento");
				bEv.addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent e) {
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								new VentanaEvento(gestorEventos, nombreUsuario);
								
							}
						});
						
					}
				});
					
		
		panelTitulo.add(mes);
		panelTitulo.add(anyo);
		panelTitulo.add(titulo);
		panelTitulo.add(anterior);
		panelTitulo.add(siguiente);
		panelTitulo.add(bajo);
		panelTitulo.setBackground(Color.WHITE);

		
		
//TABLA
		modelo = new ModeloTablaCalendario(this);
		tablaCalendario = new JTable(modelo);
	    for( int i=1 ; i<10 ; ++i ) {
	    	diasMuestraPantalla.add(modelo.getColumnName(i));
	    }
	    
	    RendererTabla rendererTabla = new RendererTabla();
	    for (int i=0; i<tablaCalendario.getColumnModel().getColumnCount();i++) {
	    	 tablaCalendario.getColumnModel().getColumn(i).setCellRenderer(rendererTabla);
	    }
	   // modelo.getEventosSemana(diasMuestraPantalla);
		
		DefaultTableCellRenderer alineadoCentro = new DefaultTableCellRenderer();
		alineadoCentro.setHorizontalAlignment(JLabel.CENTER);
		TableColumnModel columnModel = tablaCalendario.getColumnModel();
	    columnModel.getColumn(0).setMaxWidth(80);
	    columnModel.getColumn(0).setCellRenderer(alineadoCentro);	  	    
	    
	    
	    bajo.add(bEv);
	    
	    JScrollPane panelCalendario = new JScrollPane(tablaCalendario);
		principal.add(panelTitulo, BorderLayout.NORTH);
		principal.add(panelCalendario, BorderLayout.CENTER);
		principal.add(bajo, BorderLayout.SOUTH);
		
		JPanel barraDerecha = new JPanel(new GridLayout(2,1));
		
		JPanel categorias = new JPanel();
		
		JLabel tituloCategorias = new JLabel("    Categorias ");
		tituloCategorias.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		
//Apartado lista de categorias
		
		List<Categoria> listaCategorias = GestorBaseDatos.todasCategorias(nombreUsuario);
		
		DefaultListModel<Categoria> modeloCategorias = new DefaultListModel<>();
		modeloCategorias.addAll(listaCategorias);
		JList<Categoria> jListCategorias = new JList<>();
		jListCategorias.setModel(modeloCategorias);
		RendererCategoria renderer = new RendererCategoria();
		jListCategorias.addMouseListener(renderer);
		jListCategorias.setCellRenderer(renderer);
		
		categorias.setBackground(Color.WHITE);
		categorias.setLayout(new BorderLayout());
		categorias.add(tituloCategorias, BorderLayout.NORTH);
		categorias.add(jListCategorias, BorderLayout.CENTER);
		
	
//Apartado Tareas Pendientes
		
		JPanel pendientes = new JPanel();
		JLabel tituloPendientes = new JLabel(" Tareas pendientes ");
		tituloPendientes.setFont(new Font("Tahoma", Font.BOLD, 20));
		pendientes.setBackground(Color.WHITE);
		
		List<Evento> todosEventos = GestorBaseDatos.getListaEventosUsuario(nombreUsuario);
		List<Evento> tareasPendientes = new ArrayList<>() ;

		for (Evento eve : todosEventos) {
			if (eve.getFechaFin() == null) {
				tareasPendientes.add(eve);
			}
		}

		DefaultListModel<Evento> modeloTareasPendientes = new DefaultListModel<>();
		modeloTareasPendientes.addAll(tareasPendientes);
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
		
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//Cerrar la conexión
			}
		});
		
		setVisible(true);
		
	}
	
	public void actualizarTabla() {
		//actualizo los eventos
		listaEventosVisibles = gestorEventos.getListaEventosSemanal(fecha);
		
		//MODELO
		modelo = new ModeloTablaCalendario(this);
		tablaCalendario.setModel(modelo);
	    for( int i=1 ; i<10 ; ++i ) {
	    	diasMuestraPantalla.add(modelo.getColumnName(i));
	    }
	    
	    //RENDERER
	    RendererTabla rendererTabla = new RendererTabla();
	    for (int i=0; i<tablaCalendario.getColumnModel().getColumnCount();i++) {
	    	 tablaCalendario.getColumnModel().getColumn(i).setCellRenderer(rendererTabla);
	    }
		
		DefaultTableCellRenderer alineadoCentro = new DefaultTableCellRenderer();
		alineadoCentro.setHorizontalAlignment(JLabel.CENTER);
		TableColumnModel columnModel = tablaCalendario.getColumnModel();
	    columnModel.getColumn(0).setMaxWidth(80);
	    columnModel.getColumn(0).setCellRenderer(alineadoCentro);
		
	}
	
	
	
	
}