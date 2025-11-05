package co.edu.editorial.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.editorial.modelo.Libro;
import co.edu.editorial.repository.LibroRepository;
import org.bson.types.ObjectId;



@Service
@Transactional
public class LibroService implements ILibroService {
	@Autowired
    private LibroRepository libroRepository;
	

	public Libro crearLibro(Libro libro) {
		libro.setId(null);
        return libroRepository.save(libro);
    }
	
	public Libro actualizarLibro(Libro libro) {
		this.libroRepository.findById(libro.getId());
        Optional <Libro> libroDb = this.libroRepository.findById(libro.getId());

        if (libroDb.isPresent()) {
            Libro libroToUpdate = libroDb.get();
            libroToUpdate.setTitulo(libro.getTitulo());
            libroToUpdate.setPrecio(libro.getPrecio());
            libroToUpdate.setNumeroPaginas(libro.getNumeroPaginas());
            libroToUpdate.setAnyoPublicacion(libro.getAnyoPublicacion());
           
            libroRepository.save(libroToUpdate);
            return libroToUpdate;
        } 
        return null;
       }
		
	public List <Libro> getTodosLosLibros() {
        return this.libroRepository.findAll();
    }
	
	public Libro getLibroPorId(ObjectId libroId) {

        Optional <Libro> libroDb = this.libroRepository.findById(libroId);

        if (libroDb.isPresent()) {
            return libroDb.get();
        } 
        return null;
   }
	
	public void eliminarLibro(ObjectId libroId) {
        Optional <Libro> libroDb = this.libroRepository.findById(libroId);

        if (libroDb.isPresent()) {
            this.libroRepository.delete(libroDb.get());
        } 
	}
	
	
   
	

}


