package ventanas;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import clases.Categoria;

/**
 * Renderer del apartado de Categorias
 * Visualiza las categorias del usuario como un JCheckBox
 *
 */
public class RendererCategoria extends JCheckBox implements ListCellRenderer<Categoria>, MouseListener {

	private static final long serialVersionUID = 1L;
	private VentanaPrincipal ventanaPrincipal;
	
	public RendererCategoria(VentanaPrincipal ventanaPrincipal) {
		super();
		this.ventanaPrincipal = ventanaPrincipal;
	}
	@Override
	public Component getListCellRendererComponent(JList<? extends Categoria> list, Categoria value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		setSelected(value.isActiva());
		setText(value.getCategoria());
		setForeground(value.getColor());
		
		setFont(new Font("default", Font.PLAIN, 15));
		setBackground(Color.WHITE);
		return this;
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		//Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		@SuppressWarnings("unchecked")
		JList<Categoria> list = (JList<Categoria>) e.getSource();
		int index = list.locationToIndex(e.getPoint());
		Categoria c = list.getModel().getElementAt(index);
		c.setActiva(!c.isActiva());
		list.repaint();
		ventanaPrincipal.actualizarTabla();
	}
}
