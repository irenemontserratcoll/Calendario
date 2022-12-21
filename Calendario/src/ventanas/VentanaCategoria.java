package ventanas;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import clases.Categoria;

public class VentanaCategoria extends JFrame {

	private static final long serialVersionUID = 1L;
	JLabel textoCategoria;
	
	public VentanaCategoria() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Ventana Categoria");
		setSize(300, 350);
		JPanel principal = new JPanel(new GridLayout(10, 1));

		// Titulo evento
		JPanel titulo = new JPanel();
		textoCategoria = new JLabel("Seleccionar categoria existente");
		titulo.add(textoCategoria);
		
		// JComboBox
		
		JPanel comboBoxCat = new JPanel();
		List<clases.Categoria> listaCategorias = List.of(
				new Categoria("Estudiar", null, Color.BLUE),
				new Categoria("Deporte", null, Color.RED),
				new Categoria("Proyecto Programacion", null, Color.GREEN));
		
		JComboBox<String> categorias = new JComboBox<String>();
		for (Categoria c : listaCategorias) {
			categorias.addItem(c.getCategoria());
		}
		comboBoxCat.add(categorias);
		
		//JButton para aceptar la categoria
		JPanel aceptar = new JPanel();
		JButton botonAceptar = new JButton("Aceptar");
		aceptar.add(botonAceptar);
		botonAceptar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
			}
		});
		
		JSeparator espacio = new JSeparator(JSeparator.HORIZONTAL);
		espacio.setBackground(Color.BLACK);
		
		JPanel vacio = new JPanel();

		//Panel categoria 
		JPanel subCat = new JPanel();
		JLabel subtitulo = new JLabel("Crear nueva categoria");
		subCat.add(subtitulo);
		
		JPanel CategoriaNueva = new JPanel();
		JLabel nombre = new JLabel("Categoria");
		CategoriaNueva.add(nombre);
		JTextField nombreCategoria = new JTextField(20);
		CategoriaNueva.add(nombreCategoria);
		
		JPanel colorCategoria = new JPanel();
		JLabel color = new JLabel("Color");
		colorCategoria.add(color);
		JComboBox<Color> colores = new JComboBox<Color>();
		colorCategoria.add(colores);
		
		//JButton para aceptar nueva categoria
		JPanel crear = new JPanel();
		JButton botoncrear = new JButton("Crear");
		crear.add(botoncrear);
		botoncrear.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		principal.add(titulo);
		principal.add(comboBoxCat);
		principal.add(aceptar);
		principal.add(vacio);
		principal.add(espacio);
		principal.add(subCat);
		principal.add(CategoriaNueva);
		principal.add(colorCategoria);
		principal.add(crear);
		
		add(principal);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	
	
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new VentanaCategoria();
			}
		});
	}
}
