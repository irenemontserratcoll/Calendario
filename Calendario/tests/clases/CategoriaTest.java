package clases;
import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class CategoriaTest {
	
	private Categoria cat = new Categoria( "Deporte",Color.BLACK);
	
	/**
	 * Me aseguro de que cada test es independiente. 
	 */
	@Before
	public void seUp() {
		//us= new Usuario ("Jon","2222");
		cat = new Categoria( "Deporte",Color.BLACK);
	}
	@Test
    public void testConstructorSinColor() {
        Categoria categoria = new Categoria("Prueba");
        assertEquals("Prueba", categoria.categoria);
        categoria.setCategoria("Correr");
        assertEquals("Correr", categoria.categoria);
        assertEquals(Color.LIGHT_GRAY, categoria.color);
        categoria.setActiva(true);
        assertEquals(true,categoria.isActiva());
    }
	@Test
    public void testGetUsuario() {
        Categoria categoria = new Categoria("Test", Color.BLACK);
        Usuario usuario = new Usuario("user", null);
        categoria.setColor(Color.red);
        assertEquals(Color.red, categoria.color);
        categoria.setUsuario(usuario);
        assertEquals(usuario, categoria.getUsuario());
    }
	@Test
	public void testToString() {
		Categoria categoria = new Categoria("Prueba");
		String esperado = "Prueba";
		assertEquals(esperado, categoria.toString());
	}
	@Test
    public void testGetCat() {
        assertEquals("Deporte", cat.getCategoria());
    }
	
	@Test
    public void testGetColor() {
    	assertEquals(Color.BLACK, cat.getColor());
    }

}
