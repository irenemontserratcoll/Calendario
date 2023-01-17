package baseDeDatos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import clases.Categoria;
import clases.Evento;
import clases.StringColores;

public class GestorBaseDatosTest {
	private static Connection conn;

	private static GestorBaseDatos baseDatos;

	@Before
	public void SetUp() {
		baseDatos = new GestorBaseDatos();
		GestorBaseDatos.iniciar();
	}
	@Test
	public void testIniciar() {
	    boolean result = GestorBaseDatos.iniciar();
	    assertTrue(result);
	}

	    @Test
	    public void testClose() {
	        GestorBaseDatos.iniciar();
	        GestorBaseDatos.close();
	        assertTrue(conn == null);
	    }
	    @Test
	    public void testCorrectLogin() {
	        int result = baseDatos.loginCorrecto("PRUEBA", "PRUEBA");
	        assertEquals(0, result);
	    }

	    @Test
	    public void testIncorrectPassword() {
	        int result = baseDatos.loginCorrecto("username", "wrongpassword");
	        assertEquals(1, result);
	    }

	    @Test
	    public void testNonExistentUsername() {
	        int result = baseDatos.loginCorrecto("nonexistent", "password");
	        assertEquals(2, result);
	    }

	    @Test
	    public void testSuccessfulAddition() {
	        int result = baseDatos.anyadirUsuarioBD("prueba1", "password");
	        assertEquals(0, result);
	    }

	    @Test
	    public void testEmptyUsername() {
	        int result = baseDatos.anyadirUsuarioBD("", "password");
	        assertEquals(1, result);
	    }

	    @Test
	    public void testEmptyPassword() {
	        int result = baseDatos.anyadirUsuarioBD("username", "");
	        assertEquals(1, result);
	    }

	    @Test
	    public void testDuplicateUsername() {
	        int result = baseDatos.anyadirUsuarioBD("username", "password");
	        assertEquals(2, result);
	    }

	    @Test
	    public void testCategoriaEncontrada() {
	    	Categoria cat = new Categoria("Deportes");
	        int resultado = GestorBaseDatos.buscarCategoria("PRUEBA", cat.getCategoria());
	        assertEquals(0, resultado);
	    }

	    @Test
	    public void testCategoriaNoEncontrada() {
	        int resultado = GestorBaseDatos.buscarCategoria("PRUEBA", "noExiste");
	        assertEquals(1, resultado);
	    }

	    @Test
	    public void testUsuarioNoEncontrado() {
	        int resultado = GestorBaseDatos.buscarCategoria("Noexiste", "prueba");
	        assertEquals(3, resultado);
	    }
	    @Test
	    public void testAñadirCategoria() {
	        // Añadir una nueva categoría
	    	GestorBaseDatos.anyadirCategoria("prueba1", "Leer", Color.BLUE);
	        List<Categoria> categorias = GestorBaseDatos.todasCategorias("prueba1");
	        boolean encontrado = false;
	        for (Categoria c : categorias) {
	            if (c.getCategoria().equals("Leer") && c.getColor() == Color.BLUE) {
	                encontrado = true;
	                break;
	            }
	        }
	        assertTrue(encontrado);
	    }
	    //VA A DAR SIEMPRE MAL PORQUE CADA VEZ QUE SE EJECUTE EL METODO ANTERIOR SE AÑADE UNA NUEVA CATEGORIA
	    @Test
	    public void testTodasCategorias() {
	        List<Categoria> categorias = GestorBaseDatos.todasCategorias("PRUEBA");
	        assertEquals(8, categorias.size());
	        ArrayList<Evento> eventos = GestorBaseDatos.getListaEventosUsuario("prueba1");
	        assertEquals(0, eventos.size());
	    }

	    @Test
	    public void testGetCategoriaDeNombre() {

	        Categoria actualCategoria = GestorBaseDatos.getCategoriaDeNombre("PRUEBA", "Deporte");

	        assertNotNull(actualCategoria);
	        assertEquals("Deporte", actualCategoria.getCategoria());
	        assertEquals(Color.decode(StringColores.ROSA.getName()), actualCategoria.getColor());
	    }
	    @Test
	    public void testGetEventosSemana() {
	        String nombreUsuario = "PRUEBA";
	        List<String> diasMostrados = Arrays.asList("Monday 15", "Tuesday 16", "Wednesday 17");
	        List<Evento> expectedEventos = new ArrayList<Evento>();

	        Categoria cat = new Categoria("Ev1", Color.decode("#000000"));
	        LocalDateTime lInicio = LocalDateTime.of(2022, 11, 15, 10, 0);
	        ZonedDateTime fechaInicio = ZonedDateTime.of(lInicio, ZoneId.of("Europe/Madrid"));
	        LocalDateTime lFin = LocalDateTime.of(2022, 11, 15, 12, 0);
	        ZonedDateTime fechaFin = ZonedDateTime.of(lFin, ZoneId.of("Europe/Madrid"));
	        Evento e1 = new Evento(nombreUsuario, fechaInicio, fechaFin, 2, cat, true);
	        expectedEventos.add(e1);
	        
	        lInicio = LocalDateTime.of(2022, 11, 16, 9, 0);
	        fechaInicio = ZonedDateTime.of(lInicio, ZoneId.of("Europe/Madrid"));
	        lFin = LocalDateTime.of(2022, 11, 16, 11, 0);
	        fechaFin = ZonedDateTime.of(lFin, ZoneId.of("Europe/Madrid"));
	        Evento e2 = new Evento(nombreUsuario, fechaInicio, fechaFin, 2, cat, false);
	        expectedEventos.add(e2);
	        
	        List<Evento> actualEventos = GestorBaseDatos.getEventosSemana(nombreUsuario, diasMostrados);
	        
	        assertNotNull(actualEventos);

	        
	    }
	    @Test
	    public void testGetTareasPendientes() {
	        String nombreUsuario = "prueba1";
	        List<Evento> actualEventos =GestorBaseDatos.getTareasPendientes(nombreUsuario);
	        assertNotNull(actualEventos);
	        assertEquals(0, actualEventos.size());
	    }
	    
	    @Test
	    public void testCargarPrueba() {

	        GestorBaseDatos.cargarPrueba();

	        GestorBaseDatos.getListaEventosUsuario("PRUEBA2");

	        
	    }
	 
	  
}

