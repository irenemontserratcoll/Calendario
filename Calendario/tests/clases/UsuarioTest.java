package clases;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class UsuarioTest {
	public Usuario user1;
	public Usuario user2;
	public Usuario user3;
	public Usuario user4;
   
	@Before
	public void setUp() {
		user1 = new Usuario("Nahia", "password");
        user2 = new Usuario("Nahia", "password");
        user3 = new Usuario("Prueba", "password");
        user4 = new Usuario("Nahia", "pass");
	}
    @Test
    public void testSetNombre() {
        Usuario usuario = new Usuario("Juan", "1234");
        usuario.setNombre("Pedro");
        assertEquals("Pedro", usuario.getNombre());
    }

    @Test
    public void testSetContraseña() {
        Usuario usuario = new Usuario("Juan", "1234");
        usuario.setContraseña("4321");
        assertEquals("4321", usuario.getContraseña());
    }
    @Test
    public void testHashCode() {
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1.hashCode(), user3.hashCode());
        assertNotEquals(user1.hashCode(), user4.hashCode());
    }

    @Test
    public void testEquals() {
    	
    	assertTrue(user1.equals(user2));
    	assertTrue(user1.equals(user1));
        assertFalse(user1.equals(user3));
        assertFalse(user1.equals(user4));
        assertFalse(user1.equals(null));
        assertFalse(user1.equals("John"));
    }
}