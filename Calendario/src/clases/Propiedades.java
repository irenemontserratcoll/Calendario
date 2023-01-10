package clases;

import java.io.FileReader;

import java.io.IOException;
import java.util.Properties;

public class Propiedades {

	public static String baseDatos;

	public static void main(String[] args) {
        // Creamos el objeto de propiedades
        Properties properties = new Properties();

        //Abrimos el reader para leer el fichero de disco
        try (FileReader reader = new FileReader("propiedades.properties")) {
            properties.load(reader); 
            baseDatos = properties.getProperty("dirBD");

        } catch (IOException e) {
            System.out.println("Error abriendo el fichero de propiedades.");
        }
    }
}
