package ventanas;

import java.awt.Color;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import clases.Categoria;

public class VentanaCategoria extends JFrame {

	private static final long serialVersionUID = 1L;
	JLabel textoCategoria;
	
	public VentanaCategoria() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Ventana Categoria");
		setSize(300, 200);
		JPanel principal = new JPanel();

		// Titulo evento
		JPanel titulo = new JPanel();
		textoCategoria = new JLabel("Categoria");
		titulo.add(textoCategoria);
		
		// JComboBox
		
		List<clases.Categoria> listaCategorias = List.of(
				new Categoria("Estudiar", null, Color.BLUE),
				new Categoria("Deporte", null, Color.RED),
				new Categoria("Proyecto Programacion", null, Color.GREEN));
		
		JComboBox<String> categorias = new JComboBox<String>();
		for (Categoria c : listaCategorias) {
			categorias.addItem(c.getCategoria());
		}

		principal.add(titulo);
		principal.add(categorias);
		
		
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
