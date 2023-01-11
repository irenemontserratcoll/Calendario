package clases;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.IOException;

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
}
