package clases;

import static org.junit.Assert.*;




import org.junit.Test;

import recursividad.GeneradorContraseñas;
import recursividad.GeneradorContraseñas.CharType;

public class GeneradorContraseñasTest {


	@Test
	public void CharTypeTest() {
		CharType char1 = CharType.letras;
		assertEquals(CharType.valueOf("letras"), char1);
	}
	@Test
	public void getLetraAlTest() {
	  
	      char letra = GeneradorContraseñas.getLetraAl();
	      int entero = Character.getNumericValue(letra);
	      assertTrue(entero<36);
	      assertTrue(entero>10);
	}
	@Test
	public void getNumAlTest() {
	  
	      char num= GeneradorContraseñas.getNumAl();
	      System.out.print(num);
	      int entero = Character.getNumericValue(num);
	      System.out.print(entero);
	      assertTrue(entero<11);
	      assertTrue(entero>-1);
	}
	@Test
	  
	public void generarContraseñatest0() {
		String esperado = "";
		String real =GeneradorContraseñas.generarContraseña(0,0);
		assertEquals(esperado, real);	
	}
	 @Test
	    public void testGenerate() {
	        GeneradorContraseñas generator = new GeneradorContraseñas();
	        String password = generator.generate();

	        // Contraseña no nula
	        assertNotNull(password);

	        // Su longitud es de 10
	        assertEquals(10, password.length());

	        // Tiene como minimo una letra y un numero
	        boolean hasLetter = false;
	        boolean hasNumber = false;
	        for (char c : password.toCharArray()) {
	            if (Character.isLetter(c)) {
	                hasLetter = true;
	            }
	            if (Character.isDigit(c)) {
	                hasNumber = true;
	            }
	        }
	        assertTrue(hasLetter);
	        assertTrue(hasNumber);
	    }
	}
    


