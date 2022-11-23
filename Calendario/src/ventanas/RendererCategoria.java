package ventanas;


import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import clases.Categoria;

public class RendererCategoria extends JPanel implements ListCellRenderer<Categoria> {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Categoria> list, Categoria value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		setBackground(value.getColor());
		setLayout( new FlowLayout());
		JCheckBox seleccionarCategoria = new JCheckBox(value.getCategoria());
		seleccionarCategoria.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				System.out.println(value.getCategoria() + " "+ e.getStateChange());
				
			}
		});
		seleccionarCategoria.setEnabled(true);
		add(seleccionarCategoria);
		
		return this;
	}

	public RendererCategoria() {
		setOpaque(true);
	}
}
