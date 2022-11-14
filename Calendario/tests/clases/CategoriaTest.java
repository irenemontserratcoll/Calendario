package clases;
import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class CategoriaTest {
	
	private Usuario us = new Usuario ("Jon","2222");
	private Categoria cat = new Categoria( "Deporte",us,Color.BLACK);
	
	/**
	 * Me aseguro de que cada test es independiente. 
	 */
	@Before
	public void seUp() {
		us= new Usuario ("Jon","2222");
		cat = new Categoria( "Deporte",us,Color.BLACK);
	}
	
	@Test
    public void testGetCat() {
        assertEquals("Deporte", cat.getCategoria());
    }
	@Test
    public void testGetUsuario() {
        assertEquals(us, cat.getAutorizados());
    }
	@Test
    public void testGetColor() {
    	assertEquals(Color.BLACK, cat.getColor());
    }

}