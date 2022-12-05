package ventanas;

import java.time.ZonedDateTime;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaCalendario extends DefaultTableModel{
	private static int NUM_COLUMNAS = 8;
	private static int NUM_FILAS = 48;
	
	
	private static final long serialVersionUID = 1L;


	VentanaPrincipal ventana;
	//ZonedDateTime fecha = ventana.getFecha();
		
	public ModeloTablaCalendario(VentanaPrincipal ventana) {
		super();
		this.ventana = ventana;
	}
	
	
	@Override
	public String getColumnName(int col) {
		// Método para obtener el nombre de las columnas de la tabla
		if (col==0) {
			return "Dia";
		}else {
			ZonedDateTime fechaCol = ventana.getFecha().minusDays(4-col);
			return fechaCol.getDayOfWeek() + " " +fechaCol.getDayOfMonth();
		}
	}
	
	public String setColumnName(int col) {
		if (col==0) {
			return "Dia";
		}else {
			ZonedDateTime fechaCol = ventana.getFecha().minusDays(4-col);
			return fechaCol.getDayOfWeek() + " " +fechaCol.getDayOfMonth();
		}
	}
	@Override
	public int getRowCount() {
		return NUM_FILAS;
	}

	@Override
	public int getColumnCount() {
		return NUM_COLUMNAS;
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
