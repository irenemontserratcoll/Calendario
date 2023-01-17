package ventanas;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import clases.Evento;

/**Renderer de la tabla de la Ventana principal. 
 * Renderiza los eventos a mostrarse como un label con el color de su categoria
 *
 */
public class RendererTabla  implements TableCellRenderer{

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		JLabel label = new JLabel();
		label.setHorizontalAlignment(JLabel.CENTER);
		if (value != null) {
			if (value.getClass().equals(Evento.class)) {
				Evento e = (Evento) value;
				label.setBackground(e.getCategoria().getColor());
				label.setText(e.getNombre());
				label.setOpaque(true);
			}
		}
		
		return label;
	}

}
