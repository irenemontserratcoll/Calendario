package clases;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import baseDeDatos.GestorBaseDatos;

public class GestorEventosTest {

	private GestorEventos calendario12;
	private Evento evento1;
    private Evento evento2;
    private Evento evento3;
    private ArrayList<Evento> listaEventos;
    private File tempFile;

	@Before
	public void setUp() throws IOException {
		ArrayList<Evento> listaPrueba = new ArrayList<Evento>();
		listaPrueba.add(new Evento("Comida"));
		GestorBaseDatos g = new GestorBaseDatos();
		GestorBaseDatos.iniciar();
		calendario12 = new GestorEventos("PRUEBA",g );
		evento1 = new Evento("Evento 1");
        evento2 = new Evento("Evento 2");
        evento3 = new Evento("Evento 3");
        listaEventos = new ArrayList<Evento>();
        listaEventos.add(evento1);
        listaEventos.add(evento2);
        listaEventos.add(evento3);
        new ArrayList<Evento>();
        tempFile = File.createTempFile("evento1", ".csv");
        String csvData = "Evento 1,2022-01-01T00:00:00Z,2022-01-01T01:00:00Z,1.0,urgente,false\n" +
                "Evento 2,2022-01-02T00:00:00Z,2022-01-02T01:00:00Z,2.0,noUrgente,true";
        Files.write(Paths.get(tempFile.getAbsolutePath()), csvData.getBytes());
	}

	@Test
	public void testGetUsuario() {

		assertEquals("PRUEBA", calendario12.getUsuario());
	}

	@Test
    public void testGetListaEventosSemanal() {
        //ZonedDateTime currentDate = ZonedDateTime.now();

        //List<Evento> listaSemana = GestorEventos.getListaEventosSemanal(currentDate);

       // assertEquals(4, listaSemana.size());

        //assertEquals("Bailar", listaSemana.get(0).getNombre());
       // assertEquals("Correr", listaSemana.get(1).getNombre());
    }

	@After
    public void tearDown() {
        tempFile.delete();
    }

    @Test
    public void testCargarEnFicheroCSV() throws IOException {
        GestorEventos.cargarEnFicheroCSV();
        assertTrue(tempFile.exists());
        BufferedReader reader = new BufferedReader(new FileReader(tempFile));
        assertEquals("Evento 1,0,0,0,null,false", reader.readLine());
        assertEquals("Evento 2,0,0,0,null,true", reader.readLine());
        reader.close();
    }
    @Test
    public void testLeerDeFicheroCSV() throws IOException {
        listaEventos = (ArrayList<Evento>) GestorEventos.leerDeFicheroCSV(tempFile.getAbsolutePath());
        assertEquals(7, listaEventos.size());
        Evento evento1 = listaEventos.get(5);
        assertEquals("Evento 1", evento1.getNombre());
        assertEquals(ZonedDateTime.parse("2022-01-01T00:00:00Z"), evento1.getFechaInicio());
        assertEquals(ZonedDateTime.parse("2022-01-01T01:00:00Z"), evento1.getFechaFin());
        Evento evento2 = listaEventos.get(6);
        assertEquals("Evento 2", evento2.getNombre());
        assertEquals(ZonedDateTime.parse("2022-01-02T00:00:00Z"), evento2.getFechaInicio());
        assertEquals(ZonedDateTime.parse("2022-01-02T01:00:00Z"), evento2.getFechaFin());
        assertEquals(2.0f, evento2.getDuracionReal(), 0.1f);
        assertTrue(evento2.isUrgente());
    }
    @Test
    public void testCrearListaTareasPendientes() {
        List<Evento> tareasPendientes = GestorEventos.crearListaTareasPendientes(listaEventos);
        assertEquals(3, tareasPendientes.size());
        assertEquals("Evento 1", tareasPendientes.get(0).getNombre());
        assertEquals("Evento 3", tareasPendientes.get(2).getNombre());
    }

}
