package clases;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

public class PropiedadesTest {
    @Test
    public void testMain() {
        // Creamos el archivo de propiedades de prueba
        try (FileWriter writer = new FileWriter("propiedades.properties")) {
            writer.write("dirBD=datos/bd.dat");
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error creando el archivo de propiedades.");
        }

        // Corremos el método main
        Propiedades.main(null);

        // Verificamos que la propiedad se haya cargado correctamente
        assertEquals("datos/bd.dat", Propiedades.baseDatos);
    }
    @Test
    public void testMain_invalidPropertiesFile() {
        // Set up the test environment by creating a non-existent properties file
        File propFile = new File("propiedades.properties");
        propFile.delete();

        // Run the main method
        Propiedades.main(null);

    }
}
