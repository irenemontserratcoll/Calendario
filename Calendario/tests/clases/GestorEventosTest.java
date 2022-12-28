package clases;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import baseDeDatos.GestorBaseDatos;

public class GestorEventosTest {

	private GestorEventos calendario12;

	@Before
	public void setUp() {
		ArrayList<Evento> listaPrueba = new ArrayList<Evento>();
		listaPrueba.add(new Evento("Comida"));
		GestorBaseDatos g = new GestorBaseDatos();
		g.iniciar();
		calendario12 = new GestorEventos("Nahia",g );
		
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
		calendario12.addEvento(new Evento("Comida", null,false));
		calendario12.getListaEventos().get(0).setUrgente(true);
		
		List<Evento> listaEsperada = new ArrayList<Evento>();
		listaEsperada.add(new Evento("Comida", null, true));
		
		assertEquals(listaEsperada.get(0).getNombre(), calendario12.getListaUrgente().get(0).getNombre());
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
