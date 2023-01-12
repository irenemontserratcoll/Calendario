package clases;

import org.junit.Test;
import static org.junit.Assert.*;

public class UsuarioTest {

   

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

}