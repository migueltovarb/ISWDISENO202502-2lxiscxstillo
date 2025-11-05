package co.edu.editorial.service;

import java.util.List;

import org.bson.types.ObjectId;

import co.edu.editorial.modelo.Libro;

public interface ILibroService {
	Libro crearLibro(Libro libro);
	Libro actualizarLibro(Libro libro);
	List <Libro> getTodosLosLibros();
	Libro getLibroPorId(ObjectId libroId);
	void eliminarLibro(ObjectId libroId);
	

}
