package ventanas;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import baseDeDatos.GestorBaseDatos;
import clases.Categoria;
import clases.StringColores;


public class VentanaCategoria extends JFrame {

	private static final long serialVersionUID = 1L;
	GestorBaseDatos baseDatosUsuarios;
	JLabel textoCategoria;
	JComboBox<String> categorias;
	Color colorCat;
	static Categoria categoria;
	
	public VentanaCategoria(String nombreUsuario) {
			
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Ventana Categoria");
		setSize(300, 350);
		JPanel principal = new JPanel(new GridLayout(10, 1));

		// Titulo evento
		JPanel titulo = new JPanel();
		textoCategoria = new JLabel("Seleccionar categoria existente");
		titulo.add(textoCategoria);
		
		// JComboBox
		
		JPanel comboBoxCat = new JPanel();
		
		List<Categoria> listaCategorias = GestorBaseDatos.todasCategorias(nombreUsuario);
		
		JComboBox<Categoria> categorias = new JComboBox<Categoria>();
		for (Categoria c : listaCategorias) {
			categorias.addItem(c);
		}
		comboBoxCat.add(categorias);
		
		//JButton para aceptar la categoria
		JPanel aceptar = new JPanel();
		JButton botonAceptar = new JButton("Aceptar");
		aceptar.add(botonAceptar);
		botonAceptar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				categoria = (Categoria) categorias.getSelectedItem();
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
		
		Color[] cc = {Color.decode(StringColores.AMARILLO.getName()),Color.decode(StringColores.AZUL.getName()),Color.decode(StringColores.GRIS.getName()),
				Color.decode(StringColores.MORADO.getName()),Color.decode(StringColores.ROSA.getName()),Color.decode(StringColores.VERDE.getName())};
		JComboBox<Color> colores = new JComboBox<Color>(cc);
		colores.setRenderer(new ColorListCellRenderer());			
		colorCategoria.add(colores);
		
		colores.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Color cl = (Color) colores.getSelectedItem();
				colorCat = cl;
				colores.setBackground(colorCat);
			}
		});
		
		//JButton para aceptar nueva categoria
		JPanel crear = new JPanel();
		JButton botoncrear = new JButton("Crear");
		crear.add(botoncrear);
		botoncrear.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nomCat = nombreCategoria.getText();
				categoria = new Categoria(nomCat,colorCat);
				anyadirCategoria(nombreUsuario,nomCat,colorCat);
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
	
	public void anyadirCategoria(String nombreUsuario, String nombreCategoria, Color color) {
		switch (GestorBaseDatos.buscarCategoria(nombreUsuario, nombreCategoria)) {
		case 0:
			JOptionPane.showMessageDialog(null, "Esta Categoria ya existe", "Error", JOptionPane.ERROR_MESSAGE);
		case 1:
			GestorBaseDatos.anyadirCategoria(nombreUsuario, nombreCategoria, color);
			JOptionPane.showMessageDialog(null, "Categoria creada", "Categoria creada", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private static class ColorListCellRenderer extends DefaultListCellRenderer {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
	    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	        label.setBackground((Color) value);
	        label.setText("  ");
	        return label;
	    }
	}
	

	
}
