package recursividad;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;



public class GeneradorContraseñas {
	private final char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private final char[] num = "0123456789".toCharArray();
	private Random contraseña;
	
	public enum CharType {
		letras, num;
	}
	
	public GeneradorContraseñas() {
		contraseña = new Random();
	}
	
	private char getLetraAl() {
		return letras[contraseña.nextInt(letras.length)];
	}
	
	
	private char getNumAl() {
		return num[contraseña.nextInt(num.length)];
	}
	
	private List<CharType> getRandomOrder() {
		List<CharType> generationOrder = Arrays.asList(CharType.values());
		Collections.shuffle(generationOrder, contraseña);
		return generationOrder;
	}
	
	
	
	private String generateRec(int length, int size) {
	
		
			if (length == size) 
				return "";
			
			String password = null;
			char randomChar = 0;
			
			for (CharType charType : getRandomOrder()) {
				switch (charType) {
					case num:	password = generateRec(length + 1, size);
								randomChar = getNumAl();
								break;
								
					case letras:password = generateRec(length + 1, size);
								randomChar = getLetraAl();
								break;
				}
			
				if (password != null) 
					return randomChar + password;
			
		}
		
		return null; 
	}

	public String generate() {
		return generateRec(0, 10);
	}
}
