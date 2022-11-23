package ventanas;


import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import clases.Categoria;

public class RendererCategoria extends JCheckBox implements ListCellRenderer<Categoria>, MouseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Categoria> list, Categoria value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		setText(value.getCategoria());
		setForeground(value.getColor());
		setSelected(value.isActiva());
		
		return this;
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		//Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//  Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Auto-generated method stub
		
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
	}
}
