import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class EventoTest {
	/* Esto no me va
	@Before 
	public void setUp() {
		Evento e = new Evento("Estudiar", new Date(7,7,2004,15,30), new Date(8,7,2004,15,45),7.5f, null, true);
	}
	 */
	

	Evento e = new Evento("Estudiar", new Date(7,7,2004,15,30), new Date(8,7,2004,15,45),7.5f, null, true);
	
	@Test
	public void testGetNombre() {
		assertEquals("Estudiar", e.getNombre());;
	}
	@Test
	public void testSetNombre() {
		e.setNombre("Trabajo Estadística");
		assertEquals("Trabajo Estadística", e.getNombre());;
	}


}
