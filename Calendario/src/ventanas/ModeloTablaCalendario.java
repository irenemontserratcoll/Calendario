package ventanas;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import clases.Evento;

public class ModeloTablaCalendario extends DefaultTableModel{
	private static int NUM_COLUMNAS = 8;
	private static int NUM_FILAS = 48;
	
	List<Evento> eventosSemana = new ArrayList<>(); //Ahora esta en ventana Principal
	private static final long serialVersionUID = 1L;


	VentanaPrincipal ventana;
		
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
	
	
	
	public String getEventosSemana(List<String> diasPantalla) {
		System.out.println("GetEventosSemana");
		System.out.println(baseDeDatos.GestorBaseDatos.getEventosSemana("prueba" ,diasPantalla));
		
		//TODO esto que es? Borrar?
		if (getColumnName(4).equals("MONDAY 9")) {
			System.out.println("Hoy es día 9");
			Evento prueba = new Evento("deporte");
		}
		
		return " ";
	}
	
	public Object getValueAt(int row, int column) {
		if (column==0) {
			LocalTime tiempo = LocalTime.of(0, 0);
	    	return tiempo.plusMinutes(row*30);
		}else {
			for (Evento e: ventana.listaEventosVisibles) {
				if ( (column >= ColumnaInicioEvento(e, ventana.fecha) && column <= ColumnaFinEvento(e, ventana.fecha)) && (row >= FilaInicioEvento(e) && row <= FilaFinEvento(e)) ){
					return e;
					}
				}
			}
		return null;
	}
		
	public int ColumnaInicioEvento(Evento e, ZonedDateTime fecha) {
		int diaInicioEvento = e.getFechaInicio().getDayOfYear();
		int diaReferencia = fecha.getDayOfYear();
		int diferencia = diaInicioEvento-diaReferencia;
		int columna = 4+diferencia;
		if (columna <8 && columna >0) {
			return columna;
		}else {
			return 1;
		}
	}
	
	public int ColumnaFinEvento(Evento e, ZonedDateTime fecha) {
		
		int diaInicioEvento = e.getFechaFin().getDayOfYear();
		int diaReferencia = fecha.getDayOfYear();
		int diferencia = diaInicioEvento-diaReferencia;
		
		int columna = 4+diferencia;
		if (columna <8 && columna >0) {
			return columna;
		}else {
			return 7;
		}
	}
	
	public int FilaInicioEvento(Evento e) {
		int hora = e.getFechaInicio().getHour();
		int minuto = e.getFechaInicio().getMinute();
		int fila = hora*2;
		if (minuto>0) {
			fila+=1;
		}
		return fila;
	}
	
	public int FilaFinEvento(Evento e) {
		int hora = e.getFechaFin().getHour();
		int minuto = e.getFechaFin().getMinute();
		int fila = hora*2;
		if (minuto>0) {
			fila+=1;
		}
		return fila;
	}
}