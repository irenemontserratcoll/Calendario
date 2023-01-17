package clases;
import static org.junit.Assert.*;

import java.awt.Color;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

public class CategoriaTest {
	
	private Categoria cat = new Categoria( "Deporte",Color.BLACK);
	private Categoria cat1 = new Categoria("Correr", Color.red);
	private Categoria cat2 = new Categoria("Correr", Color.red);
	/**
	 * Me aseguro de que cada test es independiente. 
	 */

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
	@Test
	public void testEquals() {
	    Categoria cat1 = new Categoria("Correr", Color.red);
	    Categoria cat2 = new Categoria("Correr", Color.red);
	    assertTrue(cat1.equals(cat2));
	}

	@Test
	public void testEquals1() {
	   
	    assertFalse(cat.equals(cat2));
	}

	@Test
	public void testEquals2() {
	    String catPrueba = "Correr";
	    assertFalse(cat1.equals(catPrueba));

	    assertFalse(cat1.equals(null));
	    assertTrue(cat1.equals(cat1));

	}
	@Test
	public void testHashCode_ReturnsExpectedValue() {

	    assertEquals(cat1.hashCode(), cat2.hashCode());
	}

}
