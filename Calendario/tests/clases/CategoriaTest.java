package clases;
import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CategoriaTest {
	
	private Usuario us = new Usuario ("Jon","2222");
	private Categoria cat = new Categoria( "Deporte",us,Color.BLACK);
	
//	/**
//	 * Me aseguro de que cada test es independiente. 
//	 */
//	@Before
//	public void seUp() {
//		us= new Usuario ("Jon","2222");
//		cat = new Categoria( "Deporte",us,Color.BLACK);
//	}
	
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
	
//	@SuppressWarnings("deprecation")
//   @Rule
//   public ExpectedException expected = ExpectedException.none();
	
//	@Test
//   public void testInvalidArguments() {
//		expected.expect(IllegalArgumentException.class);
//        new Categoria(null,us,Color.BLACK);
//        new Categoria("Deporte",null,Color.BLACK);
//        new Categoria("Deporte",us,null);
//        new Categoria("",us,Color.BLACK);
//    }
//	
//	@Test
//    public void testEquals() {
//    	Categoria samecat = new Categoria("Deporte",us,Color.BLACK);
//        assertTrue(cat.equals(samecat));
//
//        Categoria differentCat = new Categoria("Jugar",us,Color.BLACK);
//        assertFalse(cat.equals(differentCat));
//
//        differentCat = new Categoria("Jugar",us,Color.GREEN);
//        assertFalse(cat.equals(differentCat));
//    }

}
