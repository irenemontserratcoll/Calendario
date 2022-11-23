package ventanas;


import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import clases.Categoria;

public class RendererCategoria extends JCheckBox implements ListCellRenderer<Categoria> {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Categoria> list, Categoria value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		setText(value.getCategoria());
		setForeground(value.getColor());
		setSelected(true);
		//TODO no se puede cambiar la seleccion en la ventana
		setEnabled(true);
		return this;
	}

	public RendererCategoria() {
		setOpaque(true);
	}
}