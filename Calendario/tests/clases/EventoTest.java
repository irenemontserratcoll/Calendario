package clases;
import static org.junit.Assert.*;

import java.time.*;
import java.time.format.*;

import org.junit.Before;
import org.junit.Test;

public class EventoTest {
	Evento e;
	ZonedDateTime inicio;
	ZonedDateTime fin;
	Categoria categoria;
	
	@Before
	public void before() {
		inicio = ZonedDateTime.of(LocalDateTime.of(2022,11,4,18,58), ZoneId.of("Europe/Madrid"));
		fin = ZonedDateTime.of(LocalDateTime.of(2022,11,4,19,58), ZoneId.of("Europe/Madrid"));
		categoria = new Categoria("Universidad");
		e = new Evento("Estudiar", inicio, fin ,7.5f, categoria, true);
	}
	

	@Test
	public void testGetNombre() {
		assertEquals("Estudiar", e.getNombre());;
	}
	@Test
	public void testSetNombre() {
		e.setNombre("Trabajo Estadística");
		assertEquals("Trabajo Estadística", e.getNombre());;
	}
	
	@Test
	public void testGetFechaInicio() {
		assertEquals(inicio, e.getFechaInicio());
	}
	
	@Test
	public void testSetFechaInicio(){
		ZonedDateTime inicio2 = ZonedDateTime.of(LocalDateTime.of(1900,11,2,17,58), ZoneId.of("Europe/Madrid"));
		e.setFechaInicio(inicio2);
		assertEquals(inicio2, e.getFechaInicio());
	}
	
	@Test
	public void testSetFechaInicio2() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MMMM.yyyy hh:mm");
		e.setFechaInicio(2005, 2, 4, 5, 32);
		assertEquals("04.febrero.2005 05:32", e.getFechaInicio().format(dtf));
	}
	
	@Test
	public void testGetFechaFin() {
		assertEquals(fin, e.getFechaFin());
	}
	
	@Test
	public void testSetFechaFin(){
		ZonedDateTime fin2 = ZonedDateTime.of(LocalDateTime.of(1900,11,2,17,58), ZoneId.of("Europe/Madrid"));
		e.setFechaInicio(fin2);
		assertEquals(fin2, e.getFechaInicio());
	}
	
	@Test
	public void testSetFechaFin2() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MMMM.yyyy hh:mm");
		e.setFechaFin(2005, 2, 4, 5, 32);
		assertEquals("04.febrero.2005 05:32", e.getFechaFin().format(dtf));
	}
	
	@Test
	public void testSetFechaFin3() {
		ZonedDateTime fin3 = ZonedDateTime.of(LocalDateTime.of(1900,11,2,17,58), ZoneId.of("Europe/Madrid"));
		e.setFechaFin(fin3);
		assertEquals(fin3, e.getFechaFin());
	}
	
	@Test
	public void testSetFechaFin4() {
		ZonedDateTime fin4 = ZonedDateTime.of(LocalDateTime.of(2022,11,4,20,59), ZoneId.of("Europe/Madrid"));
		e.setFechaFin(121);
		assertEquals(fin4, e.getFechaFin());
	}
	
	@Test
	public void testGetDuracionReal() {
		assertEquals(7.5f, e.getDuracionReal(),0.0001);
	}
	
	@Test
	public void testSetDuracionReal() {
		e.setDuracionReal(2.3f);
		assertEquals(2.3f, e.getDuracionReal(),0.0001);
	}
	
	@Test
	public void testGetCategoria() {
		assertEquals(categoria, e.getCategoria());;
	}
	
	@Test
	public void testSetCategoria() {
		Categoria c = new Categoria("Universidad");
		e.setCategoria(c);
		assertEquals(c,e.getCategoria());
	}
	
	@Test
	public void testIsUrgente() {
		assertEquals(true, e.isUrgente());
	}
	
	@Test
	public void testSetUrgente() {
		e.setUrgente(false);
		assertEquals(false, e.isUrgente());
	}
	


}
