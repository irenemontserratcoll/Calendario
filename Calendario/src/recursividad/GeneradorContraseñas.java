package recursividad;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;



public class GeneradorContraseñas {
	public static char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	public static char[] num = "0123456789".toCharArray();
	private static Random random = new Random();
	
	public enum CharType {
		letras, num;
	}
	
	
	public static char getLetraAl() {
		return letras[random.nextInt(letras.length)];
	}
	
	
	public static char getNumAl() {
		return num[random.nextInt(num.length)];
	}
	
	private static List<CharType> getRandomOrder() {
		List<CharType> generationOrder = Arrays.asList(CharType.values());
		Collections.shuffle(generationOrder, random);
		return generationOrder;
	}
	
	
	
	public static String generarContraseña(int length, int size) {
	
			if (length == size) 
				return "";
			
			String contraseña = null;
			char randomChar = 0;
			
			for (CharType charType : getRandomOrder()) {
				switch (charType) {
					case num:	contraseña = generarContraseña(length + 1, size);
								randomChar = getNumAl();
								break;
								
					case letras:contraseña = generarContraseña(length + 1, size);
								randomChar = getLetraAl();
								break;
				}
			}

		return randomChar + contraseña;
			

		
	}

	public String generate() {
		return generarContraseña(0, 10);
	}
}
