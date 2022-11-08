package ventanas;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	public VentanaPrincipal() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Calendario");
		setSize(1600,1000);
		
		JSplitPane barra = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		barra.setBorder(null); //No se ve el borde
		barra.setResizeWeight(0.80); // Una parte ocupa este procentaje de panel
		barra.setEnabled(false); // El usuario no puede moverla
		barra.setDividerSize(0); //No se ve
		
		JPanel principal = new JPanel();
		principal.setBackground(Color.RED);
		JPanel barraDerecha = new JPanel(new GridLayout(2,1));
		
		JPanel categorias = new JPanel();
		categorias.setBackground(Color.BLUE);
		barraDerecha.add(categorias);
		
		JPanel pendientes = new JPanel();
		pendientes.setBackground(Color.LIGHT_GRAY);
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
