package ventanas;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import clases.Evento;

/**Render del apartado tareas pendientes
 * Renderiza como un JCheckBox las tareas pendientes
 */
public class RendererPendientes  extends JCheckBox implements ListCellRenderer<Evento>, MouseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Evento> list, Evento value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		setText(value.getNombre());
		setSelected(value.isActiva());

		return this;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		@SuppressWarnings("unchecked")
		JList<Evento> list = (JList<Evento>) e.getSource();
		int index = list.locationToIndex(e.getPoint());
		Evento e1 = list.getModel().getElementAt(index);
		e1.setActiva(!e1.isActiva());
		list.repaint();		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
