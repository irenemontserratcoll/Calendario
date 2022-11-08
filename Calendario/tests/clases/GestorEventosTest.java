package clases;
import static org.junit.Assert.*;

import java.util.ArrayList;


import org.junit.Before;
import org.junit.Test;

public class GestorEventosTest {

	private GestorEventos calendario12;

	@Before
	public void setUp() {
		ArrayList<Evento> listaPrueba = new ArrayList<Evento>();
		listaPrueba.add(new Evento("Comida"));
		calendario12 = new GestorEventos("Nahia", listaPrueba);
	}

	@Test
	public void testGetUsuario() {

		assertEquals("Nahia", calendario12.getUsuario());
	}

	@Test
	public void testGetListaEventos() {
		ArrayList<Evento> listaEsperada = new ArrayList<Evento>();
		listaEsperada.add(new Evento("Comida"));
		assertSame(listaEsperada.get(0).getNombre(), calendario12.getListaEventos().get(0).getNombre());
	}

	@Test
	public void testGetListaEventosUrgentes() {

		calendario12.getListaEventos().get(0).setUrgente(true);

		ArrayList<Evento> listaEsperada = new ArrayList<Evento>();
		listaEsperada.add(new Evento("Comida"));
		assertSame(listaEsperada.get(0).getNombre(), calendario12.getListaUrgente().get(0).getNombre());
	}

	@Test
	public void testGetListaEventosUrgentes1() {
		ArrayList<Evento> listaEsperada = new ArrayList<Evento>();
		assertEquals(listaEsperada, calendario12.getListaUrgente());
	}

	/*
	 * @Test public void testAddEventos() {
	 * (calendario12.getListaEventos()).addEvento(new Evento("Cena"));
	 * ArrayList<Evento> listaEsperada = new ArrayList<Evento>();
	 * listaEsperada.add(new Evento("Comida"));
	 * assertSame(listaEsperada.get(0).getNombre(),
	 * calendario12.getListaEventos().get(0).getNombre()); }
	 */

}
