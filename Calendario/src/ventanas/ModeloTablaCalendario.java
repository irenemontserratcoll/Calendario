package ventanas;

import java.time.ZonedDateTime;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaCalendario extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	ZonedDateTime fecha;
	

	public ModeloTablaCalendario(ZonedDateTime fecha) {
		super();
		this.fecha = fecha;
	}
	@Override
	public String getColumnName(int col) {
		// Método para obtener el nombre de las columnas de la tabla
		if (col==0) {
			return "Dia";
		}else {
			ZonedDateTime fechaCol = fecha.minusDays(4-col);
			return fechaCol.getDayOfWeek() + " " +fechaCol.getDayOfMonth();
		}
	}
	@Override
	public int getRowCount() {
		return 49;
	}

	@Override
	public int getColumnCount() {
		return 8;
	}
	


/* con el extends abstractTableModel	
 * 
	private static final long serialVersionUID = 1L;
	ZonedDateTime fecha;

	public ModeloTablaCalendario(ZonedDateTime fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public String getColumnName(int col) {
		// Método para obtener el nombre de las columnas de la tabla
		if (col==0) {
			return "Dia";
		}else {
			ZonedDateTime fechaCol = fecha.minusDays(4-col);
			return ""+fechaCol.getDayOfWeek() + fechaCol.getDayOfYear();
		}
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 49;
	}

	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return columnIndex;
	}
	*/


}