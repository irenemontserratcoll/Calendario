package ventanas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import baseDeDatos.GestorBaseDatos;
import clases.Evento;

public class ModeloTablaCalendario extends DefaultTableModel{
	private static int NUM_COLUMNAS = 8;
	private static int NUM_FILAS = 48;
	
	
	private static final long serialVersionUID = 1L;


	VentanaPrincipal ventana;
	//ZonedDateTime fecha = ventana.getFecha();
		
	public ModeloTablaCalendario(VentanaPrincipal ventana) {
		super();
		this.ventana = ventana;
		
		for(int i=0; i<NUM_FILAS; i++) {
	    	LocalTime tiempo = LocalTime.of(0, 0);
	    	this.setValueAt(tiempo.plusMinutes(i*30), i, 0);
	    }
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
		
		
		if (getColumnName(4).equals("MONDAY 9")) {
			System.out.println("Hoy es día 9");
			Evento prueba = new Evento("deporte");
			ventana.modelo.setValueAt("Hola", 8, 4);
			ventana.modelo.setValueAt(prueba.getNombre(), 9, 4);
			ventana.modelo.getValueAt(10, 4);

			
		}
		
		return " ";
	}
	
    @Override
    public Object getValueAt(int row, int column) {
        // como el modelo de datos es una lista de objetos en este caso
        // se obtiene la fila/objeto correspondiente
        // en función del número de columna se obtiene la propiedad
        Evento e = new Evento("deporte");

        return e.getNombre();
    }
    
 

}