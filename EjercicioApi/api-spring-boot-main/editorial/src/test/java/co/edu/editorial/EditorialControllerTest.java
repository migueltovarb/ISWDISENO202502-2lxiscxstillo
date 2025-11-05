package co.edu.editorial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.edu.editorial.modelo.Libro;
import co.edu.editorial.service.ILibroService;

public class EditorialControllerTest {

	@Mock
    private ILibroService libroServiceMock;

    @InjectMocks
    private EditorialController editorialController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaludar() {
        String nombre = "Juan";
        String expected = "hola Juan";
        String actual = editorialController.saludar(nombre);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testCrearLibro() {
        Libro libro = new Libro("titulo", 0, 0, 0);

        when(libroServiceMock.crearLibro(any(Libro.class))).thenReturn(libro);

        ResponseEntity<Libro> response = editorialController.crearLibro(libro);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(libro, response.getBody());
    }
    
    @Test
    public void testObtenerLibros() {
        List<Libro> libros = new ArrayList<>();
        when(libroServiceMock.getTodosLosLibros()).thenReturn(libros);

        ResponseEntity<List<Libro>> response = editorialController.obtenerLibros();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(libros, response.getBody());
    }
    

    @Test
    public void testObtenerLibroPorId() {
        ObjectId id = new ObjectId("66292c1e764cc864209e7937");
        Libro libro = new Libro("titulo", 0, 0, 0);
        when(libroServiceMock.getLibroPorId(id)).thenReturn(libro);

        ResponseEntity<Libro> response = editorialController.obtenerLibroPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(libro, response.getBody());
    }


    @Test
    public void testEliminarLibro() {
        ObjectId id = new ObjectId("66292c1e764cc864209e7937");

        HttpStatus expectedStatus = HttpStatus.OK;

        HttpStatus actualStatus = editorialController.eliminarLibro(id);

        assertEquals(expectedStatus, actualStatus);
        verify(libroServiceMock).eliminarLibro(id);
    }


    @Test
    public void testActualizarLibro() {
        ObjectId id = new ObjectId("66292c1e764cc864209e7937");
        Libro libro = new Libro("titulo", 0, 0, 0);
        libro.setId(id);

        when(libroServiceMock.actualizarLibro(libro)).thenReturn(libro);

        ResponseEntity<Libro> response = editorialController.actualizarLibro(id, libro);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(libro, response.getBody());
    }



}
