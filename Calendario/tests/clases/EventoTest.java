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
	
	@Test
    public void testEvento2() {
		Evento evento2 = new Evento("Evento 2", new Categoria("IMPORTANTE"), true);
        assertEquals("Evento 2", evento2.getNombre());
        assertEquals("IMPORTANTE", evento2.getCategoria().toString());
        evento2.setActiva(false);
        assertFalse(evento2.isActiva());
        assertTrue(evento2.isUrgente());
    } 
	@Test
    public void testEvento3() {
		Evento evento2 = new Evento("Evento 3");
        assertEquals("Evento 3", evento2.getNombre());
        assertNull(evento2.getFechaInicio());
        assertNull(evento2.getFechaFin());
        String expectedString = evento2.getNombre()+ "" + evento2.fechaInicio + "" + evento2.fechaFin + "" + evento2.duracionReal + "" + evento2.categoria + "" + evento2.urgente + "" + evento2.activa;
        assertEquals(expectedString, evento2.toString());
    }
	@Test
	public void testEventoConstructor1() {
	    String nombre = "Evento";
	    int anyo = 2022;
	    int mes = 12;
	    int dia = 25;
	    int hora = 12;
	    int minuto = 0;
	    int duracionMinutos = 90;
	    Evento evento1 = new Evento(nombre, anyo, mes, dia, hora, minuto);
	    assertEquals(ZonedDateTime.of(LocalDateTime.of(anyo, mes, dia, hora, minuto), ZoneId.of("Europe/Madrid")), evento1.getFechaInicio());
	    Evento evento2 = new Evento(nombre, anyo, mes, dia, hora, minuto, duracionMinutos);

        LocalDateTime dt = LocalDateTime.of(anyo, mes, dia, hora, minuto);
        ZonedDateTime expectedFechaInicio = ZonedDateTime.of(dt, ZoneId.of("Europe/Madrid"));
        ZonedDateTime expectedFechaFin = expectedFechaInicio.plusMinutes(duracionMinutos);

        assertEquals(expectedFechaFin, evento2.getFechaFin());
        
    }
    
	}
	



