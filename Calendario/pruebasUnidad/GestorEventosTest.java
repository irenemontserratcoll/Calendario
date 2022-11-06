import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class GestorEventosTest {
	
	/*
	 * public GestorEventos calendario1;
	 * 
	 * @Before public void setUp() { ArrayList<Evento> listaPrueba = new
	 * ArrayList<Evento>(); listaPrueba.add(new Evento("Comida")); GestorEventos
	 * calendario1 = new GestorEventos("Nahia", listaPrueba ); }
	 */
	
	@Test
	public void testGetUsuario() {
		ArrayList<Evento> listaPrueba = new ArrayList<Evento>();
		listaPrueba.add(new Evento("Comida"));
		GestorEventos calendario1 = new GestorEventos("Nahia", listaPrueba );
		assertEquals("Nahia", calendario1.getUsuario());
	}
	@Test
	public void testGetListaEventos() {
		ArrayList<Evento> listaPrueba = new ArrayList<Evento>();
		listaPrueba.add(new Evento("Comida"));
		GestorEventos calendario1 = new GestorEventos("Nahia", listaPrueba );
		ArrayList<Evento> listaEsperada = new ArrayList<Evento>();
		listaEsperada.add(new Evento("Comida"));
		assertSame(listaEsperada.get(0).getNombre(), calendario1.getListaEventos().get(0).getNombre());	
	}
	
}
